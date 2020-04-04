package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * @author yhyf
 */
public class Egg {
	/* ���ڵ�λ�� */
	private int row;
	private int col;
	/* ��С */
	private static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
	private static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HEIGHT;
	
	private static final Random R = new Random();
	
	private Color color = Color.RED;
	
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Egg() {
		this((R.nextInt(SnakeFrame.ROW-2))+2,(R.nextInt(SnakeFrame.COL-2))+2);
	}
	
	public void reAppear(){
		this.row = (R.nextInt(SnakeFrame.ROW-2))+2;
		this.col = (R.nextInt(SnakeFrame.COL-2))+2;
	} 
	
	public void draw(Graphics g){
		Color c= g.getColor();
		g.setColor(color);
		g.fillOval(col*BLOCK_WIDTH, row*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
		g.setColor(c);
		//�ı���һ�ε���ɫ
		if(color==Color.RED){
			color = Color.BLUE;
		}
		else{
			color = Color.RED;
		}
		
	}
	/* ������ײ��� */
	public Rectangle getRect(){
		return new Rectangle(col*BLOCK_WIDTH, row*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
	}
	
}
