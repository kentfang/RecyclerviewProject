package com.fbw.recyclerviewproject.galanz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.fbw.recyclerviewproject.R;

public class GalanzActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galanz);
        recyclerView = findViewById(R.id.rv);
    }
}
