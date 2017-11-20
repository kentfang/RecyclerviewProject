package com.fbw.recyclerviewproject.itemhelp;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewDebug;

import com.fbw.recyclerviewproject.R;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_IDLE;
import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;


public class ItemHelpCallBack extends ItemTouchHelper.Callback {

    private ItemMoveListner listner;
    public ItemHelpCallBack(ItemMoveListner listner){
        this.listner = listner;
    }
    /**
     * callback 回调监听之前回调的，用来判断当前是什么动作，判断方向
     * 我要监听哪个方向的拖动
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //上下交换位置
        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        //左右删除
        int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        int flag = makeMovementFlags(dragFlags,swipeFlags);
        return flag;
    }

    /**
     * 是否长按拖拽
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * 移动的时候 回调这个方法
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //如果item，的类型不一样，就不换了
        if(viewHolder.getItemViewType()!=target.getItemViewType()){
            return false;
        }
        //在拖拽过程中，需要不停的调用adapter.notifyItemMoved(int from , int to);方法去交换两个viewhold的位置，同时数据也需要交换位置
        if(this.listner!=null){
            listner.OnItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        }
        return true;
    }

    /**
     * 拖拽的时候回调这个方法
     * @param viewHolder 当前的viewholder
     * @param direction 方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //监听侧滑，调用 adapter.notifyItemRemoved(position);
        if(this.listner!=null){
            listner.OnItemswape(viewHolder.getAdapterPosition(),direction);
        }
    }

    /**
     * item 状态发生改变的时候回调,也就是说当item在被拖拽的时候，可以让item的背景发生改变
     * @param viewHolder
     * @param actionState One of {@link ItemTouchHelper#ACTION_STATE_IDLE},
     *                    {@link ItemTouchHelper#ACTION_STATE_SWIPE} or
     *                    {@link ItemTouchHelper#ACTION_STATE_DRAG}.
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState != ACTION_STATE_IDLE){
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.colorAccent));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * Called by the ItemTouchHelper when the user interaction with an element is over and it
     * also completed its animation. 动画结束的时候时候调用
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(android.R.color.white));

        //修复左右滑动删除item之后，出现有不显示的item的bug,主要原因是 因为RecyclerView的view复用,
        //因为在onChildDraw中 对view的属性进行了改变，需要在结束的时候，重新对属性重新恢复到原来的状态
        viewHolder.itemView.setAlpha(1);
        viewHolder.itemView.setScaleX(1);
        viewHolder.itemView.setScaleY(1);
        super.clearView(recyclerView, viewHolder);
    }

    /**
     * 重绘item，在拖拽过程中，如果需要对这个item 做某种特效
     * * Called by ItemTouchHelper on RecyclerView's onDraw callback.
     * <p>
     * If you would like to customize how your View's respond to user interactions, this is
     * a good place to override.
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX X轴方向的偏移量，也就是水平方向的偏移量,有正负，如果为负数，为左，如果正，为右。范围是从0到item的宽度
     * @param dY
     * @param actionState   ACTION_STATE_DRAG   ACTION_STATE_SWIPE
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        int d =0;
        if(actionState == ACTION_STATE_SWIPE){
            //d 是需要从1到0
            d = (int) (1 - Math.abs(dX)/viewHolder.itemView.getWidth());
            viewHolder.itemView.setAlpha(d);
            viewHolder.itemView.setScaleX(d);
            viewHolder.itemView.setScaleY(d);
        }
        //修复左右滑动删除item之后，出现有不显示的item的bug
//        if(d == 0){
//            viewHolder.itemView.setAlpha(1);
//            viewHolder.itemView.setScaleX(1);
//            viewHolder.itemView.setScaleY(1);
//        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
