package com.lqr.topsales;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @创建者 CSDN_LQR
 * @描述 处理被HorizontalScrollView嵌套时，手指快速上下滚动事件被抢先处理问题
 */
public class MyRecyclerView extends RecyclerView {

    private float mDownX;
    private float mDownY;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //事件分发
    //dispatchTouchEvent->onInterceptTouchEvent->onTouchEvent
    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = e.getRawX();
                mDownY = e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float rawX = e.getRawX();
                float rawY = e.getRawY();
                float dx = Math.abs(mDownX - rawX);
                float dy = Math.abs(mDownY - rawY);
                if (dy > 5 && dx > 5 && dx > dy) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    //请求所有父控件及祖宗控件不要拦截事件
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.dispatchTouchEvent(e);
    }

}
