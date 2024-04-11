package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.smart.pangu.R;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu.pop.PopFullScreenPangu;
import com.smart.pangu_ui_lib.base.PanguBasePop;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 本类的主要功能是 :  盘古Pop
 *
 * @author jiangzhengyan  2024/4/11 14:17
 */
public class PanguPopActivity extends BaseActivity {


    @Bind(R.id.tv_1)
    TextView mTv1;
    @Bind(R.id.tv_2)
    TextView mTv2;
    @Bind(R.id.tv_3)
    TextView mTv3;

    public static void start(Context context) {
        Intent intent = new Intent(context, PanguPopActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_pangu_pop;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {
    }

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3})
    public void onViewClicked(View view) {
        PopFullScreenPangu popFullScreen = new PopFullScreenPangu(this);
        switch (view.getId()) {
            case R.id.tv_1:
                popFullScreen.setAnimaType(PanguBasePop.AnimaType.BOTTOM_IN_OUT);
                break;
            case R.id.tv_2:
                popFullScreen.setAnimaType(PanguBasePop.AnimaType.TOP_IN_OUT);

                break;
            case R.id.tv_3:
                popFullScreen.setAnimaType(PanguBasePop.AnimaType.SCALE_IN_OUT);

                break;
        }
        popFullScreen.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                showToast("dismis");
            }
        });
        popFullScreen.showAtCenter();
    }
}