package com.fbw.recyclerviewproject.galanz.viewpage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.fbw.recyclerviewproject.R;
import java.util.ArrayList;
import java.util.List;

public class MyVerticaladapter extends PagerAdapter {
    private List<Integer> list = new ArrayList<Integer>();
    private Context context;
    public MyVerticaladapter(Context context){
        list.add(R.drawable.p001);
        list.add(R.drawable.p002);
        list.add(R.drawable.p003);
        list.add(R.drawable.p004);
        list.add(R.drawable.p005);
        list.add(R.drawable.p006);
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.item_base,null);
        ImageView iv = view.findViewById(R.id.iv);
        iv.setBackgroundResource(list.get(position));
        container.addView(view);
        return view;
    }
}
