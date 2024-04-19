package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.smart.pangu.R;
import com.smart.pangu.UserUtil;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.entity.SelectItem;
import com.smart.pangu_ui_lib.util.FilePathUtil;
import com.smart.pangu_ui_lib.widget.wheelview.adapter.BaseWheelAdapter;
import com.smart.pangu_ui_lib.widget.wheelview.listener.OnItemSelectedListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :  文件存储路径工具类FilePathUtil的介绍
 *
 * @author jiangzhengyan  2024/4/19 9:58
 */
public class FilePathActivity extends BaseActivity {


    @Bind(R.id.tv_1)
    TextView mTv1;
    @Bind(R.id.tv_2)
    TextView mTv2;

    public static void start(Context context) {
        Intent intent = new Intent(context, FilePathActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_filepath_util;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mTv1.setText(
                "(1),  \n示例:\n/data/user/0/{包名}/app_/{subPath}\n路径:\n"+ FilePathUtil.getAppIntenalPublicDir(this,"subpath")+"\n\n"+
                "(2),  \n示例:\n/data/user/0/{包名}/files/{subPath}\n路径:\n"+ FilePathUtil.getAppIntenalFilesDir(this,"subpath")+"\n\n"+
                "(3),  \n示例:\n/data/user/0/{包名}/cache\n路径:\n"+ FilePathUtil.getAppIntenalCacheDir(this)
        );
        mTv2.setText(
                "(1), app外,公共存储,可以被外部识别,卸载App不会被清除\n示例:\n/storage/emulated/0/{@link #EXTERNAL_PUBLIC_ROOT_DIR}/{subPath}\n路径:\n"+ FilePathUtil.getAppExternalPublicDir("subpath")+"\n\n"+
                "(2), app内,本app专属目录,不可以被外部识别,卸载app会被清除\n 示例:\n/storage/emulated/0/Android/data/{包名}/files/{subPath}\n路径:\n"+ FilePathUtil.getAppExternalFilesDir(this,"subpath")+"\n\n"+
                "示例:\n/storage/emulated/0/Android/data/{包名}/cache\n路径:\n"+ FilePathUtil.getAppExternalCacheDir(this)
        );
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