package model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @version V1.1
 * @Title: SuitTest
 * @Package
 * @Description: 测试套包
 * @author: chenqi
 * @date: 2020/4/5
 */



@RunWith(Suite.class)
@Suite.SuiteClasses({SnakeTest.class,SnakeParam1Test.class,SnakeParam2Test.class,SnakeParam3Test.class,SnakeFrameTest.class,SnakeFrameParam1Test.class,EggTest.class})
public class SuiteTest {

}