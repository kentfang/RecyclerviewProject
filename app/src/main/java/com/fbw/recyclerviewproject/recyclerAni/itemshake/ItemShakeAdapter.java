package com.fbw.recyclerviewproject.recyclerAni.itemshake;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.fbw.recyclerviewproject.R;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView.Adapter 封装了很多刷新数据的方法
 */
public class ItemShakeAdapter extends RecyclerView.Adapter<ItemShakeAdapter.ItemShakeHold> {

    private List<String> datas = new ArrayList<>();

    private List<ItemShakeHold> holds = new ArrayList<>();

    private Activity activity;
    public ItemShakeAdapter(ItemShakeActivity itemShakeActivity) {
        this.activity = itemShakeActivity;
    }
    @Override
    public ItemShakeHold onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemShakeHold viewHold = new ItemShakeHold(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shake_re,null));
        holds.add(viewHold);
        return viewHold;
    }
    @Override
    public void onBindViewHolder(ItemShakeHold holder, int position) {
        holder.iv.setImageResource(R.mipmap.one);
        showFoodItemInitAnim(holder.itemView,position);
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
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



     class ItemShakeHold extends RecyclerView.ViewHolder {
        public ImageView iv;
        public ItemShakeHold(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item_shake_iv);
        }
    }

    private void showFoodItemInitAnim(View item) {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(item, scaleX, scaleY);
        objectAnimator.setDuration(1000).start();
    }
    private void showFoodItemInitAnim(View item,int position) {
        Message message = Message.obtain();
        message.obj = item;
        handler.sendMessageDelayed(message,position*50);
    }
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            showFoodItemInitAnim((View) msg.obj);
        }
    };

//    public void showAni(){
//        for (ItemShakeHold hold:holds){
//            showFoodItemInitAnim(hold.itemView);
//        }
//    }
}
