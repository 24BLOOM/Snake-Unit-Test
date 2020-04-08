package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @version V1.1
 * @Title: SnakeFrameParamTest1
 * @Package
 * @Description: 使用参数化测试方法为switchStatus()编写测试脚本
 * @author: chenqi
 * @date: 2020/4/4
 */
@RunWith(Parameterized.class)
public class SnakeFrameParam1Test {
    private SnakeFrame sf;

    public SnakeFrameParam1Test(boolean pause,boolean bGameOver,int keyCode,boolean expPause,boolean expBGameOver){
        this.pause = pause;
        this.bGameOver = bGameOver;

        this.keyCode = keyCode;
        this.expPause = expPause;
        this.expBGameOver = expBGameOver;

    }

    private boolean pause;
    private boolean bGameOver;

    private int keyCode;
    private boolean expPause;
    private boolean expBGameOver;


    @Parameterized.Parameters(name="{index}:switchStatus[{2},keyCode]")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {false,false,KeyEvent.VK_SPACE,true,false},
                {true,false,KeyEvent.VK_SPACE,true,false},
                {true,false,KeyEvent.VK_B,false,false},
                {false,false,KeyEvent.VK_B,false,false},
                {true,true,KeyEvent.VK_F2,false,false},
                {true,true,KeyEvent.VK_F2,false,false},
                {false,false,KeyEvent.VK_F2,false,false}


        });
    }

    @Before
    public void setUp() throws Exception {
         sf = new SnakeFrame();
    }

    @After
    public void tearDown() throws Exception {
        sf = null;
    }


    @Test
    public void switchStatus(){

        sf.setBGameOver(bGameOver);
        SnakeFrame.MyPaintThread paintThread =sf.getPaintThread();


        paintThread.setPause(pause);


        sf.getPaintThread().switchStatus(keyCode,sf);

        System.out.println("pause value verify");
        assertEquals(expPause,sf.getPaintThread().getPause());
        System.out.println("bGameOver verify");
        assertEquals(expBGameOver,sf.getBGameOver());

    }



}

