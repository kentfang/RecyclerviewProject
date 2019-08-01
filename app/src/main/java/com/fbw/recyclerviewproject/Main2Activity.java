package com.fbw.recyclerviewproject;

import android.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {


    public Main2Activity(){
        Log.d("fbw","Main2Activity");
    }

    @Override
    public void startLockTask() {
        Log.d("fbw","Main2Activity----------startLockTask");
        super.startLockTask();
    }

    @Override
    public void stopLockTask() {
        Log.d("fbw","Main2Activity----------stopLockTask");
        super.stopLockTask();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("fbw","Main2Activity----------onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public boolean onNavigateUp() {
        Log.d("fbw","Main2Activity----------onNavigateUp");
        return super.onNavigateUp();
    }

    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        Log.d("fbw","Main2Activity----------onCreateNavigateUpTaskStack");
        super.onCreateNavigateUpTaskStack(builder);
    }
}
