package com.fbw.recyclerviewproject.galanz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.itemhelp.ItemEntity;
import com.fbw.recyclerviewproject.itemhelp.ItemHelpViewHold;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemHelpViewHold> {
    List<ItemEntity> datas = new ArrayList<>();
    ItemClickListener listener;
    private final Context context;

    public ItemAdapter(Context context,ItemClickListener listener){
        this.context = context;
        this.listener = listener;
    }
    @Override
    public ItemHelpViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_touch_hlep,null);
        return new ItemHelpViewHold(view);
    }
    @Override
    public void onBindViewHolder(ItemHelpViewHold holder, int position) {
        holder.tv.setText(datas.get(position).tv);
        int type = datas.get(position).type;
        if (type==0){
            holder.iv.setBackgroundResource(R.mipmap.one);
        }else if(type==1){
            holder.iv.setBackgroundResource(R.mipmap.two);
        }else{
            holder.iv.setBackgroundResource(R.mipmap.thr);
        }
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
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
}
