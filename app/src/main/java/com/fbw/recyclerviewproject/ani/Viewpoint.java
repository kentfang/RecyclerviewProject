package com.fbw.recyclerviewproject.ani;

public class Viewpoint {
	
	/**
	 * 移动
	 */
	public static final int MOVE=0;
	
	public static final int CRUVE=1;
	
	public static final int LINE=2;
	
	
	public Float mX,mY;
	
	int type;
	
	Float c1x=0f;
	Float c1y=0f;
	Float c2x=0f;Float c2y=0f;Float ex=0f;Float ey=0f;
	
	
	private Viewpoint(int type,Float x,Float y){
		
		this.type=type;
		
		this.mX=x;
		
		this.mY=y;
	}
	

	//path.cubicTo(control1.x, control1.y, control2.x,control2.y, end.x, end.y);
	private Viewpoint(int type,Float c1x,Float c1y,Float c2x,Float c2y,Float ex,Float ey){
		this.type=type;
		this.c1x=c1x;
		this.c1x=c1y;
		this.c2x=c2x;
		this.c2y=c2y;
		this.ex=ex;
		this.ey=ey;
	}
	
	
	
	public static Viewpoint moveto(Float x,Float y){
		
		return new Viewpoint(MOVE, x, y);
	}
	
	public static Viewpoint lineTo(Float x,Float y){
		
		return new Viewpoint(LINE, x, y);
	}
	
	public static Viewpoint curveTo(Float c1x,Float c1y,Float c2x,Float c2y,Float ex,Float ey){
		
		return new Viewpoint(CRUVE, c1x, c1y,c2x, c2y,ex, ey);
	}
	

}
