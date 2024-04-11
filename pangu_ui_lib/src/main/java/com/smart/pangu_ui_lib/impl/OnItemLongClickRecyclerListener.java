package com.smart.pangu_ui_lib.impl;

import android.view.View;

/**
 *  本类的主要功能是 :  RecyclerView 长按回调
 *
 * @author  jiangzhengyan  2024/4/10 10:14
 *
 */
public interface OnItemLongClickRecyclerListener<T> {

    boolean onItemLongClick(int position, T data, View view);
}