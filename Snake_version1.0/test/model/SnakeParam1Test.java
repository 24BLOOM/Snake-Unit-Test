package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description: 测试Snake类中的switchHeadDir方法
 * @author: yhyf
 * @date: 2020/4/2
 */
@RunWith(Parameterized.class)
public class SnakeParam1Test {
    private Snake snake;
    private SnakeFrame sf;

    public SnakeParam1Test(int keyCode,Direction headDir,Direction expectHeadDir){
        this.keyCode = keyCode;
        this.headDir = headDir;
        this.expectHeadDir = expectHeadDir;
    }

    private int keyCode;
    private Direction headDir;
    private  Direction expectHeadDir;

    @Parameterized.Parameters(name="{index}:switchHeadDir[{0},{1}]={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {KeyEvent.VK_LEFT,Direction.D,Direction.L},
                {KeyEvent.VK_UP,Direction.R,Direction.U},
                {KeyEvent.VK_RIGHT,Direction.R,Direction.R},
                {KeyEvent.VK_DOWN,Direction.R,Direction.D},
                {KeyEvent.VK_LEFT,Direction.L,Direction.L},
                {KeyEvent.VK_UP,Direction.U,Direction.U},
                {KeyEvent.VK_RIGHT,Direction.R,Direction.R},
                {KeyEvent.VK_DOWN,Direction.D,Direction.D}

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
    public void switchHeadDir() throws InvocationTargetException,NoSuchFieldException,IllegalAccessException,NoSuchMethodException{

        Class [] parameterTypes = {int.class,Direction.class};
        Method method = snake.getClass().getDeclaredMethod("switchHeadDir" ,parameterTypes);
        method.setAccessible(true);
        Object[] parameter = {keyCode,headDir};
        Direction result = (Direction) method.invoke(snake,parameter);
        assertEquals(expectHeadDir,result);

    }



}