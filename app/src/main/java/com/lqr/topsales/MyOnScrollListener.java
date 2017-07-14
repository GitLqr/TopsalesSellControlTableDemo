package com.lqr.topsales;

import android.support.v7.widget.RecyclerView;

/**
 * @创建者 CSDN_LQR
 * @描述 实现一个RecyclerView.OnScrollListener的子类，当RecyclerView空闲时取消自身的滚动监听
 */
public class MyOnScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == recyclerView.SCROLL_STATE_IDLE) {
            recyclerView.removeOnScrollListener(this);
        }
    }
}
