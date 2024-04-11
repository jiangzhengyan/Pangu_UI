package com.smart.pangu_ui_lib.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.smart.pangu_ui_lib.impl.OnItemClickRecyclerListener;
import com.smart.pangu_ui_lib.impl.OnItemLongClickRecyclerListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *  本类的主要功能是 :  recyclerView 父类适配器 
 *
 * @author  jiangzhengyan  2024/4/10 10:14
 *
 */
public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {
    //list列表数据
    public List<T> list;
    private Delegate<T> mDelegate;

    public Context mContext;
    //点击事件接口回调
    protected OnItemClickRecyclerListener<T> itemClickRecyclerListener;
    protected OnItemLongClickRecyclerListener<T> itemLongClickRecyclerListener;

    public BaseRecyclerViewAdapter(Context context, List<T> list, Delegate<T> mDelegate) {
        this.mContext = context;
        this.list = list;
        this.mDelegate = mDelegate;
    }

    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.list = list;
    }

    public void setmDelegate(Delegate<T> mDelegate) {
        this.mDelegate = mDelegate;
    }

    /**
     * 绑定布局
     *
     * @param parent
     * @param viewType
     * @return 顶级ViewHolder
     */
    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mDelegate.buildView(parent, viewType);
        return new BaseViewHolder<T>(view);
    }

    /**
     * 绑定ViewHolder数据,处理数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (mDelegate != null) {
            mDelegate.bindViewData(position, list.get(position), holder.itemView);
        }
        // 对条目设置点击事件回调
        holder.itemView.setOnClickListener((v) -> {
            if (itemClickRecyclerListener != null) {
                itemClickRecyclerListener.onItemClick(position, list.get(position), holder.itemView);
            }
        });
        holder.itemView.setOnLongClickListener((v) -> {
            if (itemLongClickRecyclerListener != null) {
                return itemLongClickRecyclerListener.onItemLongClick(position, list.get(position), holder.itemView);
            }
            return false;
        });
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    /**
     * 刷新数据
     *
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     *
     * @param list
     */
    public void addList(List<T> list, int page) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        if (page == 1) {
            this.list.clear();
        } else {
            if (list == null) {
                return;
            }
        }
        this.list.addAll(list);
        //notifyItemRangeChanged(this.list.size() > 1 ? this.list.size() - 2 : 0, list.size());
        notifyDataSetChanged();
    }

    public List<T> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    /**
     * 添加点击事件的接口回调
     *
     * @param itemClickRecyclerListener
     */
    public void setOnItemClickRecyclerListener(OnItemClickRecyclerListener<T> itemClickRecyclerListener) {
        this.itemClickRecyclerListener = itemClickRecyclerListener;
    }

    public void setOnItemLongClickRecyclerListener(OnItemLongClickRecyclerListener<T> itemLongClickRecyclerListener) {
        this.itemLongClickRecyclerListener = itemLongClickRecyclerListener;
    }

    public interface Delegate<T> {
        View buildView(ViewGroup parent, int viewType);

        void bindViewData(int position, T data, View view);
    }
}
