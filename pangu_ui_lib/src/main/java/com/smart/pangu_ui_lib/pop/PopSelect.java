package com.smart.pangu_ui_lib.pop;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.BasePop;
import com.smart.pangu_ui_lib.entity.SelectItem;
import com.smart.pangu_ui_lib.widget.wheelview.adapter.BaseWheelAdapter;
import com.smart.pangu_ui_lib.widget.wheelview.listener.OnItemSelectedListener;
import com.smart.pangu_ui_lib.widget.wheelview.view.WheelView;

import java.util.List;


/**
 * 本类的主要功能是 :  选择框
 *
 * @author jiangzhengyan  2024/4/10 9:49
 */
public class PopSelect extends BasePop {
    private TextView mTvCancel;
    private TextView mTvSure;
    private WheelView mWvYear;
    private LinearLayout mLlContainer;


    private SelectItem selectItem;


    public PopSelect(Context context) {
        super(context);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pop_select;
    }

    @Override
    public void initData(View layout, Context context) {
        mTvCancel = layout.findViewById(R.id.tv_cancel);
        mTvSure = layout.findViewById(R.id.tv_sure);
        mWvYear = layout.findViewById(R.id.wv_year);
        mLlContainer = layout.findViewById(R.id.ll_container);
        //动画
        setAnimaType(AnimaType.BOTTOM_IN_OUT);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onClickSureListener != null) {
                    onClickSureListener.onClick(view);
                }
            }
        });
    }

    /**
     * 设置数据
     *
     * @param items
     */
    public void setData(List<SelectItem> items) {

        this.selectItem = items.get(0);
        mWvYear.setAdapter(new BaseWheelAdapter<SelectItem>(items));
        mWvYear.setCurrentItem(0);
        mWvYear.setCyclic(false);
        mWvYear.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index, Object item) {

                selectItem = (SelectItem) item;

            }

        });
    }

    /**
     * @return
     */
    public SelectItem getSelectItem() {
        return selectItem;
    }


}
