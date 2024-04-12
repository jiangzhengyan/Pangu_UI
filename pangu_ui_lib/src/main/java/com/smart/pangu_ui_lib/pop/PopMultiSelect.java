package com.smart.pangu_ui_lib.pop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.PanguBasePop;
import com.smart.pangu_ui_lib.base.BaseRecyclerViewAdapter;
import com.smart.pangu_ui_lib.entity.SelectItem;
import com.smart.pangu_ui_lib.impl.OnItemClickRecyclerListener;
import com.smart.pangu_ui_lib.itemview.SelectMultiItemView;
import com.smart.pangu_ui_lib.widget.PanguRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;


/**
 * 本类的主要功能是 :  多选择弹窗框
 *
 * @author jiangzhengyan  2024/4/10 10:12
 */
public class PopMultiSelect extends PanguBasePop {


    private TextView mTvCancel;
    private TextView mTvTitle;
    private TextView mTvSure;
    private PanguRecyclerView mRecycleItem;



    public PopMultiSelect(Context context) {
        super(context);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pop_multi_select;
    }

    @Override
    public void initData(View layout, Context context) {
        mTvCancel = layout.findViewById(R.id.tv_cancel);
        mTvTitle = layout.findViewById(R.id.tv_title);
        mTvSure = layout.findViewById(R.id.tv_sure);
        mRecycleItem = layout.findViewById(R.id.recycle_item);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onClickSureListener != null) {
                    onClickSureListener.onClick(v);
                }
            }
        });
        //动画
        setAnimaType(AnimaType.BOTTOM_IN_OUT);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    private List<SelectItem> dataList = new ArrayList<>();

    /**
     * 设置数据
     *
     * @param dataList
     */
    public void setData(List<SelectItem> dataList) {
        this.dataList = dataList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecycleItem.setLayoutManager(linearLayoutManager);
        BaseRecyclerViewAdapter<SelectItem> adapter = new BaseRecyclerViewAdapter<>(getContext(), dataList, new BaseRecyclerViewAdapter.Delegate<SelectItem>() {
            @Override
            public View buildView(ViewGroup parent, int viewType) {
                return new SelectMultiItemView(getContext());
            }

            @Override
            public void bindViewData(int position, SelectItem data, View view) {
                ((SelectMultiItemView) view).setData(data);
            }
        });
        mRecycleItem.setAdapter(adapter);
        adapter.setOnItemClickRecyclerListener(new OnItemClickRecyclerListener<SelectItem>() {
            @Override
            public void onItemClick(int position, SelectItem data, View view) {
                boolean select = data.isSelect();
                data.setSelect(!select);
                adapter.notifyDataSetChanged();
            }
        });
    }


    /**
     * @return 返回选中数据
     */
    public List<SelectItem> getSelectedDatas() {
        List<SelectItem> selectedDataList = new ArrayList<>();
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        for (int i = 0; i < dataList.size(); i++) {
            SelectItem selectItem = dataList.get(i);
            if (selectItem.isSelect()) {
                selectedDataList.add(selectItem);
            }
        }
        return selectedDataList;
    }


}
