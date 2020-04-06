package model;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collection;
import java.lang.String;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SFKeyPressedTest {
    private int key;
    private boolean pause;
    private boolean bGameOver;
    private boolean expPause;
    private boolean expBGameOver;

    SnakeFrame testobj0;
    SnakeFrame.KeyMonitor testobjKey;
    SnakeFrame.MyPaintThread testobjThread;
    SnakeFrame.KeyMonitor keyMon;
    SnakeFrame.MyPaintThread paintThread;

    KeyEvent e;

    public SFKeyPressedTest(int key, boolean pause, boolean bGameOver, boolean expPause, boolean expBGameOver){
        this.key = key;
        this.pause = pause;
        this.bGameOver = bGameOver;
        this.expPause = expPause;
        this.expBGameOver = expBGameOver;
    }

    @Parameterized.Parameters(name="{index}:SFKeyPressed[{0},{1},{2}]=[{3},{4}]")
    public static Collection testData(){
        return Arrays.asList( new Object[][]{
                {KeyEvent.VK_SPACE,false,false,true,false},
                {KeyEvent.VK_SPACE,true,false,true,false},
                {KeyEvent.VK_B,false,false,false,false},
                {KeyEvent.VK_B,true,false,false,false},
                {KeyEvent.VK_F2,true,true,false,false},
                {KeyEvent.VK_F2,true,false,false,false},
                {KeyEvent.VK_F2,false,false,false,false},
                {KeyEvent.VK_P,false,false,false,false},
                {KeyEvent.VK_O,false,false,false,false},
                {KeyEvent.VK_SPACE,false,false,true,false},
                {KeyEvent.VK_B,true,false,false,false},
                {KeyEvent.VK_F2,true,true,false,false}
        });
    }


    @Before
    public void setUp() throws Exception {
        testobj0 = new SnakeFrame();
        /*testobj0.launch();*/
        /*testobjKey = testobj0.new KeyMonitor();
        testobjThread = testobj0.new MyPaintThread();*/

        /*String sKey = String.valueOf(this.key);
        System.setIn(new ByteArrayInputStream(sKey.getBytes()));*/
        /*testobjKey.setKey(key);
        testobj0.setBGameOver(this.bGameOver);
        testobjThread.setPause(pause);*/
        /*testobj0.getKeyMon().setKey(key);
        testobj0.getPaintThread().setPause(pause);
        testobj0.setBGameOver(bGameOver);*/
        testobj0.setBGameOver(bGameOver);

        //keyMon = testobj0.new KeyMonitor();
        //testobj0.addKeyListener(keyMon);

        /*new Thread(paintThread).start();*/
        //testobj0.getKeyMon().setKey(key);

        testobj0.addKeyListener(testobj0.getKeyMon());
        Robot robot = new Robot();
        robot.keyPress(key);
        //robot.keyRelease(key);

        testobj0.getPaintThread().setPause(pause);

        //testobj0.getKeyMon().keyPressed(e);
        /*paintThreadPool.execute(paintThread);
        paintThread.setPause(pause);*/
    }

    @After
    public void tearDown() throws Exception {
        testobj0 = null;
        SnakeFrame.tested = SnakeFrame.HAVE_NOT_RUN;
    }

    @Test
    public void testSFKeyPressed(){
        boolean resPause = pause;
        boolean resBGameOver = bGameOver;
        do{
            if(SnakeFrame.tested == SnakeFrame.HAVE_RUN){
                resPause = testobj0.getPaintThread().getPause();
                resBGameOver = testobj0.getBGameOver();
                break;
            }
        } while(SnakeFrame.tested == SnakeFrame.HAVE_NOT_RUN);
        assertTrue(expPause==resPause);
        assertTrue(expBGameOver==resBGameOver);
    }
}