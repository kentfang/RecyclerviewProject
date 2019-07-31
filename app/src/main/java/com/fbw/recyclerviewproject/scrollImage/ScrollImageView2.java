package com.fbw.recyclerviewproject.scrollImage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.utils.LogUtil;


public class ScrollImageView2 extends View {

    private static final double MAX_ANGLE = 1e-1;
    private  int maxFlingVelocity;
    private Paint mPaint;

    private Float mPreviousAngle;

    private VelocityTracker mVelocityTracker;
    int width;
    int height;

    double dx;//作为手势的固定位置，view 的中心位置
    double dy;//作为手势的固定位置，view的中心位置

    private float maxDegree = 360;
    private float minDegree = 0;
    private float factor = 40 ; //反弹因子
    private Integer preCallBackRotation ;//上一次回调给用户时候的数据，避免同一个数据，回调频繁

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public float getMaxDegree() {
        return maxDegree;
    }
    public void setMaxDegree(float maxDegree) {
        this.maxDegree = maxDegree;
    }
    public float getMinDegree() {
        return minDegree;
    }
    public void setMinDegree(float minDegree) {
        this.minDegree = minDegree;
    }

    double preX;
    double preY;

    private Bitmap mBitmap;
    private Bitmap mTop;
    private Bitmap mBottom;

    private int mPointerId;//记录按下的点。

    private static final int standerSpeed = 2000;

    ValueAnimator valueAnimator;

    ValueAnimator reboundAnimator;

    private float mRotation;//当前角度

    public void setmRotation(float mRotation) {
        this.mRotation = mRotation;
        preCallBackRotation = (int)mRotation;
        invalidate();
    }

    public ScrollImageView2(Context context) {
        super(context);
        init();
    }

    public ScrollImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ScrollImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        maxDegree = 360;
        minDegree = 0;
        factor = 40 ;
        preCallBackRotation = 0;
        mRotation = 0;

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.common_temperature_turntable);
        mTop = BitmapFactory.decodeResource(getResources(), R.mipmap.top);
        mBottom = BitmapFactory.decodeResource(getResources(), R.mipmap.bottom);


        maxFlingVelocity  = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
        mVelocityTracker = VelocityTracker.obtain();
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPaint = new Paint();
        mPreviousAngle = null;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        dx = width/2;
        dy = height/2;

//        if (mRotation>=maxDegree){
//            mPaint.setAlpha((int) (255*((mRotation-maxDegree)/factor)));
//        }else{
//            mPaint.setAlpha(0);
//        }
        Rect srcTop = new Rect(0,0,mTop.getWidth(),mTop.getHeight());
        Rect dstTop= new Rect((int)(width/9),0,(int)(width/3.5),(int)(height/3.5));
        canvas.drawBitmap(mTop, srcTop,dstTop,mPaint);
//        if (mRotation<=minDegree){
//            mPaint.setAlpha((int) (255*((minDegree-mRotation)/factor)));
//        }else{
//            mPaint.setAlpha(0);
//        }
//        Rect srcBottom = new Rect(0,0,mBottom.getWidth(),mBottom.getHeight());
//        Rect dstBottom= new Rect(0,  height-(int)(height/3.5), (int)(width/3.5), height);
//        canvas.drawBitmap(mBottom, srcBottom,dstBottom,mPaint);
//        mPaint.setAlpha(255);
//        canvas.rotate(mRotation, width / 2, height / 2);
        Rect src = new Rect(0,0,mBitmap.getWidth(),mBitmap.getHeight());
        Rect dst = new Rect(0,0,width,height);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary2));
        canvas.drawCircle(width/2,height/2,width/2,mPaint);
//        canvas.drawBitmap(mBitmap, src,dst,mPaint);

        if (null!=onScrollViewDegreeListener){
            this.post(new Runnable() {
                @Override
                public void run() {
                    synchronized (preCallBackRotation){
                        if (preCallBackRotation !=(int)mRotation){
                            preCallBackRotation = (int) mRotation;
                            onScrollViewDegreeListener.onScrollViewDegree(preCallBackRotation);
                        }
                    }
                }
            });
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionEvent);
        calculateAngleByMotionEvent(motionEvent);
        return true;
    }

    /**
     * 根据手势，计算圆盘角度
     * @param event
     */
    private void calculateAngleByMotionEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPointerId = event.getPointerId(0);
                preX = event.getX();
                preY = event.getY();
                if (null != valueAnimator && valueAnimator.isRunning()){
                    valueAnimator.cancel();
                    valueAnimator = null;
                }
                if (null != reboundAnimator && reboundAnimator.isRunning()){
                    reboundAnimator.cancel();
                    reboundAnimator = null;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                moveAction(event);
                break;
            case MotionEvent.ACTION_UP:
                if (mRotation>maxDegree){
                    startReboundAnimation(mRotation,maxDegree);
                }else if (mRotation<minDegree){
                    startReboundAnimation(mRotation,minDegree);
                }else{
                    upAction(event);//手势起，，，不处理滚动操作，判断是否有惯性
                }
                break;
            default:
                break;
        }
    }

    //判断角度范围
    private double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }




    private void moveAction(MotionEvent event){
        double deltaX = (event.getX() - dx);
        double deltaY = (event.getY() - dy);
        float currentAngle = (float) Math.atan2(deltaY, deltaX);
        if (mPreviousAngle != null) {
            mRotation -= Math.toDegrees(clamp(mPreviousAngle - currentAngle, -MAX_ANGLE, MAX_ANGLE));
            if (mRotation>=maxDegree+factor){
                mRotation = maxDegree+factor;
            }else if (mRotation<=minDegree-factor){
                mRotation = minDegree-factor;
            }
            invalidate();
        }
        mPreviousAngle = currentAngle;
    }

    private void upAction(MotionEvent event){
        double cruX = event.getX();
        double cruY = event.getY();
        mVelocityTracker.computeCurrentVelocity(1000, maxFlingVelocity);
        float yVelocity = mVelocityTracker.getYVelocity(mPointerId);
        if (yVelocity > standerSpeed || yVelocity < -standerSpeed) {
            //处于惯性状态，修改动态修改deltaX，deltaY的值，，标准速度下 不处理
            if (cruY<preY){
                //向上
                startOverScollerAnimation((int) Math.abs(yVelocity),(int) Math.abs(yVelocity)-standerSpeed, State.UP);
            }else{
                //向下
                startOverScollerAnimation((int) Math.abs(yVelocity),(int) Math.abs(yVelocity)-standerSpeed, State.DOWN);
            }
        }
        releaseVelocityTracker();
    }

    private boolean isRebound = false;//是否处于反弹状态，默认是不反弹
    private int preRotation;//前一个角度，用于动画过程中，避免刷新频繁
    private void startOverScollerAnimation(final float speed, int duration, final State type) {
        isRebound = false;
        valueAnimator = ValueAnimator.ofFloat(speed,0);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float s =(float)animation.getAnimatedValue();
                switch (type){
                    case UP:
                        mRotation =  mRotation + (s/speed)*2;
                        break;
                    case DOWN:
                        mRotation =  mRotation - (s/speed)*2;
                        break;
                }
                mPreviousAngle = (float) Math.toRadians(mRotation);
                if (preRotation != (int)mRotation){
                    preRotation = (int)mRotation;
                    invalidate();
                }
                if (mRotation>maxDegree+factor){
                    isRebound = true;
                    animation.cancel();
                    startReboundAnimation(mRotation,maxDegree);
                }else if (mRotation<minDegree-factor){
                    isRebound = true;
                    animation.cancel();
                    startReboundAnimation(mRotation,minDegree);
                }
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }
            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isRebound){
                    if (mRotation>maxDegree){
                        startReboundAnimation(mRotation,maxDegree);
                    }else if (mRotation<minDegree){
                        startReboundAnimation(mRotation,minDegree);
                    }
                }
            }
            @Override
            public void onAnimationCancel(Animator animator) {
            }
            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        valueAnimator.start();
    }

    private void startReboundAnimation(final float startRotation, final float endRotation){
        reboundAnimator = ObjectAnimator.ofFloat(0,1);
        reboundAnimator.setInterpolator(new DecelerateInterpolator());
        reboundAnimator.setDuration(1000);
        reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float s =(float)valueAnimator.getAnimatedValue();
                mRotation =  (endRotation-startRotation)*s + startRotation ;
                mPreviousAngle = (float) Math.toRadians(mRotation);
                if (preRotation != (int)mRotation){
                    preRotation = (int)mRotation;
                    invalidate();
                }
            }
        });
        reboundAnimator.start();
    }




    private void releaseVelocityTracker() {
        if(null != mVelocityTracker) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }


    public enum State {
        UP,
        DOWN;
    }

    public interface OnScrollViewDegreeAsyncListener{
        void onScrollViewDegree(float degree);
    }
    private OnScrollViewDegreeAsyncListener onScrollViewDegreeListener;

    public OnScrollViewDegreeAsyncListener getOnScrollViewDegreeListener() {
        return onScrollViewDegreeListener;
    }
    public void setOnScrollViewDegreeListener(OnScrollViewDegreeAsyncListener onScrollViewDegreeListener) {
        this.onScrollViewDegreeListener = onScrollViewDegreeListener;
    }
}
