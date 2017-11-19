package com.fbw.recyclerviewproject.itemhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.fbw.recyclerviewproject.DataUtil;
import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.divider.LinearLayoutManagerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView用法总结：
 * 5：RecylerView 通过ItemTouchHelp实现item的拖拽效果
 */
public class ItemTouchHelpActivity extends AppCompatActivity implements StartDragListner {

    private RecyclerView recyclerView;
    private ItemHelpAdapter adapter;

    private ItemTouchHelper helper;
    List<ItemEntity> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_help);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemHelpAdapter(this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new LinearLayoutManagerItemDecoration(this,LinearLayoutManager.VERTICAL));
        helper = new ItemTouchHelper(new ItemHelpCallBack(adapter));
        helper.attachToRecyclerView(recyclerView);
        adapter.setDatas(DataUtil.getItemEntuty());


    }

    @Override
    public void onStartDragListner(RecyclerView.ViewHolder viewHolder) {
        helper.startDrag(viewHolder);
    }
}
