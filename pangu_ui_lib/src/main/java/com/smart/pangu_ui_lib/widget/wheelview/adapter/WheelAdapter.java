package com.smart.pangu_ui_lib.widget.wheelview.adapter;


public interface WheelAdapter<T> {

	int getItemsCount();

	T getItem(int index);

	int indexOf(T o);
}
