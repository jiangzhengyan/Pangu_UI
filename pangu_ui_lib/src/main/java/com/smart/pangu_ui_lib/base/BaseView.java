package com.smart.pangu_ui_lib.base;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


import com.smart.pangu_ui_lib.R;


/**
 * 本类的主要功能是 :  自定义View 基类
 *
 * @author jiang_zheng_yan  2020/4/3 17:44
 */
public abstract class BaseView extends FrameLayout {

    protected View mContentView;
    protected Context mContext;
    private Dialog loadingDialog;

    public BaseView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public BaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;

    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        mContentView = View.inflate(context, getLayoutId(), this);

        //水平显示时候,如果宽度过宽,需要子类重新设置布局宽度(WRAP_CONTENT或者自定义宽度)
        LayoutParams params = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);
    }

    protected abstract int getLayoutId();

    public void showLoading() {

        if (loadingDialog == null) {
            loadingDialog = new Dialog(getContext(), R.style.custom_dialog_style);
            View dialogView = View.inflate(getContext(), R.layout.common_waiting_dialog, null);
            loadingDialog.setContentView(dialogView);
            loadingDialog.setCanceledOnTouchOutside(false);//点击空白是否消失
        }
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        loadingDialog = null;
    }

    protected OnClickListener onCustomClickListener;

    /**
     * 自定义定义点击监听
     * @param onCustomClickListener
     */
    public void setOnCustomClickListener(OnClickListener onCustomClickListener) {
        this.onCustomClickListener = onCustomClickListener;
    }

}
