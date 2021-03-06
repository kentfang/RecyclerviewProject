package com.fbw.recyclerviewproject.recyclerplayground.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.fbw.recyclerviewproject.recyclerplayground.InsetDecoration;
import com.fbw.recyclerviewproject.recyclerplayground.adapters.SimpleAdapter;
import com.fbw.recyclerviewproject.recyclerplayground.adapters.SimpleStaggeredAdapter;


public class VerticalStaggeredGridFragment extends RecyclerFragment {

    public static VerticalStaggeredGridFragment newInstance() {
        VerticalStaggeredGridFragment fragment = new VerticalStaggeredGridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new InsetDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount() {
        return 100;
    }

    @Override
    protected SimpleAdapter getAdapter() {
        return new SimpleStaggeredAdapter();
    }
}
