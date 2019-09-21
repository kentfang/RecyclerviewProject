package com.fbw.recyclerviewproject.popup;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.fbw.recyclerviewproject.R;

public class StatusPopUpView {


    public void showSuccess(Context context){
        GalanzPopUpView view1 = new SuccessStatusPopUpView(context);
        view1.showAutoDismiss(Gravity.CENTER, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,
                GalanzPopUpView.GalanzPopUpViewMaskType.None);
    }
    public static void showSuccess(View view) throws Exception {
        GalanzPopUpView view1 = new SuccessStatusPopUpView(view);
        view1.showAutoDismiss(Gravity.CENTER, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,
                GalanzPopUpView.GalanzPopUpViewMaskType.DISSMISS);
    }
    public void showErr(){}
    public void showFail(){}


    public static class SuccessStatusPopUpView extends GalanzPopUpView{
        public SuccessStatusPopUpView(View view) throws Exception {
            super(view);
        }

        public SuccessStatusPopUpView(Context context) {
            super(context);
        }
        @Override
        protected int layoutId() {
            return R.layout.status_pop_up_view_success;
        }
        @Override
        protected void init(View contentView) {
        }
    }
}
