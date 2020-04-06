package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SFUpdateTest {
    private static int startScore=0;

    private Image offScreenImage;
    private boolean bGameOver;
    private boolean bSuccess;
    private boolean expBGameOver;
    private boolean expPause;
    private int expScore;

    SnakeFrame testobj;

    public SFUpdateTest(Image offScreenImage, boolean bGameOver, boolean bSuccess, boolean expBGameOver, boolean expPause, int expScore){
        this.offScreenImage = offScreenImage;
        this.bGameOver = bGameOver;
        this.bSuccess = bSuccess;
        this.expBGameOver = expBGameOver;
        this.expPause = expPause;
        this.expScore = expScore;
    }

    @Parameterized.Parameters(name="{index}:SFKeyPressed[{0},{1},{2}]=[{3},{4},{5}]")
    public static Collection testData(){
        SnakeFrame sf = new SnakeFrame();
        return Arrays.asList( new Object[][]{
                {null,true,false,true,true,startScore},
                {null,false,true,false,false,startScore+5},
                {sf.createImage(SnakeFrame.ROW*SnakeFrame.BLOCK_HEIGHT, SnakeFrame.COL*SnakeFrame.BLOCK_WIDTH),
                        false,false,false,false,startScore},
                {sf.createImage(SnakeFrame.ROW*SnakeFrame.BLOCK_HEIGHT, SnakeFrame.COL*SnakeFrame.BLOCK_WIDTH),
                        true,false,true,true,startScore},
                {null,false,false,false,false,startScore},
                {sf.createImage(SnakeFrame.ROW*SnakeFrame.BLOCK_HEIGHT, SnakeFrame.COL*SnakeFrame.BLOCK_WIDTH),
                        false,true,false,false,startScore+5},
                {null,true,true,true,true,startScore}
        });
    }

    @Before
    public void setUp() throws Exception {
        testobj = new SnakeFrame();
        testobj.setOffScreenImage(offScreenImage);
        testobj.setBGameOver(bGameOver);
        testobj.setScore(startScore);

        new Thread(testobj.getPaintThread()).start();
    }

    @After
    public void tearDown() throws Exception {
        testobj = null;
        SnakeFrame.isUpdated = SnakeFrame.HAVE_NOT_RUN;
    }

    @Test
    public void testSFUpdate() {
        boolean resBGameOver = bGameOver;
        boolean resPause = false;
        int resScore = startScore;
        do {
            if(SnakeFrame.isUpdated == SnakeFrame.HAVE_RUN){
                resBGameOver = testobj.getBGameOver();
                resPause = testobj.getPaintThread().getPause();
                resScore = testobj.getScore();
                break;
            }
        } while(SnakeFrame.isUpdated == SnakeFrame.HAVE_NOT_RUN);
        assertTrue(resBGameOver == expBGameOver);
        assertTrue(resPause == expPause);
        assertTrue(resScore == expScore);
    }
}