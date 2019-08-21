package com.fbw.recyclerviewproject;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ImageView  imageView=(ImageView)findViewById(R.id.imageView2);
        ViewCompat.setTransitionName(imageView, "123");
    }
}
