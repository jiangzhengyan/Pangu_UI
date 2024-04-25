package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smart.pangu.R;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.widget.PanguDrawBoard;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :   手写画板
 *
 * @author jiangzhengyan  2024/4/25 16:15
 */
public class DrawBoardActivity extends BaseActivity {


    @Bind(R.id.pg_draw_board)
    PanguDrawBoard mPgDrawBoard;

    public static void start(Context context) {
        Intent intent = new Intent(context, DrawBoardActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_draw_board;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }

    /**
     * 保存画板
     *
     * @param v
     */
    public void save(View v) {
        String signFile = mPgDrawBoard.getSignFile();
        showToast("保存成功,地址为: "+signFile);
    }

    /**
     * 清空画板
     *
     * @param v
     */
    public void clear(View v) {
        mPgDrawBoard.clean();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}