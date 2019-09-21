package com.fbw.recyclerviewproject.popup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fbw.recyclerviewproject.R;

public class PopUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            StatusPopUpView.showSuccess(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
