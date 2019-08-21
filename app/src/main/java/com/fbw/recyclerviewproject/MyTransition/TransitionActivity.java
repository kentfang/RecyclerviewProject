package com.fbw.recyclerviewproject.MyTransition;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.Button;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.utils.LogUtil;

import java.util.List;
import java.util.Map;

public class TransitionActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        button = findViewById(R.id.transition_button);
        button.setOnClickListener(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
//        FreeCookExitTransition freeCookExitTransition = new FreeCookExitTransition();
//        freeCookExitTransition.addTarget("123");
//        getWindow().setSharedElementExitTransition(new Fade());
        this.setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                LogUtil.d("fbw","A=============onSharedElementStart");
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                LogUtil.d("fbw","A=============onSharedElementEnd");
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
            }

            @Override
            public void onRejectSharedElements(List<View> rejectedSharedElements) {
                LogUtil.d("fbw","A=============onRejectSharedElements");
                super.onRejectSharedElements(rejectedSharedElements);
            }

            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                LogUtil.d("fbw","A=============onMapSharedElements");
                super.onMapSharedElements(names, sharedElements);
            }

            @Override
            public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
                LogUtil.d("fbw","A=============onCaptureSharedElementSnapshot");
                return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
            }

            @Override
            public View onCreateSnapshotView(Context context, Parcelable snapshot) {
                LogUtil.d("fbw","A=============onCreateSnapshotView");
                return super.onCreateSnapshotView(context, snapshot);
            }

            @Override
            public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, OnSharedElementsReadyListener listener) {
                LogUtil.d("fbw","A=============onSharedElementsArrived");
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener);
            }
        });
        ActivityOptionsCompat aop=ActivityOptionsCompat.makeSceneTransitionAnimation(TransitionActivity.this,
                v,"123");
        Intent intent=new Intent(TransitionActivity.this, Transition2Activity.class);
        ActivityCompat.startActivity(TransitionActivity.this,intent,aop.toBundle());
    }
}
