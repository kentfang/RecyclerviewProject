package com.fbw.recyclerviewproject.recyclerAni;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.itemhelp.ItemEntity;
import com.fbw.recyclerviewproject.itemhelp.ItemHelpViewHold;
import com.fbw.recyclerviewproject.itemhelp.ItemMoveListner;
import com.fbw.recyclerviewproject.itemhelp.StartDragListner;
import com.fbw.recyclerviewproject.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ItemAniAdapter extends RecyclerView.Adapter<ItemHelpViewHold> {

    private final Context context;

    List<ItemEntity> datas = new ArrayList<>();
    public ItemAniAdapter(Context context){
        this.context = context;
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

        LogUtil.d("fbw","position:"+position);
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
    public void remove(int position){
        this.datas.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(int... position){
        List<ItemEntity> entities = new ArrayList<>();
        for (int p:position){
            notifyItemRemoved(p);
            entities.add(this.datas.get(p));
        }
        Iterator<ItemEntity> iterator = this.datas.iterator();
        while (iterator.hasNext()){
            ItemEntity s = iterator.next();
            if (entities.contains(s)){
                iterator.remove();
            }
        }
    }
}
