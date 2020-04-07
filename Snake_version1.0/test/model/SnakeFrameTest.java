package model;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description: 测试SnakeFrame类中的gameOver方法
 * @author: chenqi
 * @date: 2020/4/2
 */
public class SnakeFrameTest {
    private SnakeFrame sf;

    @Before
    public void setUp() throws Exception {
        sf = new SnakeFrame();
    }

    @After
    public void tearDown() throws Exception {
        sf = null;
    }

    @org.junit.Test
    public void gameOver() {
        boolean exp = true;
        SnakeFrame sf = new SnakeFrame();

        sf.gameOver();
        boolean res = sf.getBGameOver();

        assertTrue(res==exp);
    }
}