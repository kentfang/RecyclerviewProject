package com.fbw.recyclerviewproject.itemhelp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.fbw.recyclerviewproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ItemHelpAdapter extends RecyclerView.Adapter<ItemHelpViewHold> implements ItemMoveListner {

    private final Context context;

    private StartDragListner listner;
    List<ItemEntity> datas = new ArrayList<>();
    public ItemHelpAdapter(Context context,StartDragListner listner){
        this.context = context;
        this.listner = listner;
    }
    @Override
    public ItemHelpViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_touch_hlep,null);
        return new ItemHelpViewHold(view);
    }
    @Override
    public void onBindViewHolder(final ItemHelpViewHold holder, int position) {
        holder.tv.setText(datas.get(position).tv);
        int type = datas.get(position).type;
        if (type==0){
            holder.iv.setBackgroundResource(R.mipmap.one);
        }else if(type==1){
            holder.iv.setBackgroundResource(R.mipmap.two);
        }else{
            holder.iv.setBackgroundResource(R.mipmap.thr);
        }

        //如果需要触摸iv，就能实现拖拽效果,需要把触摸情况传递给ItemHelpCallBack
        //通过接口回掉的方式把触摸事件传递给ItemTouchHelper 调用 helper.startDrag(viewHolder);
//        helper.startSwipe(viewHolder);
        holder.iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                listner.onStartDragListner(holder);
                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    public void setDatas(List<ItemEntity> datas){
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public void OnItemMove(int from, int to) {
        //数据要发生改变
        Collections.swap(datas,from,to);
        //adapter更换位置
        this.notifyItemMoved(from,to);
    }

    @Override
    public void OnItemswape(int position, int derection) {
        //这里实现的滑动删除
        datas.remove(position);
        this.notifyItemRemoved(position);
    }
}
