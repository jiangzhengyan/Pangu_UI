package com.smart.pangu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smart.pangu.activity.PanguInputViewActivity;
import com.smart.pangu.activity.PanguNavActivity;
import com.smart.pangu.activity.PanguSelectViewActivity;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.widget.PanguNavBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {


    @Bind(R.id.btn1)
    Button mBtn1;
    @Bind(R.id.btn2)
    Button mBtn2;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //盘古输入框
                PanguInputViewActivity.start(this);
                break;
            case R.id.btn2:
                //盘导航栏
                PanguNavActivity.start(this);
                break;
            case R.id.btn3:
                //盘选择栏
                PanguSelectViewActivity.start(this);
                break;
        }
    }
}