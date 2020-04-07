package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description: 测试Snake类中的checkDead方法
 * @author: yhyf
 * @date: 2020/4/3
 */
@RunWith(Parameterized.class)
public class SnakeParam2Test {
    private int headRow;
    private int headCol;
    private int nextRow;
    private int nextCol;
    private boolean isDead;

    SnakeFrame sf;
    Snake testobj;

    public SnakeParam2Test(int headRow,int headCol,int nextRow,int nextCol,boolean isDead){
        this.headRow = headRow;
        this.headCol = headCol;
        this.nextRow = nextRow;
        this.nextCol = nextCol;
        this.isDead = isDead;
    }

    @Parameterized.Parameters(name="{index}:checkDead[{0},{1},{2},{3}]={4}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {41,20,40,20,true},
                {31,30,30,30,false},
                {20,20,20,20,true},
                {20,21,20,20,false},
                {21,20,20,20,false},
                {20,20,21,21,false}
        });
    }

    @Before
    public void setUp() throws Exception {
        sf = new SnakeFrame();
        testobj = new Snake(sf);
        sf.setBGameOver(false);

        Snake.Node headNode = testobj.new Node(headRow,headCol,Direction.R);
        Snake.Node nextNode = testobj.new Node(nextRow,nextCol,Direction.R);

        testobj.setHead(headNode);
        testobj.getHead().setNext(nextNode);
        testobj.getHead().getNext().setNext(null);
    }

    @After
    public void tearDown() throws Exception {
        testobj = null;
        sf = null;
    }

    @Test
    public void testCheckDead(){
        testobj.checkDead();
        assertTrue(isDead == testobj.getSf().getBGameOver());
    }
}