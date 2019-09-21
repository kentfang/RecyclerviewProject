package com.fbw.recyclerviewproject.lottie;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.utils.LogUtil;

public class LottieActivity extends AppCompatActivity implements View.OnTouchListener {

    private static int timeout=400;
    private int clickCount = 0;
    private Handler handler;
    private String TAG = "fbw";
    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        FrameLayout textureViewContainer = findViewById(R.id.framlayout);
        textureViewContainer.setOnTouchListener(this);
        handler = new Handler();

        animationView = new LottieAnimationView(this);
        animationView.setAnimation("like.json");
        animationView.loop(false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        textureViewContainer.addView(animationView,layoutParams);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clickCount++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (clickCount == 1) {
                    }else if(clickCount==2){
                        LogUtil.i(TAG,"clickCount==2");
                        animationView.playAnimation();
                    }
                    handler.removeCallbacksAndMessages(null);
                    clickCount = 0;
                }
            },timeout);
        }
        return false;
    }
}
