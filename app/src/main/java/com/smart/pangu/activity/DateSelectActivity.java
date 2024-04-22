package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.smart.pangu.R;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.widget.PanguSelectDateView;
import com.smart.pangu_ui_lib.widget.PanguSelectView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :  时间选择框
 *
 * @author jiangzhengyan  2024/4/19 15:51
 */
public class DateSelectActivity extends BaseActivity {


    @Bind(R.id.pg_date)
    PanguSelectDateView mPgDate;
    @Bind(R.id.pg_date2)
    PanguSelectDateView mPgDate2;
    @Bind(R.id.pg_date3)
    PanguSelectDateView mPgDate3;

    public static void start(Context context) {
        Intent intent = new Intent(context, DateSelectActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_date_select;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPgDate.setOnDateSureListener(new PanguSelectDateView.OnDateSureListener() {
            @Override
            public boolean onSureClick(int type, PanguSelectView view, String currentSelectDate, long currentSelectDateMillis) {
                showToast("选中的时间 , "+currentSelectDate);

                //上次选择的时间
                long startTime = mPgDate.getStartTimeMillis();
                long endTime = mPgDate.getEndTimeMillis();
                if (type==0){
                    //开始时间
                    if (currentSelectDateMillis>System.currentTimeMillis()){
                        showToast("请选择当前时间-之前-的时间 ");
                        return true;
                    }
                }
                if (type==1){
                    //结束时间
                    if (currentSelectDateMillis<System.currentTimeMillis()){
                        showToast("请选择当前时间-之后-的时间 ");
                        return true;
                    }
                }
                return false;
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