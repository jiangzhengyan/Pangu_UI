package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.smart.pangu.R;
import com.smart.pangu.UserUtil;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu_ui_lib.entity.SelectItem;
import com.smart.pangu_ui_lib.widget.PanguFlexBoxView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :  盘古盒子布局
 *
 * @author jiangzhengyan  2024/4/15 10:37
 */
public class PanguFlexboxActivity extends BaseActivity {


    @Bind(R.id.pg_flex_box1)
    PanguFlexBoxView mPgFlexBox1;
    @Bind(R.id.pg_flex_box2)
    PanguFlexBoxView mPgFlexBox2;
    @Bind(R.id.pg_flex_box3)
    PanguFlexBoxView mPgFlexBox3;
    @Bind(R.id.pg_flex_box4)
    PanguFlexBoxView mPgFlexBox4;

    public static void start(Context context) {
        Intent intent = new Intent(context, PanguFlexboxActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_pangu_flexbox;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mPgFlexBox1.setDataText(UserUtil.getSelectItems(), com.smart.pangu_ui_lib.R.layout.item_flex_box_text, new PanguFlexBoxView.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item, int position) {
                showToast("您点击了:position=" + position + ",name=" + item.getName());
            }
        });
        mPgFlexBox2.setDataText(UserUtil.getSelectItems(), new PanguFlexBoxView.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item, int position) {
                showToast("您点击了:position=" + position + ",name=" + item.getName());
            }
        });
        mPgFlexBox3.setDataRb(UserUtil.getSelectItems(), new PanguFlexBoxView.OnItemCheckedListener() {
            @Override
            public void onItemCheck(SelectItem item, int position, boolean currentItemChecked) {
                showToast("当前" + (currentItemChecked ? "选择了" : "取消选择了") + ":position=" + position + ",name=" + item.getName());
            }
        });
        mPgFlexBox4.setDataCb(UserUtil.getSelectItems(), new PanguFlexBoxView.OnItemCheckedListener() {
            @Override
            public void onItemCheck(SelectItem item, int position, boolean currentItemChecked) {
                showToast("当前" + (currentItemChecked ? "选择了" : "取消选择了") + ":position=" + position + ",name=" + item.getName());
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