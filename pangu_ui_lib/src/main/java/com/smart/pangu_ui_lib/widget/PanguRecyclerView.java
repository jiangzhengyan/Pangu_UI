package com.smart.pangu_ui_lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 *  本类的主要功能是 :  解决scrollView嵌套RecyclerView,导致RecyclerView条目显示不全问题
 *   (慎用此类,对数据量过大的列表会出现严重卡顿)
 *
 * @author  jiangzhengyan  2024/4/10 10:07
 *
 */
public class PanguRecyclerView extends RecyclerView {
    public PanguRecyclerView(Context context) {
        super(context);
    }

    public PanguRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //限制RecyclerView自身的滑动，页面滑动仅依靠ScrollView实现
        setHasFixedSize(true);
        setNestedScrollingEnabled(false);
    }

    public PanguRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
