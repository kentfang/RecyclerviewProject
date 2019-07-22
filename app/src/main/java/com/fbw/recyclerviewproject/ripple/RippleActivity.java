package com.fbw.recyclerviewproject.ripple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fbw.recyclerviewproject.R;

public class RippleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        RippleView rippleView = findViewById(R.id.ripple_view);
        rippleView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("fbw", "Click Rect !");
            }
        });
        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Log.d("fbw", "Ripple completed");
            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}
