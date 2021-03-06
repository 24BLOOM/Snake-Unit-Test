package model;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

 /**
  * @title: SnakeFrame
  * @author: chenqi
  * @description: 游戏界面类
  * @date: 2020/4/3
  */
public class SnakeFrame extends Frame{

	public static final int HAVE_RUN = 1;
	public static final int HAVE_NOT_RUN = 0;

	 /**是否运行键盘响应*/
	public static int tested = HAVE_NOT_RUN;
	/**是否更新*/
	public static int isUpdated = HAVE_NOT_RUN;

	/**方格的宽度和长度*/
	public static final int BLOCK_WIDTH = 15 ;
	public static final int BLOCK_HEIGHT = 15 ;
	/**界面的方格的行数和列数*/
	public static final int ROW = 40;
	public static final int COL = 40;

	/**构造方法*/
	public SnakeFrame(){}

	/**得分*/
	private int score = 0;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	private MyPaintThread paintThread = new MyPaintThread();
	public MyPaintThread getPaintThread() {
		 return paintThread;
	}

	 /**线程池*/
	ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
			.setNameFormat("demo-pool-%d").build();
	ThreadPoolExecutor  paintThreadPool = new ThreadPoolExecutor(1, 1,
			0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

	/**缓冲图像*/
	private Image offScreenImage = null;
	public void setOffScreenImage(Image offScreenImage) {
		this.offScreenImage = offScreenImage;
	}

	private Snake snake = new Snake(this);
	
	private Egg egg = new Egg();
	
	private static SnakeFrame sf =null;

	KeyMonitor keyMonitor = new KeyMonitor();

	public KeyMonitor getKeyMonitor() {
		 return keyMonitor;
	}


	public static void main(String[] args) {
		sf = new SnakeFrame();
		sf.launch();
	}

	/**
	 * @title: launch
	 * @description: 启动游戏
	 * @param: []
	 * @return: void
	 * @date: 2020/4/3
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
		this.addKeyListener(keyMonitor);

		paintThreadPool.execute(paintThread);

	}
	
	
	private static boolean bGameOver = false;

	public void gameOver(){
		bGameOver = true;
	}

	public boolean getBGameOver() {
		return bGameOver;
	}

	public void setBGameOver(boolean bGameOver) {
		SnakeFrame.bGameOver = bGameOver;
	}


	 /**
	  * @title: update
	  * @description: 重写updata方法 更新画布
	  * @param: [g]
	  * @return: void
	  * @date: 2020/4/3
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
			paintThread.dead();
		}
		
		snake.draw(g);
		snake.eatEgg(egg);


		egg.draw(g);
		displaySomeInfor(g);


		
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

		 /* 将界面画成由ROW*COL的方格构成,两个for循环即可解决*/
		 for(int i = 0;i<ROW;i++){
			 g.drawLine(0, i*BLOCK_HEIGHT, COL*BLOCK_WIDTH,i*BLOCK_HEIGHT );
		 }
		 for(int i=0;i<COL;i++){
			 g.drawLine(i*BLOCK_WIDTH, 0 , i*BLOCK_WIDTH ,ROW*BLOCK_HEIGHT);
		 }

		 g.setColor(c);
	 }



	 /**
	  * @title: displaySomeInfor
	  * @description: 在界面上显示一些提示信息
	  * @param: [g]
	  * @return: void
	  * @date: 2020/4/3 21:44
	  * @throws
	  */

	public void displaySomeInfor(Graphics g){

		Color c = g.getColor();
		g.setColor(Color.RED);
		g.drawString("使用说明:空格键---暂停，按键B---暂停后开始,F2---重新开始", 5*BLOCK_HEIGHT, 3*BLOCK_WIDTH);
		g.drawString("得分:"+score, 5*BLOCK_HEIGHT, 5*BLOCK_WIDTH);		
		g.setColor(c);
		
	}



	 /**
	  * @title: MyPaintThread
	  * @author: chenqi
	  * @description: 画图线程类
	  * @date: 2020/4/3
	  */

	protected class MyPaintThread implements Runnable{

		//running不能改变，改变后则线程结束
		private static final boolean RUNNING = true;
		private boolean  pause = false;

		/**构造方法*/
		public MyPaintThread(){}

		public boolean getPause() {
			 return pause;
		}

		public void setPause(boolean pause) {
			 this.pause = pause;
		}

		@Override
		public void run() {
			while(RUNNING){
				/*如果pause 为true ，则暂停*/
				if(pause){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}

				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}

		 /**
		  * @title: pause
		  * @description: 暂停游戏
		  * @param: []
		  * @return: void
		  * @date: 2020/4/3
		  * @throws
		  */
		public void pause(){

			pause = true;
		}

		 /**
		  * @title: recover
		  * @description: 从暂停中恢复
		  * @param: []
		  * @return: void
		  * @date: 2020/4/5
		  * @throws
		  */

		public void recover(){

			pause = false;
		}

		 /**
		  * @title: dead
		  * @description: 游戏结束，死亡,只能设置pause 为true，不能设置running = false，这样就导致重画的线程结束了，否则不能重新开始
		  * @param: []
		  * @return: void
		  * @date: 2020/4/5
		  * @throws
		  */

		public void dead(){
			pause = true;

		}

		 /**
		  * @title: reStart
		  * @description: 重新开始
		  * @param: []
		  * @return: void
		  * @date: 2020/4/3
		  * @throws
		  */
		public void reStart(SnakeFrame sf){

			bGameOver = false;

			this.pause = false;
			System.out.println(sf);
			snake = new Snake(sf);

			sf.setScore(0);


	
		}

		public void switchStatus(int keyCode,SnakeFrame sf){
			if(keyCode == KeyEvent.VK_SPACE){//暂停

				paintThread.pause();
			}
			else if(keyCode == KeyEvent.VK_B){//开始

				paintThread.recover();
			}
			else if(keyCode == KeyEvent.VK_F2){//再开一局

				paintThread.reStart(sf);
			}
			else{
				snake.keyPressed();
			}
		}
		
	}


	 /**
	  * @title: KeyMonitor
	  * @author: chenqi
	  * @description: 键盘监听
	  * @date: 2020/4/3
	  */
	protected class KeyMonitor extends KeyAdapter{
		int key;
		public void setKey(int key) {
			 this.key = key;
		}
		public int getKey() {
			 return key;
		}

		 /**构造方法*/
		public KeyMonitor(){}

		@Override
		public void keyPressed(KeyEvent e) {
			key = e.getKeyCode();
			paintThread.switchStatus(key,sf);

		}
		
	}
	
}
