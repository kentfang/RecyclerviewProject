package com.fbw.recyclerviewproject.AutoPoll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;

import com.fbw.recyclerviewproject.R;

public class AutoPollMainActivity extends AppCompatActivity {
    private AutoPollRecyclerView Aprv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.auto_pull_activity_main );
        initView();
    }

    private void initView() {
        Aprv= (AutoPollRecyclerView) findViewById(R.id.rv);
        AutoPollAdapter adapter = new AutoPollAdapter();
        Aprv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Aprv.setAdapter(adapter);
        Aprv.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != Aprv){
            Aprv.stop();
        }
    }
}
