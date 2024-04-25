package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smart.pangu.R;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.widget.PanguFormTitle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :  详情|表单的小标题
 *
 * @author jiangzhengyan  2024/4/19 15:51
 */
public class FormTitleActivity extends BaseActivity {


    @Bind(R.id.pg_ftitle)
    PanguFormTitle mPgFtitle;

    public static void start(Context context) {
        Intent intent = new Intent(context, FormTitleActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_form_title;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPgFtitle.setOnRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击了右标题");
            }
        });
        mPgFtitle.setOnRightIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击了右图标");
            }
        });
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}