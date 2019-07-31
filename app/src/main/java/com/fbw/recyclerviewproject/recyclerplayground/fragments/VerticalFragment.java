package com.fbw.recyclerviewproject.recyclerplayground.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fbw.recyclerviewproject.recyclerplayground.DividerDecoration;
import com.fbw.recyclerviewproject.recyclerplayground.adapters.SimpleAdapter;


public class VerticalFragment extends RecyclerFragment {

    public static VerticalFragment newInstance() {
        VerticalFragment fragment = new VerticalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        //We must draw dividers ourselves if we want them in a list
        return new DividerDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount() {
        return 100;
    }

    @Override
    protected SimpleAdapter getAdapter() {
        return new SimpleAdapter();
    }
}
