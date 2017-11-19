package com.fbw.recyclerviewproject.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
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
            int right = child.getRight() + layoutParams.rightMargin + divider.getIntrinsicWidth();
            int bottom =top + divider.getIntrinsicHeight();
            divider.setBounds(left,top,right,bottom);
            divider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {

//        outRect.set(0,0,divider.getIntrinsicWidth(),divider.getIntrinsicHeight());


        /**
         * 以上为止，实现的效果是gridview的最后一列和最后一行也出现了分割线，如果需要把最后一列和
         * 最后一行的分割线也去掉，
         * 就需要做个判断
         */
        int right = divider.getIntrinsicWidth();
        int bottom = divider.getIntrinsicHeight();
         if(isLastColum(itemPosition,parent)){//最后一列
             right = 0;
         }
         if(isLastRow(itemPosition,parent)){//最后一行
             bottom = 0;
         }
        outRect.set(0,0,right,bottom);
    }

    /**
     * 判断是否是最后一列，需要获取grid的spanCount,可以从外部把spanCount传进来，也可以从LayoutManager中获取
     * GridLayoutManager 才能获取到spanCount
     * @param position
     * @param parent
     * @return
     */
    public boolean isLastColum(int position, RecyclerView parent){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            int spanCount =getSpanCount(parent);
            if((position+1)%spanCount==0){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是最后一行
     * @param position
     * @param parent
     * @return
     */
    public boolean isLastRow(int position, RecyclerView parent){
        int spanCount =  getSpanCount(parent);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //有多少列
        if(layoutManager instanceof GridLayoutManager){
            int childCount = parent.getAdapter().getItemCount();
            int lastRowCount = childCount%spanCount;
            //最后一行的数量小于spanCount
            if(lastRowCount==0||lastRowCount<spanCount){
                return true;
            }
        }
        return false;
    }

    private int getSpanCount(RecyclerView parent){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager lm = (GridLayoutManager)layoutManager;
            int spanCount = lm.getSpanCount();
            return spanCount;
        }
        return 0;
    }
}
