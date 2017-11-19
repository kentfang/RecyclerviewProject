package com.fbw.recyclerviewproject.head;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.divider.LinearLayoutManagerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView用法总结：
 * 4：RecylerView 添加头部和尾部，其实是获取学习listview的添加头部和尾部的逻辑，通过代理adapter去实现
 */
public class HeaderRecylerActivity extends AppCompatActivity {
    private WrapRecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_recyler);
        recyclerView =findViewById(R.id.wraprecyclerview);
        TextView headerView = new TextView(this);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        headerView.setLayoutParams(params);
        headerView.setText("我是HeaderView");
        recyclerView.addHeaderView(headerView);

        TextView footerView = new TextView(this);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        footerView.setLayoutParams(params);
        footerView.setText("我是FooterView");
        recyclerView.addFooterView(footerView);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("item "+i);
        }

        MyAdapter adapter = new MyAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new LinearLayoutManagerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }
}
