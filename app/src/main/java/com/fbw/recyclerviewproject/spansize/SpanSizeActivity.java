package com.fbw.recyclerviewproject.spansize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fbw.recyclerviewproject.DataUtil;
import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.divider.GridLayoutManagerItemDecoration;
import com.fbw.recyclerviewproject.simple.SimpleAdapter;

/**
 * RecyclerView通过GridLayoutManager设置SpanSize 来显示大小不一样的item
 */
public class SpanSizeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_size);
        recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position%3==0){
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SimpleAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setDatas(DataUtil.getSimpleData());
    }
}
