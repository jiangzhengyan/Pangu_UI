package com.smart.pangu_ui_lib.widget.wheelview.timer;


import com.smart.pangu_ui_lib.widget.wheelview.view.WheelView;

import java.util.TimerTask;

/**
 *  本类的主要功能是 :  滚动惯性的实现
 *
 * @author  jiangzhengyan  2024/4/10 9:55
 *
 */
public final class InertiaTimerTask extends TimerTask {

    private float mCurrentVelocityY; //当前滑动速度
    private final float mFirstVelocityY;//手指离开屏幕时的初始速度
    private final WheelView mWheelView;

    /**
     * @param wheelView 滚轮对象
     * @param velocityY Y轴滑行速度
     */
    public InertiaTimerTask(WheelView wheelView, float velocityY) {
        super();
        this.mWheelView = wheelView;
        this.mFirstVelocityY = velocityY;
        mCurrentVelocityY = Integer.MAX_VALUE;
    }

    @Override
    public final void run() {

        if (mCurrentVelocityY == Integer.MAX_VALUE) {
            if (Math.abs(mFirstVelocityY) > 2000F) {
                mCurrentVelocityY = mFirstVelocityY > 0 ? 2000F : -2000F;
            } else {
                mCurrentVelocityY = mFirstVelocityY;
            }
        }

        if (Math.abs(mCurrentVelocityY) >= 0.0F && Math.abs(mCurrentVelocityY) <= 20F) {
            mWheelView.cancelFuture();
            mWheelView.getHandler().sendEmptyMessage(MessageHandler.WHAT_SMOOTH_SCROLL);
            return;
        }

        int dy = (int) (mCurrentVelocityY / 100F);
        mWheelView.setTotalScrollY(mWheelView.getTotalScrollY() - dy);
        if (!mWheelView.isLoop()) {
            float itemHeight = mWheelView.getItemHeight();
            float top = (-mWheelView.getInitPosition()) * itemHeight;
            float bottom = (mWheelView.getItemsCount() - 1 - mWheelView.getInitPosition()) * itemHeight;
            if (mWheelView.getTotalScrollY() - itemHeight * 0.25 < top) {
                top = mWheelView.getTotalScrollY() + dy;
            } else if (mWheelView.getTotalScrollY() + itemHeight * 0.25 > bottom) {
                bottom = mWheelView.getTotalScrollY() + dy;
            }

            if (mWheelView.getTotalScrollY() <= top) {
                mCurrentVelocityY = 40F;
                mWheelView.setTotalScrollY((int) top);
            } else if (mWheelView.getTotalScrollY() >= bottom) {
                mWheelView.setTotalScrollY((int) bottom);
                mCurrentVelocityY = -40F;
            }
        }

        if (mCurrentVelocityY < 0.0F) {
            mCurrentVelocityY = mCurrentVelocityY + 20F;
        } else {
            mCurrentVelocityY = mCurrentVelocityY - 20F;
        }
        mWheelView.getHandler().sendEmptyMessage(MessageHandler.WHAT_INVALIDATE_LOOP_VIEW);
    }
}
