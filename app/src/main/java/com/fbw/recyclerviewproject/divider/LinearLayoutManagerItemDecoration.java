package com.fbw.recyclerviewproject.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fbw.recyclerviewproject.R;

/**
 * 作者：fbw
 * 邮箱：kent.fang@hotmail.com
 * RecyclerView,线性显示item的下划线
 */

/**
 * RecyclerView.ItemDecoration,绘制item的下划线
 */
public class LinearLayoutManagerItemDecoration extends RecyclerView.ItemDecoration {


    /**
     * 布局，如果没有设定，默认是垂直布局
     */
    private int orientation = LinearLayoutManager.VERTICAL;

    /**
     *  下划线的drawable,也可以采用系统的listview的下划线
     */
    private Drawable divider;

    /**
     * 这是系统的divider
     */
    private int [] attrs = new int[]{
            android.R.attr.listDivider
    };

    public LinearLayoutManagerItemDecoration(Context context, int orientation){
//        divider = context.getResources().getDrawable(R.drawable.item_divider);
        /**
         * 注释部分是获取系统的divier
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        divider = typedArray.getDrawable(0);
        typedArray.recycle();

        setOrientation(orientation);
    }
    /**
     * 设定RecyclerView的是垂直布局还是水平布局
     * @param orientation 必须是LinearLayoutManager.HORIZONTAL或者LinearLayoutManager.VERTICAL
     */
    public void setOrientation(int orientation){
        if(orientation!= LinearLayoutManager.HORIZONTAL&&orientation!= LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("参数错误，必须是HORIZONTAL或者是VERTICAL");
        }
        this.orientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //2，通过getItemOffsets，确定好Rect的位置之后，将Rect绘制到画布中
        if(orientation== LinearLayoutManager.VERTICAL){//垂直方向
            drawVertical(c,parent);
        }else{//水平方向
            drawHorizontal(c,parent);
        }
        super.onDraw(c, parent, state);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        //画垂直线,需要绘制所有item的下划线
        int top = parent.getPaddingLeft();
        int bottom = parent.getHeight()-parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i=0;i<childCount;i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            // left值的确定：item的left+ child自身的rightMargin + 如果child存在左右移动的平移动画，下划线的位置也需要跟着变化
            int left = child.getLeft() + layoutParams.rightMargin +Math.round(ViewCompat.getTranslationX(child));
            int right = left+divider.getIntrinsicWidth();
            divider.setBounds(left,top,right,bottom);
            divider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        //画水平线,需要绘制所有item的下划线
        int left = parent.getPaddingLeft();
        int right = parent.getWidth()-parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i=0;i<childCount;i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            // top值的确定：item的bottom + child自身的bottomMargin + 如果child存在上下移动的平移动画，下划线的位置也需要跟着变化
            int top = child.getBottom() + layoutParams.bottomMargin +Math.round(ViewCompat.getTranslationY(child));
            int bottom = top+divider.getIntrinsicHeight();
            divider.setBounds(left,top,right,bottom);
            divider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //1，获取item之间的偏移量，每一个item的 都会回调这个方法
        //通过Rect 来确定item之间的间隙，
        //具体Rect的宽高，以及定义Rect所在 位置，由我们自定义
        if(orientation== LinearLayoutManager.VERTICAL){//垂直方向
//            outRect.set(0,0,长度，高度);因为长度可以在绘制的过程中拉伸，所以这里的长度可以为0
            outRect.set(0,0,0,divider.getIntrinsicHeight());
        }else{//水平方向
//            outRect.set(0,0,长度，高度);因为长度可以在绘制的过程中拉伸，所以这里的高度可以为0
            outRect.set(0,0,divider.getIntrinsicWidth(),0);
        }
    }
}
