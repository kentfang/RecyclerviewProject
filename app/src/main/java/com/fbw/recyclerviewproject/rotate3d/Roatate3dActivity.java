package com.fbw.recyclerviewproject.rotate3d;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fbw.recyclerviewproject.R;

import static android.view.animation.Animation.RESTART;

public class Roatate3dActivity extends Activity
{
    private LinearLayout layout;  //根布局；
    private ImageView picture, picture1;  //用于展示图片详细的ImageView；
    int[] imgs = {R.drawable.p1, R.drawable.p2};  //创建一个数组，存放要切换的图片；

    Boolean flag = true;
    int index = -1;                                       //要加载的图片脚标；

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_roatate3d);

        layout = (LinearLayout) findViewById(R.id.layout);
        picture = (ImageView) findViewById(R.id.picture);
        picture1 = (ImageView) findViewById(R.id.picture1);

        picture.setVisibility(View.GONE);
        new Thread()
        {
            @Override
            public void run()
            {
                while(flag)
                {
                    index++;
                    handler.sendEmptyMessage(0);
                    try
                    {
                        Thread.sleep(3500);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        flag = false;
        super.onDestroy();
    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if(msg.what == 0)
            {
                if(index % 2 == 1)
                {
                    leftToRigth();
                }
                else if(index%2==0)
                {
                    rigthToLeft();
                }
            }
        }
    };

    public void rigthToLeft()
    {
        // 奇数张图片转换到偶数张；
        picture.setImageResource(imgs[index]);
        // 获取布局的中心点位置，作为旋转的中心点
        float centerX = layout.getWidth() ;
        float centerY = layout.getHeight() / 2f;
        // 构建3D旋转动画对象，旋转角度为360到270度，这使得ImageView将会从可见变为不可见，并且旋转的方向是相反的
        final Rotate3dAnimation rotation = new Rotate3dAnimation(360, 270, centerX,
                centerY, 310.0f, true);
        // 动画持续时间500毫秒
        rotation.setDuration(500);
        // 动画完成后保持完成的状态
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        // 设置动画的监听器
        rotation.setAnimationListener(new TurnToListView());
        layout.startAnimation(rotation);
    }

    public void leftToRigth()
    {
        // 偶数张转换到奇数张图片；
        picture1.setImageResource(imgs[index]);
        // 获取布局的中心点位置，作为旋转的中心点
        float centerX = layout.getWidth() ;
        float centerY = layout.getHeight() / 2f;
        // 构建3D旋转动画对象，旋转角度为0到90度，这使得ListView将会从可见变为不可见
        final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 90, centerX, centerY,
                310.0f, true);
        // 动画持续时间500毫秒
        rotation.setDuration(500);
        // 动画完成后保持完成的状态
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        // 设置动画的监听器
        rotation.setAnimationListener(new TurnToImageView());
        layout.startAnimation(rotation);
    }

    /**
     * 注册在ListView点击动画中的动画监听器，用于完成ListView的后续动画。
     */
    class TurnToImageView implements Animation.AnimationListener
    {
        @Override
        public void onAnimationStart(Animation animation)
        {
        }

        /**
         * 当ListView的动画完成后，还需要再启动ImageView的动画，让ImageView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation)
        {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = layout.getWidth() / 2f;
            float centerY = layout.getHeight() / 2f;
            picture.setVisibility(View.GONE);
            picture1.setVisibility(View.VISIBLE);
            picture1.requestFocus();
            // 构建3D旋转动画对象，旋转角度为270到360度，这使得ImageView将会从不可见变为可见
            final Rotate3dAnimation rotation = new Rotate3dAnimation(270, 360, centerX, centerY,
                    310.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            layout.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation)
        {
        }

    }

    /**
     * 注册在ImageView点击动画中的动画监听器，用于完成ImageView的后续动画。
     */
    class TurnToListView implements Animation.AnimationListener
    {
        @Override
        public void onAnimationStart(Animation animation)
        {
        }

        /**
         * 当ImageView的动画完成后，还需要再启动ListView的动画，让ListView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation)
        {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = layout.getWidth() / 2f;
            float centerY = layout.getHeight() / 2f;
            picture1.setVisibility(View.GONE);
            picture.setVisibility(View.VISIBLE);
            picture.requestFocus();
            // 构建3D旋转动画对象，旋转角度为90到0度，这使得ListView将会从不可见变为可见，从而回到原点
            final Rotate3dAnimation rotation = new Rotate3dAnimation(90, 0, centerX, centerY,
                    310.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            layout.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation)
        {
        }
    }
}
