package com.fbw.recyclerviewproject.itemhelp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fbw.recyclerviewproject.R;


public class ItemHelpViewHold extends RecyclerView.ViewHolder {
    public TextView tv;
    public ImageView iv;
    public ItemHelpViewHold(View itemView) {
        super(itemView);
        this.tv = itemView.findViewById(R.id.tv);
        this.iv = itemView.findViewById(R.id.iv);
    }
}
