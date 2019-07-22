package com.fbw.recyclerviewproject.ani;

import android.animation.TypeEvaluator;
import android.util.Log;

public class ViewTypeEvaluator implements TypeEvaluator<Viewpoint>{

	public Viewpoint evaluate(float fraction, Viewpoint startValue, Viewpoint endValue) {
		
		float x=0 , y=0 ;
		/**
		 * move
		 */
		if(endValue.type==Viewpoint.MOVE){
			
			x=endValue.mX;
			y=endValue.mY;

		}else if(endValue.type==Viewpoint.LINE){
			
			
			x = startValue.mX+fraction*(endValue.mX-startValue.mX);
			y = startValue.mY+fraction*(endValue.mY-startValue.mY);
			
		}else if(endValue.type==Viewpoint.CRUVE){
			
			x=startValue.mX*(1-fraction)*(1-fraction)*(1-fraction)+
					3*endValue.c1x*fraction*(1-fraction)*(1-fraction)+
					3*endValue.c2x*fraction*fraction*(1-fraction)+
					endValue.ex*fraction*fraction*fraction;
			
			y=startValue.mY*(1-fraction)*(1-fraction)*(1-fraction)+
					3*endValue.c1y*fraction*(1-fraction)*(1-fraction)+
					3*endValue.c2y*fraction*fraction*(1-fraction)+
					endValue.ey*fraction*fraction*fraction;
		}
		
		return Viewpoint.moveto(x, y);
	}

}
