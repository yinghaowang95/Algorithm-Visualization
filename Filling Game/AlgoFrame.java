package com.wyh.jframe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 1.构建自己的窗口:视图层
 * */
public class AlgoFrame extends JFrame {
	//让长和宽不可以随便修改
	private int canvasWidth;
	private int canvasHeight;
	//构建属于自己的窗口
	public AlgoFrame(String title,int canvasWidth,int canvasHeight) {//窗口中真正可以绘制的区域的宽和高
		super(title);//调用父类的构造函数
		this.canvasWidth = canvasWidth;//传入的长宽赋值给这
		this.canvasHeight = canvasHeight;
		
		AlgoCanvas canvas = new AlgoCanvas();//创建了画布对象之后，下面的画布长和宽就要在这里面进行修改
		//canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));//将长和宽包裹在dimension里,这句话以及在下面重写方法，故不用
		this.setContentPane(canvas);//设置窗口的内容面板，让设置好的canvas成为窗口的内容面板
		pack();//根据加载进窗口canvas的内容，自动调整AlgoFrame窗口的大小，否则创建的窗口就为0
		
		//this.setSize(canvasWidth,canvasHeight);//这里以及以下可以不写this
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	public AlgoFrame(String title) {
		this(title,1024,768);//调用上面部分的方法，计算当前屏幕的宽和高，直接构造出沾满全屏幕的窗口
	}
	//用户可以获取长和宽
	public int getCanvasWidth() {
		return canvasWidth;
	}
	public int getCanvasHeight() {
		return canvasHeight;
	}
	
	private Circle[] circles;
	/**2.根据传进来的数据进行渲染，要设置相应的数据类型*/
	public void render(Circle[] circles) {
		this.circles=circles;
		this.repaint();/**将jframe中所有的控件重新刷新一遍，自动调用AlgoCanvas类*/
	}
	
	//创建画布类继承画板，因为各类元素和内容的添加不可以在JFrame中只可以在JPanel中
	private class AlgoCanvas extends JPanel{
		
		public AlgoCanvas() {
			super(true);//isDoubleBufferred,打开双缓存
		}
		//对于JPanel来说，有个绘制组件，用来绘制,返回值是void类型
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2d=(Graphics2D)g;//将Graphics强制转化为Graphics2D，为了更好的绘制
			
			//抗锯齿
			RenderingHints hints=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);//打开抗锯齿
			
			g2d.addRenderingHints(hints);//添加抗锯齿，这里完全是框架提供的方式
			
			/**3.具体绘制，将具体绘制的工具和绘制什么分开了*/
			//设置笔画宽度，调用AlgoVisHelper中的方法
			AlgoVisHelper.setStrokeWidth(g2d, 1);		
			AlgoVisHelper.setColor(g2d,Color.RED);
			for(Circle circle:circles) //传入多个圆形
				if(!circle.isFilled)
					AlgoVisHelper.strokeCircle(g2d, circle.x,circle.y, circle.getR());
				else
					AlgoVisHelper.fillCircle(g2d, circle.x,circle.y, circle.getR());
			
			
			
			
		}
		
		@Override
		public Dimension getPreferredSize() {//返回画布的大小
			return new Dimension(canvasWidth,canvasHeight);
		}
	}
}
