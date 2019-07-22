package com.fbw.recyclerviewproject.circleView;


import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.fbw.recyclerviewproject.R;

import static android.graphics.Paint.Style.STROKE;

/**
 *
 */

public class CircleView extends View {

    private int outsideColor;    //进度的颜色
    private float outsideRadius;    //外圆半径大小
    private int insideColor;    //背景颜色
    private float progressWidth;    //圆环的宽度
    private float progress;    //当前进度
    private int direction;    //进度从哪里开始(设置了4个值,上左下右)

    private int duration; //整个运行时间

    private TimeInterpolator value;

    private Paint paint;

    private ValueAnimator animator;

    public void setProgressCallBack(OnCircleViewProgressCallBack progressCallBack) {
        this.progressCallBack = progressCallBack;
    }

    private OnCircleViewProgressCallBack progressCallBack;

    enum DirectionEnum {
        LEFT(0, 180.0f),
        TOP(1, 270.0f),
        RIGHT(2, 0.0f),
        BOTTOM(3, 90.0f);

        private final int direction;
        private final float degree;

        DirectionEnum(int direction, float degree) {
            this.direction = direction;
            this.degree = degree;
        }

        public int getDirection() {
            return direction;
        }

        public float getDegree() {
            return degree;
        }

        public boolean equalsDescription(int direction) {
            return this.direction == direction;
        }

        public static DirectionEnum getDirection(int direction) {
            for (DirectionEnum enumObject : values()) {
                if (enumObject.equalsDescription(direction)) {
                    return enumObject;
                }
            }
            return RIGHT;
        }

        public static float getDegree(int direction) {
            DirectionEnum enumObject = getDirection(direction);
            if (enumObject == null) {
                return 0;
            }
            return enumObject.getDegree();
        }
    }
    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, 0);
        outsideColor = a.getColor(R.styleable.CircleView_outside_color, ContextCompat.getColor(getContext(), R.color.colorPrimary));
        outsideRadius = a.getDimension(R.styleable.CircleView_outside_radius,60);
        insideColor = a.getColor(R.styleable.CircleView_inside_color, ContextCompat.getColor(getContext(), android.R.color.holo_blue_light));
        progressWidth = a.getDimension(R.styleable.CircleView_progress_width, 10);
        progress = a.getFloat(R.styleable.CircleView_progress, 50.0f);
        direction = a.getInt(R.styleable.CircleView_direction, 3);
        duration =a.getInt(R.styleable.CircleView_duration, 200);
        a.recycle();
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int circlePoint = getWidth() / 2;
        //第一步:画背景(即内层圆)
        paint.setColor(insideColor); //设置圆的颜色
        paint.setStyle(STROKE); //设置空心
        paint.setStrokeWidth(progressWidth); //设置圆的宽度
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawCircle(circlePoint, circlePoint, outsideRadius, paint); //画出圆
        //第二步:画进度(圆弧)
        paint.setColor(outsideColor);  //设置进度的颜色
        RectF oval = new RectF(circlePoint - outsideRadius, circlePoint - outsideRadius, circlePoint + outsideRadius, circlePoint + outsideRadius);  //用于定义的圆弧的形状和大小的界限
        canvas.drawArc(oval, DirectionEnum.getDegree(direction), 360 * (progress / 100), false, paint);  //根据进度画圆弧
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else {
            width = (int) ((2 * outsideRadius) + progressWidth);
        }
        size = MeasureSpec.getSize(heightMeasureSpec);
        mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else {
            height = (int) ((2 * outsideRadius) + progressWidth);
        }
        setMeasuredDimension(width, height);
    }
    private synchronized void startAnim(float startProgress) {
        animator = ObjectAnimator.ofFloat(0, startProgress);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                CircleView.this.progress = (float) animation.getAnimatedValue();
                postInvalidate();
                if (progressCallBack!=null){
                    progressCallBack.onCircleViewProgress( CircleView.this.progress);
                }
            }
        });
        animator.setDuration(duration);
        animator.setInterpolator(value==null?new LinearInterpolator():value);
        animator.start();
    }


    interface OnCircleViewProgressCallBack{
        void onCircleViewProgress(float progress);
    }

    public void start(){
        startAnim(100);
    }
    public void setInterpolator(TimeInterpolator value){
        this.value = value;
    }
}
