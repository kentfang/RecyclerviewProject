package com.fbw.recyclerviewproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cquxcm on 2016/6/20.
 */
public class RotateView extends View {

    private static final double MAX_ANGLE = 1e-1;
    private Paint mPaint;
    private float mRotation;
    private Float mPreviousAngle;



    public RotateView(Context context) {
        super(context);
        Log.d("xcm", "constructor 1");
    }

    public RotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("xcm", "constructor 2");

    }

    public RotateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("xcm", "constructor 3");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("xcm", "onAttachedToWindow");
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);

        mPreviousAngle = null;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        int radius = (int) (width > height ? height * 0.4444f : width * 0.444f);
        canvas.drawCircle(width / 2, height / 2, radius, mPaint);
        canvas.save();

        canvas.rotate(mRotation, width / 2, height / 2);
        canvas.drawLine(width / 2, height * 0.1f, width / 2, height * 0.9f, mPaint);
        canvas.restore();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getPointerCount() == 2) {
            float currentAngle = (float) angle(event);
            if (mPreviousAngle != null) {
                mRotation -= Math.toDegrees(clamp(mPreviousAngle - currentAngle, -MAX_ANGLE, MAX_ANGLE));
                invalidate();
            }
            mPreviousAngle = currentAngle;
        } else {
            mPreviousAngle = null;
        }
        return true;
    }

    private static double angle(MotionEvent event) {

        Log.d("xcm", "x0 = " + event.getX(0) + "," + "x1 = " + event.getX(1));
        Log.d("xcm", "y0 = " + event.getY(0) + "," + "y1 = " + event.getY(1));
//        x0 = 285.05814,x1 = 224.5007
//        y0 = 354.35126,y1 = 163.50037
        double deltaX = (event.getX(0) - event.getX(1));//获取两个手指触摸点的X坐标值的差值
        double deltaY = (event.getY(0) - event.getY(1));//获取两个手指触摸点的Y坐标值的差值
        return Math.atan2(deltaY, deltaX);
    }

    private static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }

        if (value > max) {
            return max;
        }

        return value;
    }


}
