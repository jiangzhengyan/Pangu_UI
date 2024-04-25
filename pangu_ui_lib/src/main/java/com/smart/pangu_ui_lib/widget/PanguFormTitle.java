package com.smart.pangu_ui_lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.BaseView;
import com.smart.pangu_ui_lib.util.PhoneHelper;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

/**
 * 本类的主要功能是 :  详情|表单的小标题
 *
 * @author jiangzhengyan  2024/4/23 10:48
 */
public class PanguFormTitle extends BaseView {
    private LinearLayout mLlRoot;
    private View mViewLine;
    private TextView mTvMore;
    private ImageView mIvRight;
    private LinearLayout mLlMidContent;
    private RelativeLayout mRlContent;
    private TextView mTvName;
    private TextView mTvMust;


    private int showRightTitle;
    private float paddingStart;
    private String midTitle;
    private int midTitleTextColor;
    private int backgroundColor;
    private String title;
    private String rightTitle;//右侧标题
    private int iconRight;
    private int titleTextStyle;//title的加粗
    private int titleColor;//标题颜色
    private int titleSize;//标题字体大小
    private boolean must;//必填红星
    private boolean showLine;//是否展示下面的线

    @Override
    protected int getLayoutId() {
        return R.layout.pangu_formtitle;
    }

    public PanguFormTitle(Context context) {
        super(context);
    }

    public PanguFormTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mLlRoot = findViewById(R.id.ll_root);
        mViewLine = findViewById(R.id.view_line);
        mTvMore = findViewById(R.id.tv_more);
        mIvRight = findViewById(R.id.iv_right);
        mLlMidContent = findViewById(R.id.ll_mid_content);
        mRlContent = findViewById(R.id.rl_content);
        mTvName = findViewById(R.id.tv_name);
        mTvMust = findViewById(R.id.tv_must);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PanguFormTitle
                , defStyleAttr, 0);
        //名称
        title = typedArray.getString(R.styleable.PanguFormTitle_pgft_title);
        rightTitle = typedArray.getString(R.styleable.PanguFormTitle_pgft_right_title);
        must = typedArray.getBoolean(R.styleable.PanguFormTitle_pgft_must, false);
        showLine = typedArray.getBoolean(R.styleable.PanguFormTitle_pgft_show_line, false);
        backgroundColor = typedArray.getColor(R.styleable.PanguFormTitle_pgft_background,
                Color.TRANSPARENT);
        showRightTitle = typedArray.getInt(R.styleable.PanguFormTitle_pgft_show_right_title, View.GONE);
        //中间
        midTitle = typedArray.getString(R.styleable.PanguFormTitle_pgft_mid_title);
        midTitleTextColor = typedArray.getColor(R.styleable.PanguFormTitle_pgft_mid_textColor,
                Color.BLACK);
        //右侧图标
        iconRight = typedArray.getResourceId(R.styleable.PanguFormTitle_pgft_icon_right, 0);
        paddingStart = typedArray.getDimension(R.styleable.PanguFormTitle_pgft_padding_start, PhoneHelper.dip2px(mContext, 12));
        //title的加粗
        titleTextStyle = typedArray.getInt(R.styleable.PanguFormTitle_pgft_title_text_style, Typeface.BOLD);
        //标题的颜色
        titleColor = typedArray.getInteger(R.styleable.PanguFormTitle_pgft_title_color, Color.parseColor("#30353D"));
        //标题字体大小
        titleSize = typedArray.getDimensionPixelSize(R.styleable.PanguFormTitle_pgft_title_size, PhoneHelper.sp2px(getContext(), 14));

        typedArray.recycle();

        init();
    }


    private void init() {

        //设置标题名称
        setTitle(title);
        setIsMust(must);
        setBackgroundColorFt(backgroundColor);
        showLine(showLine);
        setRightTitle(rightTitle);
        setRightRIcon(iconRight);
        showRightTitle(showRightTitle);
        setPaddingStart(paddingStart);
        setTitleTextStyle(titleTextStyle);
        //标题的颜色
        setTitleColor(titleColor);
        setTitleSize(titleSize);
        setMidText(midTitle);
        setMidTextColor(midTitleTextColor);
    }

    /**
     * 设置中间文字的字体颜色
     *
     * @param midTitleTextColor 颜色值
     */
    public void setMidTextColor(int midTitleTextColor) {
        this.midTitleTextColor = midTitleTextColor;
        LinearLayout midContentView = getMidContentView();
        if (midContentView != null && midContentView.getChildCount() > 0 && midContentView.getChildAt(0) instanceof TextView) {
            TextView childAt = (TextView) midContentView.getChildAt(0);
            childAt.setTextColor(midTitleTextColor);
        }
    }

    /**
     * 设置控件中间的文字
     *
     * @param midTitle
     */
    public void setMidText(String midTitle) {
        TextView tv2 = new TextView(mContext);
        tv2.setText(midTitle);
        tv2.setTextSize(14);
        tv2.setTextColor(midTitleTextColor);
        getMidContentView().removeAllViews();
        getMidContentView().addView(tv2);
    }

    /**
     * paddingStart
     *
     * @param paddingStart
     */
    public void setPaddingStart(float paddingStart) {
        mRlContent.setPadding((int) paddingStart, PhoneHelper.dip2px(mContext, 9), PhoneHelper.dip2px(mContext, 12), PhoneHelper.dip2px(mContext, 9));
    }

    /**
     * 展示右侧标题
     *
     * @param showRightTitle {@link View#VISIBLE}{@link View#INVISIBLE}{@link View#GONE}
     */
    public void showRightTitle(int showRightTitle) {
        if (mTvMore != null)
            mTvMore.setVisibility(showRightTitle);

    }

    /**
     * 获取中间的线性布局,可以自定义布局内容
     *
     * @return LinearLayout
     */
    public LinearLayout getMidContentView() {
        return mLlMidContent;
    }


    /**
     * 显示下面的线
     *
     * @param showLine
     */
    public void showLine(boolean showLine) {
        if (mViewLine != null) {
            mViewLine.setVisibility(showLine ? VISIBLE : GONE);
        }
    }


    /**
     * 设置背景颜色
     *
     * @param color
     */
    public void setBackgroundColorFt(@ColorInt int color) {

        if (mLlRoot != null) {
            mLlRoot.setBackgroundColor(color);
        }
    }

    /**
     * 设置标题名称
     *
     * @param title
     */
    public void setTitle(String title) {
        if (mTvName != null) {
            mTvName.setText(title);
        }
    }

    /**
     * 设置标题名称
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        if (mTvName != null) {
            mTvName.setText(title);
        }
    }

    /**
     * 设置标题样式
     *
     * @param typeface Typeface.BOLD 加粗  Typeface.NORMAL 正常
     */
    public void setTitleTextStyle(int typeface) {
        if (mTvName != null) {
            mTvName.setTypeface(Typeface.defaultFromStyle(typeface));
        }

    }

    /**
     * 设置标题字体的大小
     *
     * @param titleSize
     */
    public void setTitleSize(int titleSize) {
        mTvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
    }

    /**
     * 设置标题的颜色
     *
     * @param titleColor
     */
    public void setTitleColor(int titleColor) {
        if (mTvName != null) {
            mTvName.setTextColor(titleColor);
        }
    }

    /**
     * 是否是必填
     *
     * @param must
     */
    public void setIsMust(boolean must) {
        if (mTvMust != null) {
            mTvMust.setVisibility(must ? VISIBLE : GONE);
        }
    }

    /**
     * 是否显示右侧标题,默认是"更多"文字
     *
     * @param has
     */
    public void setShowRight(boolean has) {
        mTvMore.setVisibility(has ? VISIBLE : GONE);
    }

    /**
     * 设置右侧标题
     *
     * @param title 标题
     */
    public void setRightTitle(String title) {
        if (mTvMore != null) {
            mTvMore.setText(TextUtils.isEmpty(title) ? "更多>" : title);
        }
    }

    /**
     * 右侧标题的点击事件
     *
     * @param listener 监听
     */
    public void setOnRightClick(OnClickListener listener) {
        mTvMore.setOnClickListener(listener);
    }

    /**
     * 设置右侧图标 , 0 不显示
     *
     * @param resId 资源id
     */
    public void setRightRIcon(@DrawableRes int resId) {
        if (mIvRight != null) {
            if (resId != 0) {
                mIvRight.setVisibility(VISIBLE);
                mIvRight.setImageResource(resId);
            } else {
                mIvRight.setVisibility(GONE);
            }
        }
    }

    /**
     * 右侧图标的点击事件
     *
     * @param listener 监听
     */
    public void setOnRightIconClick(OnClickListener listener) {
        mIvRight.setOnClickListener(listener);
    }

}