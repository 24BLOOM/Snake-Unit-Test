package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

 /**
  * @title: Egg
  * @author: yhyf
  * @description: 食物类
  * @date: 2020/3/30
  */
public class Egg {
	/**所在的位置*/
	private int row;
	private int col;
	/**大小*/
	private static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
	private static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HEIGHT;
	
	private static final Random R = new Random();
	
	private Color color = Color.RED;
	
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow(){
		return this.row;
	}
	public int getCol()
	{
		return this.col;
	}

	public Egg() {
		this((R.nextInt(SnakeFrame.ROW-2))+2,(R.nextInt(SnakeFrame.COL-2))+2);
	}

	/**随机产生egg*/
	public void reAppear(){
		this.row = (R.nextInt(SnakeFrame.ROW-2))+2;
		this.col = (R.nextInt(SnakeFrame.COL-2))+2;
	} 

	public void changeColor(){
		/**改变下一次的颜色*/
		if(color==Color.RED){
			color = Color.BLUE;
		}
		else{
			color = Color.RED;
		}
	}

	public void draw(Graphics g){
		Color c= g.getColor();
		g.setColor(color);
		g.fillOval(col*BLOCK_WIDTH, row*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
		g.setColor(c);
		changeColor();
		
	}

	/**用于碰撞检测*/
	public Rectangle getRect(int x,int y){
		return new Rectangle(x*BLOCK_WIDTH, y*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
	}
	
}
