package com.smart.pangu_ui_lib.impl;

import android.view.View;

/**
 *  本类的主要功能是 :  RecyclerView 点击回调
 *
 * @author  jiangzhengyan  2024/4/10 10:14
 *
 */
public interface OnItemClickRecyclerListener<T> {

    void onItemClick(int position, T data, View view);
}