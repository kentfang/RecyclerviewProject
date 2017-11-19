package com.fbw.recyclerviewproject.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import com.fbw.recyclerviewproject.DataUtil;
import com.fbw.recyclerviewproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView用法总结：
 * 1，常用的简单用法
 */
public class RecyclerActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;

    private SimpleAdapter adapter;

    private List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.recyclerview);
        /**
         * new LinearLayoutManager(context),只传一个参数，默认表示垂直显示，数据不反转
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        /**
         * GridLayoutManager:spanCount表示一行显示的item个数
         */
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        /**
         * StaggeredGridLayoutManager 类似瀑布流效果，如果存在item的大小不一样，可以使用
         * StaggeredGridLayoutManager.VERTICAL 垂直分布,spanCount==3   显示效果是三列垂直分布
         * StaggeredGridLayoutManager.HORIZONTAL 水平分布，spanCount==3   显示效果是三行，水平分布
         */
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
        adapter = new SimpleAdapter();
        recyclerView.setAdapter(adapter);
        findViewById(R.id.add_all).setOnClickListener(this);
        findViewById(R.id.add_one).setOnClickListener(this);
        findViewById(R.id.sub_one).setOnClickListener(this);
    }

    public void addAll(View view) {
        datas = DataUtil.getSimpleData();
        adapter.setDatas(datas);
    }

    public void addOne(View view) {
        if (datas.size()>5){
            datas.add(5,"插入数据");
            adapter.addOneData(datas.get(5));
        }
    }

    public void subOne(View view) {
        if (datas.size()>5){
            datas.remove(5);
            adapter.remove(5);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.add_all:
                addAll(view);
                break;
            case R.id.add_one:
                addOne(view);
                break;
            case R.id.sub_one:
                subOne(view);
                break;
        }
    }
}
