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

public class SnakeTest {
    private Snake snake;
    private SnakeFrame sf;
    private Egg egg;






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
    public void draw() {
    }

    @Test
    public void move() {
    }


    @Ignore
    public void keyPressed() throws NoSuchFieldException,IllegalAccessException{


    }

    @Test
    public void getRect() {
    }

    @Test
    public  void eatEggTest1() throws  NoSuchFieldException,IllegalAccessException{

        egg = new Egg(15,20);
        Field headField = snake.getClass().getDeclaredField("head");
        headField.setAccessible(true);
        Field nodeField = snake.getClass().getDeclaredField("head");
        headField.set(snake,snake.new Node(15,20,Direction.D));
        sf.setScore(0);
        boolean result_ = snake.eatEgg(egg);
        int result_score = sf.getScore();
        assertEquals(true,result_);
        assertEquals(5,result_score);

    }

    @Test
    public void eatEggTest2() throws  NoSuchFieldException,IllegalAccessException{
        egg = new Egg(20,20);
        Field headField = snake.getClass().getDeclaredField("head");
        headField.setAccessible(true);
        Field nodeField = snake.getClass().getDeclaredField("head");
        headField.set(snake,snake.new Node(15,15,Direction.D));
        sf.setScore(0);
        boolean result_ = snake.eatEgg(egg);
        int result_score = sf.getScore();
        assertEquals(false,result_);

        assertEquals(0,result_score);

    }


}