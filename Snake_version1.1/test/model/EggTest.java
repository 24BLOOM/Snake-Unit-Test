package model;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @version V1.1
 * @Title: EggTest
 * @Package
 * @Description:
 * @author: chenqi
 * @date: 2020/4/3
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
    public void draw() {

    }

    @org.junit.Ignore
    public void getRect() {
    }
}