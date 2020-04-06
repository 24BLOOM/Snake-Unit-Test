package model;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


 /**
  * @title: SnakeFrame
  * @author: chenqi
  * @description: 游戏界面类
  * @date: 2020/3/31
  */
public class SnakeFrame extends Frame{
	/**方格的宽度和长度*/
	public static final int BLOCK_WIDTH = 15 ;
	public static final int BLOCK_HEIGHT = 15 ;
	/**界面的方格的行数和列数*/
	public static final int ROW = 40;
	public static final int COL = 40;
	
	/**得分*/
	private int score = 0;
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**缓冲图像*/
	private Image offScreenImage = null;
	
	private Snake snake = new Snake(this);
	
	private Egg egg = new Egg();
	
	public static void main(String[] args) {
		new SnakeFrame().launch();
	}

	 /**
	  * @title: launch
	  * @description: 启动游戏
	  * @param: []
	  * @return: void
	  * @date: 2020/3/31
	  * @throws
	  */

	public void launch(){
		
		this.setTitle("Snake");
		this.setSize(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
		this.setLocation(30, 40);
		this.setBackground(Color.WHITE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.setResizable(false);
		this.setVisible(true);
		
		//为界面添加监听事件
		this.addKeyListener(new KeyMonitor());
		
		new Thread(new MyPaintThread()).start();
	}
	
	
	private boolean bGameOver = false;
	
	public void gameOver(){
		bGameOver = true;
	}

	 /**
	  * @title: update
	  * @description: 重写updata方法 更新画布
	  * @param: [g]
	  * @return: void
	  * @date: 2020/3/31
	  * @throws
	  */

	@Override
	public void update(Graphics g) {
		/**双缓冲解决闪烁*/
		if(offScreenImage==null){
			offScreenImage = this.createImage(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
		}
		Graphics offg = offScreenImage.getGraphics();
		//先将内容画在虚拟画布上
		paint(offg);
		//然后将虚拟画布上的内容一起画在画布上
		g.drawImage(offScreenImage, 0, 0, null);
		
		if(bGameOver){
			g.drawString("游戏结束！！！", ROW/2*BLOCK_HEIGHT, COL/2*BLOCK_WIDTH);
		}
		
		snake.draw(g);
		boolean bSuccess=snake.eatEgg(egg);
		//吃一个加5分
		if(bSuccess){
			score+=5;
		}
		egg.draw(g);
		g.drawString("得分:"+score, 5*BLOCK_HEIGHT, 5*BLOCK_WIDTH);
		
	}

	 /**
	  * @title: paint
	  * @description: 画图
	  * @param: [g]
	  * @return: void
	  * @date: 2020/4/5
	  * @throws
	  */
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		/*
		 * 将界面画成由ROW*COL的方格构成,两个for循环即可解决
		 * */
		for(int i = 0;i<ROW;i++){
			g.drawLine(0, i*BLOCK_HEIGHT, COL*BLOCK_WIDTH,i*BLOCK_HEIGHT );
		}
		for(int i=0;i<COL;i++){
			g.drawLine(i*BLOCK_WIDTH, 0 , i*BLOCK_WIDTH ,ROW*BLOCK_HEIGHT);
		}
		
		g.setColor(c);
	}



	 /**
	  * @title: MyPaintThread
	  * @author: chenqi
	  * @description: 画图线程类
	  * @date: 2020/3/31
	  */
	private class MyPaintThread implements Runnable{
		private boolean running = true;
		@Override
		public void run() {
			while(running){
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

	 /**
	  * @title: KeyMonitor
	  * @author: chenqi
	  * @description: 键盘监听
	  * @date: 2020/3/31
	  */
	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			snake.keyPressed(e);
		}
		
	}
	
}
