package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description: 测试Snake类中的eatEgg方法
 * @author: yhyf
 * @date: 2020/4/3
 */
@RunWith(Parameterized.class)
public class SnakeTest {
    private Snake snake;
    private SnakeFrame sf;
    private Egg egg;

    private int eggRow;
    private int eggCol;
    private int headRow;
    private int headCol;
    private int startScore;
    private boolean isEaten;
    private int expScore;

    public SnakeTest(int eggRow,int eggCol,int headRow,int headCol,int startScore,boolean isEaten,int expScore){
        this.eggRow = eggRow;
        this.eggCol = eggCol;
        this.headRow = headRow;
        this.headCol = headCol;
        this.startScore = startScore;
        this.isEaten = isEaten;
        this.expScore = expScore;
    }

    @Parameterized.Parameters(name="{index}:eatEgg[{0},{1},{2},{3},{4}]=[{5},{6}]")
    public static Collection testData(){
        return Arrays.asList( new Object[][]{
                {15,20,15,20,0,true,5},
                {20,20,15,15,0,false,0},
                {15,20,14,20,0,false,0},
                {15,20,16,20,0,false,0},
                {15,20,15,19,0,false,0},
                {15,20,15,21,0,false,0}
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
        egg = null;
    }

    @Test
    public void eatEggTest() throws  NoSuchFieldException,IllegalAccessException{

        egg = new Egg(eggRow,eggCol);
        Field headField = snake.getClass().getDeclaredField("head");
        headField.setAccessible(true);
        Field nodeField = snake.getClass().getDeclaredField("head");
        headField.set(snake,snake.new Node(headRow,headCol,Direction.D));
        sf.setScore(startScore);
        boolean result_ = snake.eatEgg(egg);
        int result_score = sf.getScore();
        assertEquals(isEaten,result_);
        assertEquals(expScore,result_score);

    }
}