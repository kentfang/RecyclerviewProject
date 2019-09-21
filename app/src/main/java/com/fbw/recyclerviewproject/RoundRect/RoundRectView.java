package com.fbw.recyclerviewproject.RoundRect;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RoundRectView extends View {
    RoundRectDrawable drawable;
    public RoundRectView(Context context) {
        super(context);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        drawable = new RoundRectDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundDrawable(drawable);
    }
}
