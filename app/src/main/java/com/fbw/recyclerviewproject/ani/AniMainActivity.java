package com.fbw.recyclerviewproject.ani;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.fbw.recyclerviewproject.R;


public class AniMainActivity extends Activity implements OnClickListener {

	private TypeEvaluator evaluator = new ViewTypeEvaluator();


	private ImageView view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		view=findViewById(R.id.imageview);view.setOnClickListener(this);
	}

	float endx = 400f;
	float endy = 400f;

	@Override
	public void onClick(final View v) {
		copy(v);
		final int[] location = new int[2];
		v.getLocationOnScreen(location);
		int x = location[0];
		int y = location[1];
		AnimatorPath path = new AnimatorPath();
		path.moveTo(0f,0f);
		path.curveTo(0f, 0f, (endx-x)/2f,endy-y-100,endx-x ,endy-y );
		ObjectAnimator ani = ObjectAnimator.ofObject(this, "hehe", evaluator, path.getPoints().toArray());
		ani.setDuration(1000);
		ani.start();
	}

	public void setHehe(Viewpoint point){
		view.setTranslationX(point.mX);
		view.setTranslationY(point.mY);
	}



	public void copy(View v){
		v.setDrawingCacheEnabled(true);
		Bitmap srcBm = Bitmap.createBitmap(v.getDrawingCache());
		v.setDrawingCacheEnabled(false);
		//创建原图的一个副本,可以修改图片
		Bitmap copyBm=Bitmap.createBitmap(srcBm.getWidth(), srcBm.getHeight(), srcBm.getConfig());
		//准备一个画板
		Canvas canvas=new Canvas(copyBm);
		//准备画笔
		Paint paint=new Paint();
		paint.setColor(Color.BLACK);
		//3.开始画
		Matrix m=new Matrix();//按照1:1画图
		canvas.drawBitmap(srcBm, m, paint);//仿照原图作画
		view.setImageBitmap(copyBm);
	}
}
