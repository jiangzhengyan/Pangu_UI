package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.smart.pangu.R;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.widget.PanguInputView;

import butterknife.Bind;

/**
 * 本类的主要功能是 :   盘古输入框
 *
 * @author jiangzhengyan  2024/4/9 15:57
 */
public class PanguInputViewActivity extends BaseActivity {

    @Bind(R.id.piv_1)
    PanguInputView mPiv1;
    @Bind(R.id.piv_3)
    PanguInputView mPiv3;

    public static void start(Context context) {
        Intent intent = new Intent(context, PanguInputViewActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_pangu_input_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mPiv1.setOnRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击");
            }
        });
        mPiv3.setOnRBClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击....");
            }
        });
    }

    @Override
    protected void loadData() {

    }

}