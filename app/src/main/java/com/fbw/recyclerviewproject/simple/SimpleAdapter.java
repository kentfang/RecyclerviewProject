package com.fbw.recyclerviewproject.simple;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fbw.recyclerviewproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView.Adapter 封装了很多刷新数据的方法
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHold> {

    private List<String> datas = new ArrayList<>();
    @Override
    public SimpleViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        SimpleViewHold viewHold = new SimpleViewHold(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,null));
        return viewHold;
    }

    @Override
    public void onBindViewHolder(SimpleViewHold holder, int position) {
        holder.itemView.setBackgroundResource(R.color.colorAccent);
        holder.tv.setText(datas.get(position));
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    /**
     * 设置数据
     * @param datas
     */
    public void setDatas(List<String> datas){
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * notifyItemInserted 第几条item上插入一条数据刷新
     * @param string
     */
    public void addOneData(String string){
        this.datas.add(5,string);
        notifyItemInserted(5);
    }

    public void remove(int position){
        this.datas.remove(position);
        notifyItemRemoved(position);
    }

}
