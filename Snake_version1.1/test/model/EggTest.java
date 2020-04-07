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
 * @Description: 食物测试类
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
    }

    @org.junit.Test
    public void reAppear() {
    }

    @org.junit.Test
    public void changeColor() throws InvocationTargetException,NoSuchMethodException,NoSuchFieldException,IllegalAccessException{
        Method method = egg.getClass().getDeclaredMethod("changeColor", null);
        method.setAccessible(true);
        Field field = egg.getClass().getDeclaredField("color");
        field.setAccessible(true);
        Object after = field.get(egg);
        System.out.print(after);
        method.invoke(egg, null);
        after = field.get(egg);
        System.out.print(after);



    }

    @org.junit.Test
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

    @org.junit.Test
    public void getRect() {
    }
}