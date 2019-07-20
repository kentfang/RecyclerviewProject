package com.fbw.recyclerviewproject.circleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fbw.recyclerviewproject.R;

public class CircleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);
        CircleView pr = findViewById(R.id.am_progressbar_one);
        pr.setProgress(100);
    }
}
