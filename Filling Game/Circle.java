package com.wyh.jframe;

import java.awt.Point;

/**设置圆圈类，然后在AlgoFrame里面绘制*/
public class Circle {
	public int x,y;
	private int r;
	public int vx,vy;//速度
	public boolean isFilled=false;
	public Circle(int x, int y, int r, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.vx = vx;
		this.vy = vy;
	}
	public int getR() {
		return r;
	}
	public void move(int minx,int miny,int maxx,int maxy) {
		x+=vx;
		y+=vy;
		checkCollision(minx,miny,maxx,maxy);
	}
	//碰撞检测
	private void checkCollision(int minx,int miny,int maxx,int maxy) {
		if(x-r<minx) {x=r;        vx=-vx;}
		if(x+r>=maxx) {x=maxx-r;  vx=-vx;}
		if(y-r<miny) {x=r;        vy=-vy;}
		if(y+r>=maxx) {x=maxy-r;  vy=-vy;}
	}
	public boolean contain(Point p) {
		return (x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)<=r*r;//是否在圆内：鼠标点击坐标离圆心的距离
	}
}
