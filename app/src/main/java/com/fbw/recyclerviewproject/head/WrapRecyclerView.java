package com.fbw.recyclerviewproject.head;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView.FixedViewInfo;

/**
 * 继承RecyclerView，实现添加头部和尾部
 */
public class WrapRecyclerView extends RecyclerView{
	private ArrayList<View> mHeaderViewInfos = new ArrayList<View>();
	private ArrayList<View> mFooterViewInfos = new ArrayList<View>();
	private Adapter mAdapter;

	public WrapRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
    public void addHeaderView(View v) {
        mHeaderViewInfos.add(v);

        // Wrap the adapter if it wasn't already wrapped.
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }
    
    public void addFooterView(View v) {
        mFooterViewInfos.add(v);

        // Wrap the adapter if it wasn't already wrapped.
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }

    /**
     * 重写setAdapter,如果存在头部和尾部，新建一个HeaderViewRecyclerAdapter处理，仿照listview添加头部
     * 和尾部的操作
     * @param adapter
     */
    @Override
    public void setAdapter(Adapter adapter) {
    	if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0) {
            mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }
    	super.setAdapter(mAdapter);
    }

}
