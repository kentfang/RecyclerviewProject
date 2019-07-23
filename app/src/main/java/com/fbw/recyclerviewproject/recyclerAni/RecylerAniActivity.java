package com.fbw.recyclerviewproject.recyclerAni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fbw.recyclerviewproject.DataUtil;
import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.simple.SimpleAdapter;

public class RecylerAniActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ItemAniAdapter aniAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_ani);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.remove).setOnClickListener(this);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        aniAdapter = new ItemAniAdapter(this);
        recyclerView.setAdapter(aniAdapter);
        recyclerView.setItemAnimator(new RecylerItemAni());
        aniAdapter.setDatas(DataUtil.getItemEntuty());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remove:
                aniAdapter.remove(2,3);
                break;
            case R.id.add:
                aniAdapter.setDatas(DataUtil.getItemEntuty());
                break;
        }
    }
}
