package com.fbw.recyclerviewproject.ripple;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.view.MotionEvent;

import android.widget.RelativeLayout;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.utils.LogUtil;


public class RippleView2 extends RelativeLayout {
    private float x = -1;
    private float y = -1;
    private boolean animationRunning = false;
    private Paint paint;

    private int rippleAlpha = 250;
    private int rippleColor= getResources().getColor(R.color.rippelColor);


    private int frameRate = 10;
    private int rippleDuration = 400;
    private int timer = 0;
    int totalTime;

    private boolean clickIsOver= false;

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (clickIsOver){

            }
            invalidate();
        }
    };

    Bitmap mBitmap;
    private Rect mSrcRect;
    private float radiusMax = 0;

    private boolean isFrist = true;

    public RippleView2(Context context) {
        super(context);
    }

    public RippleView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public RippleView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        if (isInEditMode())
            return;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(rippleColor);
        paint.setAlpha(rippleAlpha);
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        this.setWillNotDraw(false);
        this.setDrawingCacheEnabled(true);
        this.setClickable(true);
        totalTime = rippleDuration / frameRate;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.common_pic_shine2);
//        mSrcRect = new Rect(mBitmap.getWidth()/4,mBitmap.getHeight()/4,mBitmap.getWidth()*3/4,mBitmap.getHeight()*3/4);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (animationRunning) {
            if (rippleDuration<=timer*frameRate) {
                animationRunning = false;
                timer = 0;
                clickIsOver = true;
                invalidate();
                if (onCompletionListener != null) onCompletionListener.onComplete(this);
                return;
            }else{
                    Message message = Message.obtain();
                    message.what = 10;
                    message.obj = timer;
                    canvasHandler.postDelayed(runnable, frameRate);
                    canvasHandler.sendMessage(message);
            }
//            canvas.drawRect(createRectF(),paint);
//            paint.setAlpha((int) (rippleAlpha + ((100 - rippleAlpha) * (((float) timer * frameRate) / rippleDuration))));
            canvas.drawBitmap(mBitmap, createmSrcRect(),createRectF(),paint);
            timer++;
        }else{
            if (clickIsOver){
                LogUtil.d("fbw","radiusMax:"+radiusMax);
                if (radiusMax>getMeasuredWidth()){
                    clickIsOver = false;
                }else{
                    canvasHandler.postDelayed(runnable, frameRate);
                    radiusMax=radiusMax+1;
                    canvas.drawBitmap(mBitmap, createmSrcRect(),createRectF(),paint);
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        animateRipple(event);
        return super.onTouchEvent(event);
    }

    public void animateRipple(MotionEvent event) {
        createAnimation(event.getX(), event.getY());
    }
    private void createAnimation(final float x, final float y) {
        if (!animationRunning){
            invalidate();
            animationRunning = true;
            clickIsOver = false;
            radiusMax = 1;
        }
    }


    float degree;
    private RectF createRectF(){
        RectF rectF = null;
        if (clickIsOver){
            rectF = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
        }else{
            degree = (float) timer/(float)totalTime;
            rectF = new RectF(0,getMeasuredHeight()*(1-degree),getMeasuredWidth(),getMeasuredHeight());
        }
        return rectF;
    }
    private Rect createmSrcRect(){
        Rect rectF = null;
        if (clickIsOver){
//            rectF = new Rect(
//                    (int) (radiusMax - getMeasuredWidth()/3),
//                    (int) (radiusMax - getMeasuredHeight()/3),
//                    (int) (getMeasuredWidth()-(radiusMax - getMeasuredWidth()/3)),
//                    (int) (getMeasuredHeight()-(radiusMax - getMeasuredHeight()/3)));

            rectF = new Rect(
                    (int) radiusMax,
                    (int)radiusMax,
                    (int)(mBitmap.getWidth()-radiusMax),
                    (int) (mBitmap.getHeight()-radiusMax));
        }else{
            degree = (float) timer/(float)totalTime;
            rectF = new Rect(0,0,mBitmap.getWidth(),(int)(mBitmap.getHeight()*degree));
        }
        return rectF;
    }

    private RippleView2.OnRippleCompleteListener onCompletionListener;
    public interface OnRippleCompleteListener {
        void onComplete(RippleView2 rippleView);
        void progress(RippleView2 rippleView, int time, int total);
    }

    public void setOnRippleCompleteListener(OnRippleCompleteListener onCompletionListener){
        this.onCompletionListener = onCompletionListener;
    }
    private Handler canvasHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==10){
                if (onCompletionListener != null) {
                    onCompletionListener.progress(RippleView2.this, (Integer) msg.obj, totalTime);
                }
            }
        }
    };
}
