package com.smart.pangu_ui_lib.widget.wheelview.listener;

import android.view.MotionEvent;

import com.smart.pangu_ui_lib.widget.wheelview.view.WheelView;


/**
 *  本类的主要功能是 :   手势监听
 *
 * @author  jiangzhengyan  2024/4/10 9:54
 *
 */
public final class WheelViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    private final WheelView wheelView;


    public WheelViewGestureListener(WheelView wheelView) {
        this.wheelView = wheelView;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        wheelView.scrollBy(velocityY);
        return true;
    }
}
