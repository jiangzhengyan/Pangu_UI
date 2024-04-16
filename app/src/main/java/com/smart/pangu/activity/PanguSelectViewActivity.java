package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.smart.pangu.R;
import com.smart.pangu.UserUtil;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.entity.SelectItem;
import com.smart.pangu_ui_lib.widget.PanguSelectView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :   盘古选择框
 *
 * @author jiangzhengyan  2024/4/9 15:57
 */
public class PanguSelectViewActivity extends BaseActivity {


    @Bind(R.id.psv_1)
    PanguSelectView mPsv1;
    @Bind(R.id.psv_2)
    PanguSelectView mPsv2;

    public static void start(Context context) {
        Intent intent = new Intent(context, PanguSelectViewActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_pangu_select_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mPsv1.setData(UserUtil.getSelectItems());
        mPsv2.setData(UserUtil.getSelectItems());
    }

    @Override
    protected void loadData() {

    }


}