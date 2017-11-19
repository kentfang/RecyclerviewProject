package com.fbw.recyclerviewproject.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fbw.recyclerviewproject.R;

/**
 * 作者：fbw
 * 邮箱：kent.fang@hotmail.com
 * RecyclerView，网格布局的item分割线
 */

public class GridLayoutManagerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable divider;
    public GridLayoutManagerItemDecoration(Context context){
        divider = context.getResources().getDrawable(R.drawable.item_divider);
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c,parent);
        drawVertical(c,parent);
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        //绘制垂直间隔线
        int childCount = parent.getChildCount();
        for (int i = 0 ;i<childCount;i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight()+layoutParams.rightMargin;
            int top = child.getTop() - layoutParams.topMargin;
            int right = left+divider.getIntrinsicWidth();
            int bottom = child.getBottom() + layoutParams.bottomMargin;
            divider.setBounds(left,top,right,bottom);
            divider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        //绘制水平间隔线
        int childCount = parent.getChildCount();
        for (int i = 0 ;i<childCount;i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft()-layoutParams.leftMargin;
            int top = child.getBottom() + layoutParams.bottomMargin;
            //right,多加一个divider的宽度，可以让item的分割线连续，或者在画垂直分割线的bottom多加一个宽度
            int right = child.getRight()+layoutParams.rightMargin+divider.getIntrinsicWidth();
            int bottom =top + divider.getIntrinsicHeight();
            divider.setBounds(left,top,right,bottom);
            divider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {

        outRect.set(0,0,divider.getIntrinsicWidth(),divider.getIntrinsicHeight());
    }
}
