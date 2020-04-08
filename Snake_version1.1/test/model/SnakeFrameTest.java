package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @version V1.1
 * @Title: SnakeFrameTest
 * @Package
 * @Description:
 * @author: yhyf
 * @date: 2020/4/4
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



    @Ignore
    public void getScore() {
    }

    @Ignore
    public void setScore() {
    }

    @Ignore
    public void main() {
    }

    @Ignore
    public void launch() {
    }

    @Test
    public void gameOver() {
        boolean exp = true;
        SnakeFrame sf = new SnakeFrame();

        sf.gameOver();
        boolean res = sf.getBGameOver();

        assertTrue(res==exp);
    }

    @Ignore
    public void update() {
    }

    @Ignore
    public void displaySomeInfor() {
    }

    @Ignore
    public void paint() {
    }

    @Ignore
    public void KeyPressed(){

    }


}