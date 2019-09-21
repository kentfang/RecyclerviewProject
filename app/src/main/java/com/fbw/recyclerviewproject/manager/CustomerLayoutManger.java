package com.fbw.recyclerviewproject.manager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CustomerLayoutManger extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        int offsetY = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View scrap = recycler.getViewForPosition(i);
            addView(scrap);
            measureChildWithMargins(scrap, 0, 0);
            int perItemWidth = getDecoratedMeasuredWidth(scrap);
            int perItemHeight = getDecoratedMeasuredHeight(scrap);
            layoutDecorated(scrap, 0, offsetY, perItemWidth, offsetY + perItemHeight);
            offsetY += perItemHeight;
        }
    }
    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        offsetChildrenVertical(-dy);
        return super.scrollVerticallyBy(dy,recycler,state);
    }
}
