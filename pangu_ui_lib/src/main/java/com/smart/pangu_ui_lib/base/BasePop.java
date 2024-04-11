package com.smart.pangu_ui_lib.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

import com.smart.pangu_ui_lib.BuildConfig;
import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.impl.OnBackPressListener;
import com.smart.pangu_ui_lib.util.PhoneHelper;

import java.util.HashMap;
import java.util.UUID;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * 本类的主要功能是 :  弹窗基类
 *
 * @author jiangzhengyan  2024/4/10 9:48
 */
@SuppressLint("NewApi")
public abstract class BasePop implements OnDismissListener {
    protected PopupWindow popupWindow;
    protected OnClickListener onClickListener;
    protected OnClickListener onClickSureListener;// 确定
    protected OnClickListener onClickCancelListener; // 取消
    protected OnItemClickListener itemClickListener;
    protected OnDismissListener onDismissListener;
    protected OnBackPressListener onBackPressListener;//返回键监听
    protected Context mContext;

    public BasePop(Context context) {
        this.mContext = context;
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(getContentViewId(), null);

        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT) {
            @Override
            public void dismiss() {
                if (onBackPressListener == null) {
                    super.dismiss();
                    return;
                }
                StackTraceElement[] stackTrace = new Exception().getStackTrace();
                //按了返回键; 并且需要自己处理
                boolean b = stackTrace.length >= 2 && "dispatchKeyEvent".equals(stackTrace[1].getMethodName()) && onBackPressListener.onBackPressed(this);
                if (!b) {
                    super.dismiss();
                }
            }
        };
        setWindowAlpha(0.7F);
        popupWindow.setClippingEnabled(false);
        popupWindow.setOnDismissListener(this);
        initData(view, context);
    }

    /**
     * 设置弹窗背景为透明灰色
     *
     * @param alpha 1,透明 0不透明
     */
    private void setWindowAlpha(float alpha) {
        Context context = getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;

            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = alpha;
            activity.getWindow().setAttributes(lp);
        }
    }

    public Context getContext() {

        return mContext;
    }

    public void setOnBackPressListener(OnBackPressListener onBackPressListener) {
        this.onBackPressListener = onBackPressListener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    protected abstract int getContentViewId();

    public abstract void initData(View layout, Context context);


    /**
     * 隐藏pop
     */
    public void dismiss() {
        popupWindow.dismiss();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //点击监听
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    //确定
    public void setOnClickSureListener(OnClickListener onClickListener) {
        this.onClickSureListener = onClickListener;
    }

    //取消
    public void setOnClickCancelListener(OnClickListener onClickListener) {
        this.onClickCancelListener = onClickListener;
    }

    public void onDismiss() {
        setWindowAlpha(1F);
        popupWindow.dismiss();
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public enum AnimaType {
        TOP_IN_OUT, BOTTOM_IN_OUT,
        // LEFT_IN_OUT, RIGHT_IN_OUT,
        SCALE_IN_OUT
    }

    /**
     * 设置动画样式
     *
     * @param styleType {@link AnimaType}
     */
    public void setAnimaType(AnimaType styleType) {

        if (styleType == AnimaType.TOP_IN_OUT) {

            popupWindow.setAnimationStyle(R.style.PopAnim_top_in_out);
        }
        if (styleType == AnimaType.BOTTOM_IN_OUT) {
            popupWindow.setAnimationStyle(R.style.PopAnim_bottom_in_out);
        }
//        if (styleType == AnimaType.LEFT_IN_OUT) {
//
//        }
//        if (styleType == AnimaType.RIGHT_IN_OUT) {
//
//        }
        if (styleType == AnimaType.SCALE_IN_OUT) {
            popupWindow.setAnimationStyle(R.style.PopAnim_scale_in_out);
        }
    }

    /**
     * 只显示内容区域
     *
     * @param anchor
     * @param xoff
     * @param yoff
     * @param popHeight 视图高度
     */
    public void showAsDropDownCommon(View anchor, int popHeight, int xoff, int yoff) {

        popupWindow.setWidth(anchor.getWidth());
        popupWindow.setHeight(popHeight);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true); // 设置PopupWindow可触摸
        popupWindow.showAsDropDown(anchor, xoff, yoff);
        popupWindow.update();
    }


    /**
     * 展示在屏幕中间
     */
    public void showAtCenter() {
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 使其聚集
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true); // 设置PopupWindow可触摸

        popupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        popupWindow.update();
    }

    /**
     * 下拉式 弹出 pop菜单 parent 右下角
     */
    @SuppressWarnings("deprecation")
    public void showAsDropDown(View anchor) {
        // 这个是为了点击“返回Back”也能使其消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(false);
        // 使其聚集
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true); // 设置PopupWindow可触摸
        //兼容api24
        if (anchor != null && Build.VERSION.SDK_INT >= 24) {
            //获取屏幕真实高度
            int screenHeightReal = PhoneHelper.getScreenHeightReal(mContext);
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            //保留1像素的线宽
            int h = screenHeightReal - rect.top - 1;
            popupWindow.setHeight(h);
        }
        // 设置弹出位置
        popupWindow.showAsDropDown(anchor);
        // 刷新状态
        popupWindow.update();
    }

    @SuppressWarnings("deprecation")
    public void showPopUp(View parent) {
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        popupWindow.showAtLocation(parent, Gravity.TOP, 15, location[1] - popupWindow.getHeight());
        popupWindow.update();
    }

    public boolean isShowing() {
        return popupWindow.isShowing();
    }

    // 吐司
    public void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
