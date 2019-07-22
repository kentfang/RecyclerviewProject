package com.fbw.recyclerviewproject.galanz.viewpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fbw.recyclerviewproject.R;

public class ViewPagerActivity extends AppCompatActivity {

    VerticalViewPager verticalViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        verticalViewPager =findViewById(R.id.viewpager);
        MyVerticaladapter myVerticaladapter = new MyVerticaladapter(this);
        verticalViewPager.setPageTransformer(true,new VerticalPageTransformer());
        verticalViewPager.setAdapter(myVerticaladapter);
    }
}
