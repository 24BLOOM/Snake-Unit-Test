package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @version V1.1
 * @Title:
 * @Package
 * @Description:
 * @author: chenqi
 * @date:
 */
@RunWith(Parameterized.class)
public class SnakeParam2Test {
    private Snake snake;
    private SnakeFrame sf;
    private Egg egg;

    public SnakeParam2Test(Direction headDir,int col,int row,int expRow,int expCol){
        this.headDir = headDir;
        this.row = row;
        this.col = col;
        this.expRow = expRow;
        this.expCol = expCol;

    }
    /**
     @Parameterized.Parameter
     public int keyCode;


     @Parameterized.Parameter(1)
     public Direction headDir;

     @Parameterized.Parameter(2)
     public Direction expectHeadDir;
     **/

    private Direction headDir;
    private  int row;
    private  int col;

    private int expCol;
    private int expRow;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Direction.L,2,2,2,1},
                {Direction.U,2,2,1,2},
                {Direction.R,2,2,2,3},
                {Direction.D,2,2,3,2}

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
        egg = null;
    }

    @Test
    public void addNodeInHead() throws InvocationTargetException,NoSuchFieldException,IllegalAccessException,NoSuchMethodException{

        Method method = snake.getClass().getDeclaredMethod("addNodeInHead" ,null);
        method.setAccessible(true);
        Field headField = snake.getClass().getDeclaredField("head");
        headField.setAccessible(true);
        headField.set(snake,snake.new Node(row,col,headDir));
        method.invoke(snake,null);
        Snake.Node after = (Snake.Node)headField.get(snake);
        System.out.println("Col");
        assertEquals(expCol,after.getCol());
        System.out.println("Row");
        assertEquals(expRow,after.getRow());
        System.out.println("Direction");
        assertEquals(headDir,after.getDir());

    }



}