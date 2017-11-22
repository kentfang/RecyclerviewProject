package com.fbw.recyclerviewproject.divider;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 设置item之间的 空隙
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    int space = 0;
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(space,space,space,space);
    }
    public void setSpace(int space ){
        this.space = space;
    }
}
