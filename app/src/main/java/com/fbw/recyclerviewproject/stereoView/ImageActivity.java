package com.fbw.recyclerviewproject.stereoView;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fbw.recyclerviewproject.R;

/**
 * Created by Mr_immortalZ on 2016/7/15.
 * email : mr_immortalz@qq.com
 */
public class ImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

//        Instrumentation inst = new Instrumentation();
//        long dowTime = SystemClock.uptimeMillis();
//        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
//                MotionEvent.ACTION_DOWN, x, y,0));
//        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
//                MotionEvent.ACTION_MOVE, x, y,0));
//        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+20,
//                MotionEvent.ACTION_MOVE, x+20, y,0));
//        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+30,
//                MotionEvent.ACTION_ MOVE, x+40, y,0));
//        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+40,
//                MotionEvent.ACTION_ MOVE, x+60, y,0));
//        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+40,
//                MotionEvent.ACTION_UP, x+60, y,0));
    }
}
