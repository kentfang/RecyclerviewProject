package com.fbw.recyclerviewproject.manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fbw.recyclerviewproject.DataUtil;
import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.simple.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class LayoutManagerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SimpleAdapter adapter;

    private List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new CustomerLayoutManger());
        adapter = new SimpleAdapter();
        recyclerView.setAdapter(adapter);
        datas = DataUtil.getSimpleData();
        adapter.setDatas(datas);
    }
}
