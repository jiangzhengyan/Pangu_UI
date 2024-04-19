package com.smart.pangu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.smart.pangu.activity.FilePathActivity;
import com.smart.pangu.activity.PanguFlexboxActivity;
import com.smart.pangu.activity.PanguInputViewActivity;
import com.smart.pangu.activity.PanguNavActivity;
import com.smart.pangu.activity.PanguPopActivity;
import com.smart.pangu.activity.PanguRecyclerAdapterActivity;
import com.smart.pangu.activity.PanguSelectViewActivity;
import com.smart.pangu.activity.WheelViewActivity;
import com.smart.pangu.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {


    @Bind(R.id.ll_item)
    LinearLayout mLlItem;
    private String[] mainItemArr = {
            "盘古输入框"
            , "盘古导航栏"
            , "盘古选择栏"
            , "盘古basePop弹窗"
            , "滚轮控件-WheelView"
            , "盘古flexbox盒子布局"
            , "盘古RecyclerAdapter适配器的使用"
            , "文件存储filepathutil"
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

        for (String arr : mainItemArr) {

            View itemView = View.inflate(this, R.layout.main_item_view, null);
            Button btn = itemView.findViewById(R.id.btn);
            btn.setText(arr);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (arr.equals(mainItemArr[0])) {
                        //盘古输入框
                        PanguInputViewActivity.start(MainActivity.this);
                    }
                    if (arr.equals(mainItemArr[1])) {
                        //盘古导航栏
                        PanguNavActivity.start(MainActivity.this);
                    }
                    if (arr.equals(mainItemArr[2])) {
                        //盘古选择栏
                        PanguSelectViewActivity.start(MainActivity.this);
                    }
                    if (arr.equals(mainItemArr[3])) {
                        //盘古basePop弹窗
                        PanguPopActivity.start(MainActivity.this);
                    }
                    if (arr.equals(mainItemArr[4])) {
                        //滚动布局,轮子滚动控件
                        WheelViewActivity.start(MainActivity.this);
                    }
                    if (arr.equals(mainItemArr[5])) {
                        //盘古flexbox盒子布局
                        PanguFlexboxActivity.start(MainActivity.this);
                    }

                    if (arr.equals(mainItemArr[6])) {
                        //盘古RecyclerAdapter适配器的使用
                        PanguRecyclerAdapterActivity.start(MainActivity.this);
                    }
                    if (arr.equals(mainItemArr[7])) {
                        //文件存储filepathutil
                        FilePathActivity.start(MainActivity.this);
                    }


                }
            });

            mLlItem.addView(itemView);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}