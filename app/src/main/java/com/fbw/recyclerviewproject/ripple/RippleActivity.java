package com.fbw.recyclerviewproject.ripple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.utils.LogUtil;

public class RippleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);
        RippleView2 rippleView = findViewById(R.id.ripple_view);
        rippleView.setOnRippleCompleteListener(new RippleView2.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView2 rippleView) {
                LogUtil.d("RippleView2","onComplete");
            }
            @Override
            public void progress(RippleView2 rippleView, int time, int total) {
            }
        });
    }
    @Override
    public void onClick(View v) {

    }
}
