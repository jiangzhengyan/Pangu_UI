package com.smart.pangu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.smart.pangu.R;
import com.smart.pangu.UserUtil;
import com.smart.pangu.base.BaseActivity;
import com.smart.pangu.item.DemoItemView;
import com.smart.pangu_ui_lib.base.BaseRecyclerViewAdapter;
import com.smart.pangu_ui_lib.entity.SelectItem;
import com.smart.pangu_ui_lib.impl.OnItemClickRecyclerListener;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :  盘古RecyclerAdapter适配器的使用
 *
 * @author jiangzhengyan  2024/4/17 20:02
 */
public class PanguRecyclerAdapterActivity extends BaseActivity {


    @Bind(R.id.pg_recycler)
    RecyclerView mPgRecycler;

    public static void start(Context context) {
        Intent intent = new Intent(context, PanguRecyclerAdapterActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_pangu_recycler_adapter;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //1,准备数据
        List<SelectItem> selectItems = UserUtil.getSelectItems();
        selectItems.addAll(selectItems);
        selectItems.addAll(selectItems);

        //2,创建适配器
        BaseRecyclerViewAdapter<SelectItem> adapter = new BaseRecyclerViewAdapter<SelectItem>(this, selectItems, new BaseRecyclerViewAdapter.Delegate<SelectItem>() {
            @Override
            public View buildView(ViewGroup parent, int viewType) {
                return new DemoItemView(PanguRecyclerAdapterActivity.this);
            }

            @Override
            public void bindViewData(int position, SelectItem data, View view) {

                DemoItemView itemView = (DemoItemView) view;
                //设置数据|做一些操作
                itemView.setData(data);
            }
        });
        //3,可以追加数据,适用于列表数据的分页
        adapter.addList(selectItems, 10);
        //4,设置点击事件
        adapter.setOnItemClickRecyclerListener(new OnItemClickRecyclerListener<SelectItem>() {
            @Override
            public void onItemClick(int position, SelectItem data, View view) {
                showToast("点击了 , 第" + position + "条,name:" + data.getName());
            }
        });
        //5,设置适配器
        mPgRecycler.setLayoutManager(new LinearLayoutManager(this));
        mPgRecycler.setAdapter(adapter);
    }

    @Override
    protected void loadData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}