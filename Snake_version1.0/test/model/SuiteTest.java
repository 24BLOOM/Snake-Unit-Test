package model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description: 测试套包
 * @author: yhyf
 * @date: 2020/4/4
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({SnakeTest.class,SnakeParam1Test.class,SnakeParam2Test.class,SnakeFrameTest.class,EggTest.class})
public class SuiteTest {

}