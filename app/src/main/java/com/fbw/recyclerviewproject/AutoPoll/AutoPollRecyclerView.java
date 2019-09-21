package com.fbw.recyclerviewproject.AutoPoll;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.lang.ref.WeakReference;

//自定义RecyclerView滚动控件，用于循环自动滚动
public class AutoPollRecyclerView extends RecyclerView {
    private static final long delayTime = 50;//间隔多少时间后执行滚动
    AutoPollTask Apt;//滚动线程
    private boolean running; //是否正在滚动
    private boolean canRun;//是否可以自动滚动，根据数据是否超出屏幕来决定

    //构造函数
    public AutoPollRecyclerView(Context context) {
        super(context);
    }
    //构造函数
    public AutoPollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Apt = new AutoPollTask(this);//滚动线程实例化
    }

    //创建滚动线程
    static class AutoPollTask implements Runnable {
        //WeakReference表示弱引用
        private final WeakReference<AutoPollRecyclerView> mReference;
        //使用弱引用持有外部类引用 防止内存泄漏
        public AutoPollTask(AutoPollRecyclerView reference) {
            this.mReference = new WeakReference<AutoPollRecyclerView>(reference);
        }

        @Override
        public void run() {
            //获取自定义的recyclerview对象
            AutoPollRecyclerView Aprv = mReference.get();
            //如果Aprv对象不为空，Aprv对象在滚动，Aprv对象可自动滚动
            if (Aprv != null && Aprv.running && Aprv.canRun) {
                //scrollBy(x, y)控制移动的距离（像素）
                //注意scrollBy和scrollTo的区别
                Aprv.scrollBy(2, 2);
                //延时运行线程，这里属于递归，线程自己调用自己
                Aprv.postDelayed(Aprv.Apt, Aprv.delayTime);
            }
        }
    }

    //开启:如果正在运行,先停止->再开启
    public void start() {
        if (running)
            stop(); //如果在运行，就停止
        canRun = true;
        running = true;
        //postDelayed方法启动指定线程（第一个参数为指定线程对象，第二参数为启动线程的延时）
        postDelayed(Apt, delayTime);
    }

    public void stop() {
        running = false;
        //removeCallbacks则关闭指定线程
        removeCallbacks(Apt);
    }

    @Override
    //重写onTouchEvent方法，防止自动滚动时，手触摸控件导致数据错乱
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (running)
                    stop();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                if (canRun)
                    start();
                break;
        }
        return super.onTouchEvent(e);
    }
}

