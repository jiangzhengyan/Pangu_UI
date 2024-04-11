package com.smart.pangu.pop;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.pangu.R;
import com.smart.pangu_ui_lib.base.PanguBasePop;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 本类的主要功能是 :   全屏pop
 *
 * @author jiangzhengyan  2024/4/11 14:29
 */
public class PopFullScreenPangu extends PanguBasePop {

    @Bind(R.id.tv_cancel)
    TextView mTvCancel;
    @Bind(R.id.tv_sure)
    TextView mTvSure;
    @Bind(R.id.ll_container)
    LinearLayout mLlContainer;
    @Bind(R.id.ll_pop_root)
    LinearLayout mLlPopRoot;


    public PopFullScreenPangu(Context context) {
        super(context);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pop_full_screen;
    }

    @Override
    public void initData(View layout, Context context) {
        ButterKnife.bind(this, layout);

        //动画
        // setAnimaType(AnimaType.BOTTOM_IN_OUT);
    }


    @OnClick({R.id.tv_cancel, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_sure:
                dismiss();
                if (onClickSureListener != null) {
                    onClickSureListener.onClick(view);
                }
                break;
        }
    }
}
