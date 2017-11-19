package com.fbw.recyclerviewproject.simple;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fbw.recyclerviewproject.R;


public class SimpleViewHold extends RecyclerView.ViewHolder {
    public TextView tv;
    public SimpleViewHold(View itemView) {
        super(itemView);
        tv = itemView.findViewById(android.R.id.text1);
    }
}
