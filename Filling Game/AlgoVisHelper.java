package com.wyh.jframe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
/**
 * 此类是为了存储关于绘制的工具类
 * */
//创建算法绘制的辅助工具类
public class AlgoVisHelper {
	private AlgoVisHelper() {}
	public static final Color Red = new Color(0xF44336);
    public static final Color Pink = new Color(0xE91E63);
    public static final Color Purple = new Color(0x9C27B0);
    public static final Color DeepPurple = new Color(0x673AB7);
    public static final Color Indigo = new Color(0x3F51B5);
    public static final Color Blue = new Color(0x2196F3);
    public static final Color LightBlue = new Color(0x03A9F4);
    public static final Color Cyan = new Color(0x00BCD4);
    public static final Color Teal = new Color(0x009688);
    public static final Color Green = new Color(0x4CAF50);
    public static final Color LightGreen = new Color(0x8BC34A);
    public static final Color Lime = new Color(0xCDDC39);
    public static final Color Yellow = new Color(0xFFEB3B);
    public static final Color Amber = new Color(0xFFC107);
    public static final Color Orange = new Color(0xFF9800);
    public static final Color DeepOrange = new Color(0xFF5722);
    public static final Color Brown = new Color(0x795548);
    public static final Color Grey = new Color(0x9E9E9E);
    public static final Color BlueGrey = new Color(0x607D8B);
    public static final Color Black = new Color(0x000000);
    public static final Color White = new Color(0xFFFFFF);
	//设置画笔的粗细
	public static void setStrokeWidth(Graphics2D g2d,int w) {
		//设置笔画宽度
		int strokeWidth=w;
		g2d.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));//绘制的线条在端点为圆型，画折线的时候，拐弯位置为圆形
	}
	//设置颜色
	public static void setColor(Graphics g2d,Color color) {
		g2d.setColor(color);
	}
	//画圆形
	public static void strokeCircle(Graphics2D g2d,int x,int y,int r) {//圆心坐标几半径
		
		Ellipse2D circle = new Ellipse2D.Double(x-r,y-r,2*r,2*r);//在窗口里的位置，算包围圈长宽
		g2d.draw(circle);
		
	}
	//填充圆形
	public static void fillCircle(Graphics2D g2d,int x,int y,int r) {//圆心坐标几半径
		
		Ellipse2D circle = new Ellipse2D.Double(x-r,y-r,2*r,2*r);//在窗口里的位置，算包围圈长宽
		g2d.fill(circle);
		
	}
	 //画矩形
	 public static void strokeRectangle(Graphics2D g, int x, int y, int w, int h){

	        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
	        g.draw(rectangle);
	    }
	 //填充矩形
	 public static void fillRectangle(Graphics2D g, int x, int y, int w, int h){

	        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
	        g.fill(rectangle);
	    }
	 
	 //将图像放置进来
	 public static void putImage(Graphics2D g, int x, int y, String imageURL){

	     ImageIcon icon = new ImageIcon(imageURL);
	     Image image = icon.getImage();

	        g.drawImage(image, x, y, null);
	    }

	public static void pause(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			System.out.println("Error.");
		}
	}
	
	
}
