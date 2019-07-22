package com.fbw.recyclerviewproject.recyclerAni.itemshake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
public class ItemShakeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ItemShakeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_shake);
        recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new ItemShakeAnimation());
        recyclerView.addItemDecoration(new GridLayoutManagerItemDecoration(this));
        adapter = new ItemShakeAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setDatas(DataUtil.getSimpleData());
//        adapter.showAni();
    }
}
