package com.fbw.recyclerviewproject.recyclerAni;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;
import java.util.List;

public class RecylerItemAni extends SimpleItemAnimator {
    List<RecyclerView.ViewHolder> removeHolders = new ArrayList<>();
    List<RecyclerView.ViewHolder> removeAnimators = new ArrayList<>();
    List<RecyclerView.ViewHolder> moveHolders = new ArrayList<>();
    List<RecyclerView.ViewHolder> moveAnimators = new ArrayList<>();
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        removeHolders.add(holder);
        return true;
    }
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        return false;
    }
    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        holder.itemView.setTranslationY(fromY - toY);
        moveHolders.add(holder);
        return true;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return false;
    }
    @Override
    public void runPendingAnimations() {
        if(!removeHolders.isEmpty()) {
            for(RecyclerView.ViewHolder holder : removeHolders) {
                remove(holder);
            }
            removeHolders.clear();
        }
        if(!moveHolders.isEmpty()){
            for(RecyclerView.ViewHolder holder : moveHolders) {
                move(holder);
            }
            moveHolders.clear();
        }
    }
    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
    }
    @Override
    public void endAnimations() {
    }
    @Override
    public boolean isRunning() {
        return !(removeHolders.isEmpty() && removeAnimators.isEmpty() && moveHolders.isEmpty() && moveAnimators.isEmpty());
    }
    private void remove(final RecyclerView.ViewHolder holder){
        removeAnimators.add(holder);
        int[] location = new int[2];
        holder.itemView.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        TranslateAnimation animation = new TranslateAnimation(0, -holder.itemView.getWidth(), 0, 0);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                dispatchRemoveStarting(holder);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                removeAnimators.remove(holder);
                dispatchRemoveFinished(holder);
                if(!isRunning()){
                    dispatchAnimationsFinished();
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        holder.itemView.startAnimation(animation);
    }

    private void move(final RecyclerView.ViewHolder holder){
        moveAnimators.add(holder);
        ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView,
                "translationY", holder.itemView.getTranslationY(), 0);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(android.animation.Animator animation) {
                dispatchMoveStarting(holder);
            }
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                dispatchMoveFinished(holder);
                moveAnimators.remove(holder);
                if(!isRunning()) dispatchAnimationsFinished();
            }
        });
        animator.start();
    }

}