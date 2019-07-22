package com.fbw.recyclerviewproject.ani;

import java.util.ArrayList;

public class AnimatorPath {

	
	private ArrayList<Viewpoint> viewpoints = new ArrayList<Viewpoint>();
	
	
	
	public void moveTo(Float x,Float y){
		viewpoints.add(Viewpoint.moveto(x, y));
	}
	
	public void lineTo(Float x,Float y){
		
		viewpoints.add(Viewpoint.lineTo(x, y));
		
	}
	
	public void curveTo(Float c1x,Float c1y,Float c2x,Float c2y,Float ex,Float ey){
		
		viewpoints.add(Viewpoint.curveTo(c1x, c1y,c2x, c2y,ex, ey));
		
	}
	
	public ArrayList<Viewpoint> getPoints(){
		
		return viewpoints;
	}
	
}
