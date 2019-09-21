package com.fbw.recyclerviewproject.Jzvd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fbw.recyclerviewproject.R;

public class JiaoziPlayActivity extends AppCompatActivity {
    MyJiaozi jiaozi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaozi_play);
        jiaozi=findViewById(R.id.jiaozi);
        jiaozi.setUp("https://b2x-app.s3.cn-northwest-1.amazonaws.com.cn/%E5%B7%A6%E4%B8%AD%E5%A0%82%E9%B8%A1%E8%85%BF.mp4","");
    }
}
