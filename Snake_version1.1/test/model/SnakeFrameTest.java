package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.awt.event.KeyEvent;
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
public class SnakeFrameTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getScore() {
    }

    @Test
    public void setScore() {
    }

    @Test
    public void main() {
    }

    @Test
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

    @Test
    public void update() {
    }

    @Test
    public void displaySomeInfor() {
    }

    @Test
    public void paint() {
    }

    @Test
    public void KeyPressed(){

    }
}