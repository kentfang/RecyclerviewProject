package com.fbw.recyclerviewproject.ripple;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
// TODO Auto-generated method stub
        super.onDraw(canvas);
//新建一只画笔，并设置为绿色属性
        Paint _paint = new Paint();
        _paint.setColor(Color.GREEN);
//新建矩形r1
        RectF r1 = new RectF();
        r1.left = 50;
        r1.right = 250;
        r1.top = 50;
        r1.bottom = 150;

//新建矩形r2
        RectF r2 = new RectF();
        r2.left = 50;
        r2.right = 250;
        r2.top = 200;
        r2.bottom = 300;
//画出矩形r1
        canvas.drawRect(r1, _paint);
//画出圆角矩形r2
        _paint.setColor(Color.rgb(204, 204, 204));
        canvas.drawRoundRect(r2, 10, 10, _paint);
    }
}
