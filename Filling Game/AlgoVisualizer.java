package com.wyh.jframe;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * 创建一个控制器类，将数据和层Main和视图层AlgoFrame连接起来
 * 从而进行封装:控制层
 * */
public class AlgoVisualizer {
	/**创建自己的数据*/
	private Circle[] circles;/**数据*/
	private AlgoFrame frame;/**视图*/
	private boolean isAnimated=true;//当前动画是否在执行
	
	public AlgoVisualizer(int sceneWidth,int sceneHeight,int N)	{
		/**初始化数据*/
		circles = new Circle[N];
		int R=50;
		for(int i=0;i<N;i++) {
			int x=(int)(Math.random()*(sceneWidth-2*R))+R;
			int y=(int)(Math.random()*(sceneHeight-2*R))+R;
			int vx=(int)(Math.random()*11)-5;//速度取-5～5
			int vy=(int)(Math.random()*11)-5;
			circles[i]=new Circle(x,y,R,vx,vy);
		}
		/**初始化视图*/
		EventQueue.invokeLater(()->{//为了将GUI创建的代码放进新的线程中，官方推荐的创建窗口的写法
			
			frame = new AlgoFrame("Welcome",sceneWidth,sceneHeight);//传入我们自己写的窗口
			/**根据情况决定是否加入键盘鼠标监听器*/
			frame.addKeyListener(new AlgoKeyListener());//调用键盘监听，并添加，这样AlgoFrame才知道有这样的事件
			frame.addMouseListener(new AlgoMouseListener());//鼠标
			new Thread(()->{//创建一个线程，用线程执行任务
				run();//
			}).start();
			
		});
	}
	
	/**对于动画逻辑进行封装，动画逻辑*/
	private void run() {
		while(true) {
			//绘制数据
			frame.render(circles);
			AlgoVisHelper.pause(20);//sleep
			//更新数据
			if(isAnimated)//动画是否在执行
				for(Circle circle:circles) {
					circle.move(0,0,frame.getCanvasWidth(),frame.getCanvasHeight());
			}
		}
	}
	//添加键盘的相应事件
	private class AlgoKeyListener extends KeyAdapter{
		@Override
		public void keyReleased(KeyEvent event) {
			if(event.getKeyChar()==' ')
				isAnimated=!isAnimated;//停顿
		}
	}
	//添加鼠标的相应事件
	private class AlgoMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent event) {
			event.translatePoint(0, -(frame.getBounds().height-frame.getCanvasHeight()));//对鼠标事件传进来的点进行位移,排除菜单bar的长度
			//System.out.println(event.getPoint());
			for(Circle circle:circles)
				if(circle.contain(event.getPoint()))//鼠标点击所在的点是否包含在圆圈中
					circle.isFilled=!circle.isFilled;//那么就填充
		}
	}
	/**main函数*/
	public static void main(String[] args) {
		
		int sceneWidth = 800;
		int sceneHeight = 800;
		
		int N = 10;//圆形的个数
		
		//将其全部封装在这里
		AlgoVisualizer visualizer=new AlgoVisualizer(sceneWidth,sceneHeight,N);
		
	}
}
