package com.fbw.recyclerviewproject.recyclerAni.itemshake;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;

public class ItemShakeAnimation extends SimpleItemAnimator {
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        Log.e("fbw","animateRemove");
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        Log.e("fbw","animateAdd");
        return false;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        Log.e("fbw","animateMove");
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        Log.e("fbw","animateChange");
        return false;
    }

    @Override
    public void runPendingAnimations() {
        Log.e("fbw","runPendingAnimations");
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        Log.e("fbw","endAnimation");
    }

    @Override
    public void endAnimations() {
        Log.e("fbw","endAnimations");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
