package com.fbw.recyclerviewproject.rotate3d;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fbw.recyclerviewproject.R;

public class FlipAnimationActivity extends AppCompatActivity {

    private int clickCount = 0;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_animation);
        imageView = (ImageView) findViewById(R.id.imageview);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playFlipAnimation2();
            }

        });
    }


    private void playFlipAnimation2() {
        clickCount++;
        AnimatorSet animatorSetOut = (AnimatorSet) AnimatorInflater
                .loadAnimator(this, R.animator.card_flip_left_out);

        final AnimatorSet animatorSetIn = (AnimatorSet) AnimatorInflater
                .loadAnimator(this, R.animator.card_flip_left_in);

        animatorSetOut.setTarget(imageView);
        animatorSetIn.setTarget(imageView);

        animatorSetOut.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {// 翻转90度之后，换图
                if (clickCount % 2 == 0) {
                    imageView.setImageResource(R.drawable.p1);
                } else {
                    imageView.setImageResource(R.drawable.p2);
                }
                animatorSetIn.start();
            }
        });

        animatorSetIn.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO
            }
        });
        animatorSetOut.start();
    }
}
