package com.smart.pangu_ui_lib.widget.wheelview.adapter;

import java.util.List;

/**
 *  本类的主要功能是 :  wheel的baseadapter 使用时直接new对象
 *
 * @author  jiangzhengyan  2024/4/10 9:58
 *
 */
public class BaseWheelAdapter<T> implements WheelAdapter {

    private List<T> items;
    public BaseWheelAdapter(List<T> items) {
        this.items = items;
    }
    @Override
    public T getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return  null;
    }

    @Override
    public int getItemsCount() {
        return items==null?0:items.size();
    }

    @Override
    public int indexOf(Object o){
        return  items==null?0:items.indexOf(o);
    }
}
