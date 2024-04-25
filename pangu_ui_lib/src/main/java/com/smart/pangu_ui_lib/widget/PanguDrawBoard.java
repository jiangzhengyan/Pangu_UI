package com.smart.pangu_ui_lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.util.FilePathUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.ColorInt;

/**
 * 本类的主要功能是 :  手写画板
 *
 * @author jiangzhengyan  2024/4/25 10:34
 */
public class PanguDrawBoard extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    private Context mContext;
    private int paintColor = Color.BLACK;
    private int paintSize = 10;
    private boolean isClean = true;//画布画板是否被清空

    public PanguDrawBoard(Context context) {
        super(context);
        initPaint(context, null, 0);
    }

    public PanguDrawBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context, attrs, 0);
    }

    public PanguDrawBoard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint(context, attrs, defStyle);
    }

    private void initPaint(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PanguDrawBoard, defStyle, 0);
        paintColor = typedArray.getInteger(R.styleable.PanguDrawBoard_pgdb_paint_color, Color.BLACK);
        typedArray.recycle();

        this.mContext = context;
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(paintColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(paintSize);
    }


    /**
     * 设置画笔的大小
     *
     * @param paintSize
     */
    public void setPaintSize(int paintSize) {
        this.paintSize = paintSize;
    }

    /**
     * 设置画笔的颜色
     *
     * @param color
     */
    public void setPaintColor(@ColorInt int color) {
        this.paintColor = color;
        mPaint.setColor(color);
    }

    /**
     * 设置画笔
     *
     * @param paint
     */
    public void setPaint(Paint paint) {
        this.mPaint = paint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    /**
     * 设置画布文件
     *
     * @param filePath 画布图片文件的路径
     */
    public void setFile(String filePath) {
        isClean = false;
        File file = new File(filePath);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inMutable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        if (bitmap == null) {
            return;
        }
        mBitmap = transBitmap(bitmap, getMeasuredWidth(), getMeasuredHeight());
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }

    /**
     * 把画布bitmap对象转换为宽度为x,长度为y的bitmap对象
     *
     * @param b
     * @param x
     * @param y
     * @return
     */
    private Bitmap transBitmap(Bitmap b, float x, float y) {
        int w = b.getWidth();
        int h = b.getHeight();
        float sx = x / w;
        float sy = y / h;
        Matrix matrix = new Matrix();
        //也可以按两者之间最大的比例来设置放大比例，这样不会是图片压缩
//        float bigerS = Math.max(sx,sy);
//        matrix.postScale(bigerS,bigerS);
        matrix.postScale(sx, sy); // 长和宽放大缩小的比例
        return Bitmap.createBitmap(b, 0, 0, w, h, matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0x00000000);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreenƒ
        mCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isClean = false;
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 获取已画完的图片文件路径
     *
     * @return
     */
    public String getSignFile() {

        String publicDir = FilePathUtil.getAppExternalFilesDir(mContext, "sign-pic");

        File file = new File(publicDir, +System.currentTimeMillis() + "sign.png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    /**
     * 清空画布
     */
    public void clean() {
        isClean = true;
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    /**
     * 画布画板是否被清空
     *
     * @return
     */
    public boolean getIsClean() {
        return isClean;
    }

}
