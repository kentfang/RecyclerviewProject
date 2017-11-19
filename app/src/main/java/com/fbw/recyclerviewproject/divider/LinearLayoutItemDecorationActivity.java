package com.fbw.recyclerviewproject.divider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fbw.recyclerviewproject.DataUtil;
import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.simple.SimpleAdapter;



/**
 * RecyclerView用法总结：
 * 2，绘制item的分割线 RecyclerView.ItemDecoration 的使用
 */
public class LinearLayoutItemDecorationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divider);
        recyclerView = findViewById(R.id.recyclerview);

        /**
         * 垂直方向显示
         */
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        recyclerView.addItemDecoration(new LinearLayoutManagerItemDecoration(this,LinearLayoutManager.VERTICAL));

        /**
         * 水平方向item显示
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new LinearLayoutManagerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SimpleAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setDatas(DataUtil.getSimpleData());
    }
}
