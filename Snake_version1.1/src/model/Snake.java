package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

 /**
  * @title: Snake
  * @author: chenqi
  * @description: 蛇类
  * @date: 2020/4/3
  */
public class Snake {

	private static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
	private static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HEIGHT;
	private static final int ROW_LIMIT = 2;
	private static final int COL_LIMIT = 0;

	private Node head = null;
	private Node tail = null;

	private SnakeFrame sf;
	private Node node = new Node(3,4,Direction.D);

	private int size = 0;

	private int keyCode;


	public Snake(SnakeFrame sf) {
		head = node;
		tail = node;
		size ++;
		this.sf = sf ;



	}


	public void draw(Graphics g){
		/**if(head==null){

			return ;
		}**/
		move();
		for(Node node = head;node!=null;node = node.next){
			node.draw(g);
		}
	}
	/**
	 * 先在头部添加一个节点，然后删除尾部的节点，完成移动
	 */
	public void move() {
		addNodeInHead();
		//检查是否死亡
		checkDead();
		deleteNodeInTail();
	}

	private void checkDead() {
		//头结点的边界检查
		if((head.row < ROW_LIMIT) || (head.row > SnakeFrame.ROW) || (head.col < COL_LIMIT) || (head.col > SnakeFrame.COL)){
			this.sf.gameOver();
		}

		//头结点与其它结点相撞
		for(Node node =head.next;node!=null;node = node.next){
			if(head.row==node.row&&head.col == node.col){
				this.sf.gameOver();
			}
		}
	}

	private void deleteNodeInTail() {
		Node node = tail.pre;
		tail = null;
		node.next = null;
		tail = node;
	}

	private void addNodeInHead() {
		Node node = null;
		switch(head.dir){
		case L:
			node = new Node(head.row,head.col-1,head.dir);
			break;
		case U:
			node = new Node(head.row-1,head.col,head.dir);
			break;
		case R:
			node = new Node(head.row,head.col+1,head.dir);
			break;
		case D:
			node = new Node(head.row+1,head.col,head.dir);
			break;
			default:
				;

		}

		node.next = head;
		head.pre = node;
		head = node;

	}

	public int getkeyCode(){
		return this.sf.getKeyMonitor().getKey();
	}

	private Direction switchHeadDir(int keyCode,Direction HeadDir){
		Direction nextDir = HeadDir;
		switch(keyCode){
			case KeyEvent.VK_LEFT :
				if(HeadDir!=Direction.R){
					nextDir = Direction.L;
				}
				break;
			case KeyEvent.VK_UP :
				if(HeadDir!=Direction.D){
					nextDir = Direction.U;
				}
				break;
			case KeyEvent.VK_RIGHT :
				if(HeadDir!=Direction.L){
					nextDir = Direction.R;
				}
				break;
			case KeyEvent.VK_DOWN :
				if(HeadDir!=Direction.U){
					nextDir = Direction.D;
				}
				break;
			default:
				;
		}
		return nextDir;

	}
	public void keyPressed() {

		keyCode = getkeyCode();
		head.dir = switchHeadDir(keyCode,head.dir);

	}

	/**用于碰撞检测*/
	public Rectangle getRect(int x,int y){
		return new Rectangle(x*BLOCK_WIDTH, y*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);

	}


	public boolean eatEgg(Egg egg){
		/**碰撞检测*/
		if(this.getRect(head.col,head.row).intersects(egg.getRect(egg.getCol(),egg.getRow()))){
			addNodeInHead();
			egg.reAppear();
			sf.setScore(sf.getScore()+5);
			return true;
		}
		else{
			return false;
		}
	}

	 /**
	  * @title: Node
	  * @author: yhyf
	  * @description: 蛇节点类
	  * @date: 2020/4/3
	  */
	public class Node {


		 public int getRow() {
			 return row;
		 }

		 public int getCol() {
			 return col;
		 }

		 /**
		 * 每个节点的位置
		 */
		private int row;
		private int col;

		 public Direction getDir() {
			 return dir;
		 }

		 /**方向*/
		private Direction dir ;

		private Node pre;

		 public Node getNext() {
			 return next;
		 }

		 public void setNext(Node next) {
			 this.next = next;
		 }

		 private Node next;

		public Node(int row, int col, Direction dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(col*BLOCK_WIDTH, row*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
			g.setColor(c);
		}
	}


}
