package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.smart.pangu.R;
import com.smart.pangu.UserUtil;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.entity.SelectItem;
import com.smart.pangu_ui_lib.widget.wheelview.adapter.BaseWheelAdapter;
import com.smart.pangu_ui_lib.widget.wheelview.listener.OnItemSelectedListener;
import com.smart.pangu_ui_lib.widget.wheelview.view.WheelView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :  滚轮滑动控件
 *
 * @author jiangzhengyan  2024/4/12 14:35
 */
public class WheelViewActivity extends BaseActivity {


    @Bind(R.id.wv)
    WheelView mWv;
    @Bind(R.id.wv2)
    WheelView mWv2;

    public static void start(Context context) {
        Intent intent = new Intent(context, WheelViewActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_wheel_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mWv.setAdapter(new BaseWheelAdapter<SelectItem>(UserUtil.getSelectItems()));
        mWv.setCurrentItem(0);//默认停留的选项
        mWv.setCyclic(true);//是否循环
        mWv.setOnItemSelectedListener(new OnItemSelectedListener<SelectItem>() {
            @Override
            public void onItemSelected(int index, SelectItem item) {
                showToast("当前位置 , " + item.getName());
            }
        });
        mWv2.setAdapter(new BaseWheelAdapter<SelectItem>(UserUtil.getSelectItems()));
        mWv2.setCurrentItem(0);//默认停留的选项
        mWv2.setCyclic(false);//是否循环
        mWv2.setOnItemSelectedListener(new OnItemSelectedListener<SelectItem>() {
            @Override
            public void onItemSelected(int index, SelectItem item) {
                showToast("当前位置 , " + item.getName());
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