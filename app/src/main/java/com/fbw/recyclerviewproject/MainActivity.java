package com.fbw.recyclerviewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public MainActivity(){
        Log.d("fbw","MainActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("fbw","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }
//测试
}
