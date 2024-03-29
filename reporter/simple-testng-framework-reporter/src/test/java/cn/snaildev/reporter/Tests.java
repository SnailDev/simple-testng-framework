package cn.snaildev.reporter;

import cn.snaildev.reporter.common.TestLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 17:03
 **/
@Slf4j
@SpringBootTest
@Listeners({TestReporter.class, TestReporterSuiteListener.class})
public class Tests extends AbstractTestNGSpringContextTests {
//    @Autowired
//    TestProcessorHandler testProcessorHandler;

    @Test
    public void testDemo() {
        TestLogger.info(log, "测试测试报告");
    }

    @Test
    public void testDemo1() {
        TestLogger.info(log, "测试测试报告1");
    }

    @Test
    public void testDemo2() {
        TestLogger.info(log, "测试测试报告2");
    }

    @Test
    public void testDemo3() {
        TestLogger.info(log, "测试测试报告3");
    }

    @Test
    public void testDemo4() throws Exception {
        TestLogger.info(log, "测试测试报告4");

        throw new Exception("假装抛出个异常");
    }
}
