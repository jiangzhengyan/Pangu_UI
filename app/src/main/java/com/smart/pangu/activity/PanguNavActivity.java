package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.smart.pangu.R;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.widget.PanguNavBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :   盘古个性化标题栏|导航栏
 *
 * @author jiangzhengyan  2024/4/9 15:57
 */
public class PanguNavActivity extends BaseActivity {


    @Bind(R.id.pg_nav_bar2)
    PanguNavBar mPgNavBar2;

    public static void start(Context context) {
        Intent intent = new Intent(context, PanguNavActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_pangu_nav;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPgNavBar2.setOnRightClickListener(v -> {
            showToast("点击1");
        });
        mPgNavBar2.setOnRightIconClickListener(v -> {
            showToast("点击2");
        });
        mPgNavBar2.setOnRightIcon2ClickListener(v -> {
            showToast("点击3");
        });
    }

    @Override
    protected void loadData() {

    }

}