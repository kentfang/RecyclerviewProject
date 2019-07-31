package com.fbw.recyclerviewproject.scrollImage;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.utils.LogUtil;


public class ScrollImageActivity extends AppCompatActivity{

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_image);
        textView = findViewById(R.id.degreeShow);
        ScrollImageView2 scrollImageView =findViewById(R.id.ScrollImageView);
        scrollImageView.setMaxDegree(230f);
        scrollImageView.setMinDegree(60f);
        scrollImageView.setmRotation(60f);
        scrollImageView.setFactor(40);
        LogUtil.d("fbw","Main thread:"+Thread.currentThread().getName());
        scrollImageView.setOnScrollViewDegreeListener(new ScrollImageView2.OnScrollViewDegreeAsyncListener() {
            @Override
            public void onScrollViewDegree(float degree) {
                textView.setText("当前进度："+degree+"");
            }
        });
    }
}
