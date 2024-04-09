package com.smart.pangu_ui_lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.BaseView;
import com.smart.pangu_ui_lib.util.PhoneHelper;

import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;


/**
 *  本类的主要功能是 :   导航栏
 *
 * @author  jiangzhengyan  2024/4/9 15:43
 *
 */
public class PanguNavBar extends BaseView {

    private ImageView mIvLeft;
    private TextView mTvLeft;
    private LinearLayout mLlLeft;
    private TextView mTvMid;
    private ImageView mIvRight;
    private TextView mTvRight;
    private ConstraintLayout mClNavBar;
    private ImageView mIvRight2;


    private String title_mid;
    private String title_left;
    private String title_right;
    private int iconLeft;
    private int iconRight;
    private int iconRight2;
    private Drawable title_bg;

    @Override
    protected int getLayoutId() {
        return R.layout.pangu_navi_bar;
    }

    public PanguNavBar(Context context) {
        this(context, null);
    }

    public PanguNavBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanguNavBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PanguNavBar, defStyleAttr, 0);
        // 中间标题
        title_mid = typedArray.getString(R.styleable.PanguNavBar_pangu_title_mid);
        // 左标题
        title_left = typedArray.getString(R.styleable.PanguNavBar_pangu_title_left);
        // 右标题
        title_right = typedArray.getString(R.styleable.PanguNavBar_pangu_title_right);
//        showBg = typedArray.getBoolean(R.styleable.custom_nav_bar_show_bg, true);
        // 左图标
        iconLeft = typedArray.getResourceId(R.styleable.PanguNavBar_pangu_icon_left, 0);
        // 右图标
        iconRight = typedArray.getResourceId(R.styleable.PanguNavBar_pangu_icon_right, 0);
        // 右图标2
        iconRight2 = typedArray.getResourceId(R.styleable.PanguNavBar_pangu_icon_right_2, 0);
        title_bg = typedArray.getDrawable(R.styleable.PanguNavBar_pangu_title_bg);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        mIvLeft = findViewById(R.id.iv_left);
        mTvLeft = findViewById(R.id.tv_left);
        mLlLeft = findViewById(R.id.ll_left);
        mTvMid = findViewById(R.id.tv_mid);
        mIvRight = findViewById(R.id.iv_right);
        mTvRight = findViewById(R.id.tv_right);
        mClNavBar = findViewById(R.id.cl_nav_bar);
        mIvRight2 = findViewById(R.id.iv_right_2);


        setMidTitle(title_mid);
        setLeftTitle(title_left);
        setLeftIcon(iconLeft);
        setRightTitle(title_right);
        setRightIcon(iconRight);
        setRightIcon2(iconRight2);

        setBgDrawable(title_bg);
    }

    private void setBgDrawable(Drawable title_bg) {
        if (title_bg == null) {
            return;
        }
        mClNavBar.setBackground(title_bg);
    }

    public void setBgColor(int bgColor) {
        mClNavBar.setBackgroundColor(bgColor);
    }

    public void setBgResource(int resid) {
        mClNavBar.setBackgroundResource(resid);
    }


    // 左侧点击事件
    public void setOnLelftClickListener(View.OnClickListener listener) {
        if (mLlLeft != null) {
            mLlLeft.setOnClickListener(listener);
        }
    }

    public void setOnRightClickListener(View.OnClickListener listener) {
        if (mTvRight != null) {
            mTvRight.setOnClickListener(listener);
        }
    }

    public void setOnRightIconClickListener(View.OnClickListener listener) {
        if (mIvRight != null) {
            mIvRight.setOnClickListener(listener);
        }
    }

    public void setOnRightIcon2ClickListener(View.OnClickListener listener) {
        if (mIvRight2 != null) {
            mIvRight2.setOnClickListener(listener);
        }
    }

//    public void setEnableBackground(boolean enable) {
//        mParent.setBackgroundColor(enable ? Color.parseColor("#D01816") : Color.TRANSPARENT);
//        mStatusBar.setBackgroundColor(enable ? Color.parseColor("#D01816") : Color.TRANSPARENT);
//        mBack.setImageResource(enable ? R.mipmap.icon_back_white : R.mipmap.icon_back_gray);
//        mTitle.setTextColor(enable ? Color.parseColor("#FFFFFF") : Color.parseColor("#333333"));
//    }

    /**
     * 隐藏返回按钮
     */
    public void leftIconGone() {
        if (mIvLeft != null) {
            mIvLeft.setVisibility(View.GONE);
        }
    }

    /**
     * 显示返回按钮
     */
    public void leftIconVisi() {
        if (mIvLeft != null) {
            mIvLeft.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏右侧图标
     */
    public void rightIconGone() {
        if (mIvRight != null) {
            mIvRight.setVisibility(View.GONE);
        }
    }

    /**
     * 隐藏右侧文字title
     */
    public void rightTitleGone() {
        if (mTvRight != null) {
            mTvRight.setVisibility(View.GONE);
        }
    }

    public ImageView getIconRight2() {
        return mIvRight2;
    }

    /**
     * 显示右侧图标
     */
    public void rightIconVisi() {
        if (mIvRight != null) {
            mIvRight.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示右侧标题
     */
    public void rightTitleVisi() {
        if (mTvRight != null) {
            mTvRight.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 设置状态栏背景颜色
     *
     * @param color
     */
//    public void setStatusBarBackground(@ColorInt int color) {
//        if (mParent != null) {
//            mParent.setBackgroundColor(color);
//        }
//    }

    /**
     * 设置标题背景颜色
     */
//    public void setNavBarBackground(@ColorInt int color) {
//        if (mStatusBar != null) {
//            mStatusBar.setBackgroundColor(color);
//        }
//    }

    // 设置主标题
    public void setMidTitle(String title) {
        if (mTvMid == null) {
            return;
        }
        if (!TextUtils.isEmpty(title)) {
            mTvMid.setVisibility(VISIBLE);
            mTvMid.setText(title);
        } else {
            mTvMid.setVisibility(GONE);
        }
    }

    // 设置左图标
    public void setLeftIcon(@DrawableRes int resId) {
        if (mIvLeft == null) {
            return;
        }
        if (resId != 0) {
            mIvLeft.setVisibility(VISIBLE);
            mIvLeft.setImageResource(resId);
        }
    }

    // 设置左标题
    public void setLeftTitle(String leftText) {
        if (mTvLeft == null) {
            return;
        }
        if (!TextUtils.isEmpty(leftText)) {
            mTvLeft.setVisibility(VISIBLE);
            mTvLeft.setText(leftText);
        } else {
            mTvLeft.setVisibility(GONE);
        }
    }

    // 设置右图标
    public void setRightIcon(@DrawableRes int resId) {
        if (mIvRight == null) {
            return;
        }
        if (resId != 0) {
            mIvRight.setVisibility(VISIBLE);
            mIvRight.setImageResource(resId);
        } else {
            mIvRight.setVisibility(GONE);
        }
    }

    /**
     * 设置右侧图标以背景图设置
     *
     * @param resId 图标  ,-1为隐藏
     */
    public void setRightIconBg(@DrawableRes int resId) {
        mIvRight.setVisibility(resId == -1 ? GONE : VISIBLE);
        if (resId == -1) {
            return;
        }
        mIvRight.setBackgroundResource(resId);
    }

    // 设置右2图标
    public void setRightIcon2(@DrawableRes int resId) {
        if (mIvRight2 == null) {
            return;
        }
        if (resId != 0) {
            mIvRight2.setVisibility(VISIBLE);
            mIvRight2.setImageResource(resId);
        } else {
            mIvRight2.setVisibility(GONE);
        }
    }

    /**
     * 设置右侧第二个图标以背景图设置
     *
     * @param resId 图标  ,-1为隐藏
     */
    public void setRightIcon2Bg(int resId) {
        mIvRight2.setVisibility(resId == -1 ? GONE : VISIBLE);
        mIvRight2.setBackgroundResource(resId);
    }

    //设置右标题
    public void setRightTitle(String rightText) {
        if (mTvRight == null) {
            return;
        }
        if (!TextUtils.isEmpty(rightText)) {
            mTvRight.setVisibility(VISIBLE);
            mTvRight.setText(rightText);
        } else {
            mTvRight.setVisibility(GONE);
        }
    }

    public ImageView getIvRight() {
        return mIvRight;
    }

    /**
     * 设置左边icon的大小
     *
     * @param type
     */
    public View getSubView(int type) {
        View view = null;
        switch (type) {
            case 0: //获取左图标
                view = mIvLeft;
                break;
            case 1://获取左标题
                view = mTvLeft;
                break;
            case 2://获取右图标
                view = mIvRight;
                break;
            case 3://获取右标题
                view = mTvRight;
                break;
            case 4://标题
                view = mTvMid;
                break;
        }

        return view;
    }

    /**
     * 获取右标题文字
     *
     * @return
     */
    public String getRightTitle() {
        if (mTvRight == null) {
            return "";
        }
        return mTvRight.getText().toString();
    }

    /**
     * 设置右边icon大小
     *
     * @param w 宽dp值
     * @param h 高dp值
     */
    public void setRighIcontSize(int w, int h) {
        ViewGroup.LayoutParams layoutParams = mIvRight.getLayoutParams();
        layoutParams.height = (int) PhoneHelper.dip2px(getContext(), h);
        layoutParams.width = (int) PhoneHelper.dip2px(getContext(), w);
        mIvRight.setLayoutParams(layoutParams);

    }
}