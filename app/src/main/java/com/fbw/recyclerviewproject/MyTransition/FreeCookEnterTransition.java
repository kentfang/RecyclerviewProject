package com.fbw.recyclerviewproject.MyTransition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

import com.fbw.recyclerviewproject.utils.LogUtil;


public class FreeCookEnterTransition extends Transition {

    private static final String scale="scale";
    private static final String TOP = "top";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        Rect rect = new Rect();
        view.getHitRect(rect);

        transitionValues.values.put(TOP, rect.top);
        transitionValues.values.put(scale, 0f);
    }
    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        transitionValues.values.put(scale, 1f);
    }

    @Override
    public Animator createAnimator(final ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        final float startScale = (float) startValues.values.get(scale);
        final float endScale = (float) endValues.values.get(scale);
        ValueAnimator animator = ObjectAnimator.ofFloat(startScale,endScale);
        animator.setDuration(1000);
        final View endView = endValues.view;

        endView.setScaleX(startScale);
        endView.setScaleY(startScale);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float) animation.getAnimatedValue();
                endView.setScaleY(f);
                endView.setScaleX(f);
                endView.setAlpha(f);
                sceneRoot.setAlpha(f);
            }
        });
        return animator;
    }
}
