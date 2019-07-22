package com.fbw.recyclerviewproject.circleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.fbw.recyclerviewproject.R;

public class CircleViewActivity extends AppCompatActivity {
    CircleView pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);
        pr = findViewById(R.id.am_progressbar_one);
        pr.setInterpolator(new AccelerateDecelerateInterpolator());
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr.start();
            }
        });

        pr.setProgressCallBack(new CircleView.OnCircleViewProgressCallBack() {
            @Override
            public void onCircleViewProgress(float progress) {
            }
        });
    }
}
