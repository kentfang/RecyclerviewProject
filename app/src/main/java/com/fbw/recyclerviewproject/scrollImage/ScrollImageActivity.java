package com.fbw.recyclerviewproject.scrollImage;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ImageView;

import com.fbw.recyclerviewproject.R;

public class ScrollImageActivity extends AppCompatActivity{

    private VelocityTracker mVelocityTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_image);

//        if(mVelocityTracker == null) {//velocityTracker对象为空 获取velocityTracker对象
//            mVelocityTracker = VelocityTracker.obtain();
//        }else {//velocityTracker对象不为空 将velocityTracker对象重置为初始状态
//            mVelocityTracker.clear();
//        }
//        findViewById(R.id.ScrollImageView).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent motionEvent) {
//                int action=motionEvent.getAction();
//                int x= (int) motionEvent.getRawX();
//                int y= (int) motionEvent.getRawY();
//                mVelocityTracker.addMovement(motionEvent);//向velocityTracker对象添加action
//                switch(action){
//                    case MotionEvent.ACTION_DOWN://按下
//                        break;
//                    case MotionEvent.ACTION_MOVE://移动
////                        mVelocityTracker.computeCurrentVelocity(1000);
////                        Log.d("fbw", "移动X velocity: " + mVelocityTracker.getXVelocity());
////                        Log.d("fbw", "移动Y velocity: " + mVelocityTracker.getYVelocity());
//                        break;
//                    case MotionEvent.ACTION_UP://抬起
//                        mVelocityTracker.computeCurrentVelocity(1000);
//                        Log.d("fbw", "抬起X velocity: " + mVelocityTracker.getXVelocity());
//                        Log.d("fbw", "抬起Y velocity: " + mVelocityTracker.getYVelocity());
//                        break;
//                }
//                return true;
//            }
//        });
    }



}
