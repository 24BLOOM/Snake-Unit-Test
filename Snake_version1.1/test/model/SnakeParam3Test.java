package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * @version V1.1
 * @Title: SnakeParam3Test
 * @Package
 * @Description: 使用参数化测试方法对checkDead()编写测试脚本
 * @author: chenqi
 * @date: 2020/4/5
 */

@RunWith(Parameterized.class)
public class SnakeParam3Test {

    private int headRow;
    private int headCol;
    private int nextRow;
    private int nextCol;
    private boolean isDead;

    SnakeFrame sf;
    Snake snake;

    public SnakeParam3Test(int headRow,int headCol,int nextRow,int nextCol,boolean isDead){
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
        snake = new Snake(sf);





    }

    @After
    public void tearDown() throws Exception {
        snake = null;
        sf = null;
    }

    @Test
    public void testCheckDead()throws InvocationTargetException,NoSuchFieldException,IllegalAccessException,NoSuchMethodException{

        sf.setBGameOver(false);
        Snake.Node headNode = snake.new Node(headRow,headCol,Direction.R);
        Snake.Node nextNode = snake.new Node(nextRow,nextCol,Direction.R);


        Field headField = snake.getClass().getDeclaredField("head");
        headField.setAccessible(true);
        headField.set(snake,headNode);
        Snake.Node after = (Snake.Node)headField.get(snake);
        after.setNext(nextNode);
        after.getNext().setNext(null);

        Method method = snake.getClass().getDeclaredMethod("checkDead" ,null);
        method.setAccessible(true);

        method.invoke(snake,null);
        assertTrue(isDead == sf.getBGameOver());
    }

}


