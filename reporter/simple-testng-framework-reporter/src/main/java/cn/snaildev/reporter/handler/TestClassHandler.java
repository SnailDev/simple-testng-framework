package cn.snaildev.reporter.handler;

import cn.snaildev.reporter.domain.TestClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;

import java.util.List;
import java.util.Map;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 15:43
 **/
//@Component
public class TestClassHandler {
//    @Autowired
//    TestMethodHandler methodHandler;

    public static TestClass getTestClass(Map.Entry<String, List<ITestResult>> resultEntry) {
        TestClass testClass = new TestClass();
        resultEntry.getValue().forEach(testResult -> {
            testClass.getMethods().add(TestMethodHandler.getTestMethod(testResult));
        });
        testClass.setName(resultEntry.getKey());

        return testClass;
    }
}
