package com.fbw.recyclerviewproject.MyTransition;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.utils.LogUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Transition2Activity extends AppCompatActivity {


    ImageView imageView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition2);
        imageView = findViewById(R.id.transition_image);
        LogUtil.d("fbw","B=====imageView:"+imageView.getId());
        this.setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                LogUtil.d("fbw","B=============onSharedElementStart");

                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                LogUtil.d("fbw","B=============onSharedElementEnd");
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
            }

            @Override
            public void onRejectSharedElements(List<View> rejectedSharedElements) {
                LogUtil.d("fbw","B=============onRejectSharedElements");
                for (View v:rejectedSharedElements){
                    LogUtil.d("fbw","rejectedSharedElements:"+v.getId());
                    LogUtil.d("fbw","rejectedSharedElements:"+v.getTransitionName());
                }
                super.onRejectSharedElements(rejectedSharedElements);
            }

            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                LogUtil.d("fbw","B=============onMapSharedElements");
                for (String s:names){
                    LogUtil.d("fbw","s:"+s);
                }
                Iterator iterator = sharedElements.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, View> m = (Map.Entry<String, View>) iterator.next();
                    LogUtil.d("fbw","Map key:"+m.getKey());
                    LogUtil.d("fbw","Map view:"+m.getValue().getId());
                }
                super.onMapSharedElements(names, sharedElements);
            }

            @Override
            public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
                LogUtil.d("fbw","B=============onCaptureSharedElementSnapshot");
                return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
            }

            @Override
            public View onCreateSnapshotView(Context context, Parcelable snapshot) {
                LogUtil.d("fbw","B=============onCreateSnapshotView");
                ImageView imageView = new ImageView(context);
                return imageView;
            }

            @Override
            public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, OnSharedElementsReadyListener listener) {
                LogUtil.d("fbw","B=============onSharedElementsArrived");
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener);
            }
        });
//        executeTransition();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void executeTransition() {
        postponeEnterTransition();
        final View decorView = getWindow().getDecorView();
        getWindow().getDecorView().getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                decorView.getViewTreeObserver().removeOnPreDrawListener(this);
                supportStartPostponedEnterTransition();
                return true;
            }
        });
        FreeCookEnterTransition freeCookEnterTransition= new FreeCookEnterTransition();
        freeCookEnterTransition.addTarget("123");
        getWindow().setSharedElementEnterTransition(new Fade());
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
