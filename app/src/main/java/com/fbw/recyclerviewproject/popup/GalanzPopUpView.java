package com.fbw.recyclerviewproject.popup;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;

public  abstract class GalanzPopUpView {
    private WeakReference<Context> contextWeak;
    private ViewGroup decorView;
    private FrameLayout rootView;
    private View contentView;
    private boolean isDismissing = false;
    private int rootViewBackgroundResource = android.R.color.transparent;
    public GalanzPopUpView(Context context){
        this.contextWeak = new WeakReference<>(context);
        initViewByContext();
        contentView  = LayoutInflater.from(context).inflate(layoutId(),null,false);
        init(contentView);
    }
    public GalanzPopUpView(View view) throws Exception {
        initViewByView(view);
        decorView = findDecorView(view);
        if (decorView==null){
            throw new Exception("");
        }
        contentView  = LayoutInflater.from(view.getContext()).inflate(layoutId(),null,false);
        init(contentView);
    }

    private ViewGroup findDecorView(View view){
        do {
            if (view instanceof FrameLayout){
                if(view.getId()==android.R.id.content){
                    return (ViewGroup)view;
                }
            }

            if (view!=null){
                ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent:null;
            }
        }while (view!=null);
        return null;
    }

    private void  initViewByContext(){
        Context context = contextWeak.get();
        if(context == null) return;
        decorView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = new FrameLayout(context);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
    }
    private void initViewByView(View view){
        rootView = new FrameLayout(view.getContext());
        rootView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
    }

    protected abstract int layoutId();
    protected abstract void init(View contentView);

    /**
     * 设置遮罩的背景色
     * @param resId
     */
    protected void setShadeBackGroud(@DrawableRes int resId){
        rootViewBackgroundResource = resId;
    }

    public void showAt(int gravity,int w,int h){
        if(isShow())return;
        isDismissing = true;
        decorView.addView(rootView);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(w,h);
        params.gravity = gravity;
        if(contentView.getParent()!=null)((ViewGroup)rootView.getParent()).removeView(rootView);
        rootView.addView(contentView,params);
    }

    public void showAt(int gravity,int w,int h,GalanzPopUpViewMaskType type){
        setMaskType(type);
        showAt(gravity,w,h);
    }
    public void showAutoDismiss(int gravity,int w,int h,GalanzPopUpViewMaskType type){
        showAt(gravity,w,h,type);
        scheduleDismiss();
    }
    public void showAutoDismiss(int gravity,int w,int h){
        showAt(gravity,w,h);
        scheduleDismiss();
    }
    private void setMaskType(GalanzPopUpViewMaskType maskType) {
        switch (maskType) {
            case None:
                configMaskType(rootViewBackgroundResource, false, false);
                break;
            case Clear:
                configMaskType(rootViewBackgroundResource, true, false);
                break;
            case DISSMISS:
                configMaskType(rootViewBackgroundResource, true, true);
                break;
            case PASS:
                configMaskType(rootViewBackgroundResource, false, true);
                break;
            default:
                break;
        }
    }

    private void configMaskType(int bg, boolean clickable, boolean cancelable) {
        rootView.setBackgroundResource(bg);
        rootView.setClickable(clickable);
        setCancelable(cancelable);
    }

    private void setCancelable(boolean isCancelable) {
        if (isCancelable) {
            rootView.setOnTouchListener(onCancelableTouchListener);
        } else {
            rootView.setOnTouchListener(null);
        }
    }

    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                dismiss();
                setCancelable(false);
            }
            return false;
        }
    };
    public void dismiss(){
        isDismissing = false;
        rootView.removeView(contentView);
        decorView.removeView(rootView);
    }
    public boolean isShow(){
        return rootView.getParent() != null || isDismissing;
    }

    public enum GalanzPopUpViewMaskType {
        None,  // 允许点击遮罩下的空间
        Clear,     // 不允许遮罩下面控件点击
        DISSMISS,//不允许点击遮罩下的控件，并且点击消失
        PASS,//允许点击遮罩下的控件，并且点击消失,点击穿透
    }


    private OnDismissListener onDismissListener;
    public interface OnDismissListener {
        void onDismiss(GalanzPopUpView view);
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dismiss();
        }
    };
    private static final long DISMISSDELAYED = 1000;
    private void scheduleDismiss() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendEmptyMessageDelayed(0, DISMISSDELAYED);
    }
}
