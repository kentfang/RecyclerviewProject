package com.fbw.recyclerviewproject.galanz.viewpage;

import android.support.v4.view.ViewPager;
import android.view.View;

public class VerticalPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        /**
         *  0 当前界面
         *  -1 前一页
         *  1 后一页
         */
//        if (position >= -1 && position <= 1) {
////            view.setTranslationX(view.getWidth() * -position);
////            float yPosition = position * view.getHeight();
////            view.setTranslationY(yPosition);
////        }

//        view.setScaleX(1-Math.abs(position));
//        view.setScaleY(1-Math.abs(position));


        if (position >= -1 && position <= 1){
            view.setRotationX(position * view.getHeight());
        }
    }
}
