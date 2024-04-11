package com.smart.pangu_ui_lib.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 *  本类的主要功能是 :  recyclerView适配器父级ViewHolder
 *
 * @author  jiangzhengyan  2024/4/10 10:13
 *
 */
public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    //条目布局
    public View itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }
}
