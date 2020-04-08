package model;

import org.junit.Test;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description: 测试egg类中的changeColor方法
 * @author: yhyf
 * @date: 2020/4/2
 */
public class EggTest {
    private Egg egg;
    private SnakeFrame sf;
    private Graphics g ;

    @org.junit.Before
    public void setUp() throws Exception {
        egg = new Egg();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        egg = null;
    }

    @org.junit.Ignore
    public void reAppear() {
    }

    @org.junit.Test
    public void changeColorTest1() throws InvocationTargetException,NoSuchMethodException,NoSuchFieldException,IllegalAccessException{
        Method method = egg.getClass().getDeclaredMethod("changeColor", null);
        method.setAccessible(true);
        Field field = egg.getClass().getDeclaredField("color");
        field.setAccessible(true);
        field.set(egg,Color.RED);
        method.invoke(egg, null);
        Object after = field.get(egg);
        assertEquals(Color.BLUE,after);
    }

    @org.junit.Test
    public void changeColorTest2() throws InvocationTargetException,NoSuchMethodException,NoSuchFieldException,IllegalAccessException{
        Method method = egg.getClass().getDeclaredMethod("changeColor", null);
        method.setAccessible(true);
        Field field = egg.getClass().getDeclaredField("color");
        field.setAccessible(true);
        field.set(egg,Color.BLUE);
        method.invoke(egg, null);
        Object after = field.get(egg);

        assertEquals(Color.RED,after);



    }

    @org.junit.Ignore
    public void draw() throws IllegalArgumentException, IllegalAccessException,NoSuchFieldException {
        Field field = egg.getClass().getDeclaredField("color");
        field.setAccessible(true);
        Object after = field.get(egg);
        System.out.print(after);
        egg.draw(g);
        Field field_ = egg.getClass().getDeclaredField("color");
        field_.setAccessible(true);
        Object after_ = field.get(egg);
        System.out.print(after);
        System.out.print(after_);

    }

    @org.junit.Ignore
    public void getRect() {
    }
}