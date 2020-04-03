package com.wyh.splitmoney;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
	private static int DELY = 40;
    private int[] money;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight){

        // 初始化数据
        // TODO: 初始化数据
    	money=new int[100];
    	for(int i=0;i<money.length;i++)
    		money[i]=100;//开始的时候每个人手里都有100快

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        // TODO: 编写自己的动画逻辑
	    	while(true) {
	    		Arrays.sort(money);//先排序
	    		frame.render(money);
	    		AlgoVisHelper.pause(DELY);
	    		for(int k=0;k<50;k++) {//进行k轮模拟，使得速度加快50倍
	    			for(int i=0;i<money.length;i++) {
		    			//if(money[i]>0) {
		    				int j=(int)(Math.random()*money.length);//模拟随机的另一个人
		    				money[i]-=1;//给钱
		    				money[j]+=1;//收到钱	
		    			//}
	    			}
	    	}
    	}
    }

    

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 800;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
