package cn.snaildev.reporter.handler;

import cn.snaildev.reporter.domain.TestMethod;
import cn.snaildev.reporter.exception.ExceptionUtils;
import org.testng.ITestResult;

import java.util.Date;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 15:45
 **/
//@Component
public class TestMethodHandler {

//    @Autowired
//    TestLogHandler logHandler;

    public static TestMethod getTestMethod(ITestResult testResult) {
        TestMethod method = new TestMethod();

        Throwable error = testResult.getThrowable();
        if (error != null) {
            method.setException(ExceptionUtils.buildStackTrace(error));
        }

        String methodName = testResult.getName();
        Object[] parameters = testResult.getParameters();
        if (parameters != null && parameters.length > 0) {
            for (Object parameter : parameters) {
                methodName += String.format("(%s)", parameter);
                break;
            }
        }

        method.setName(methodName);
        method.setIsPass(testResult.getStatus());
        method.setTestStartTime(new Date(testResult.getStartMillis()));
        method.setTestEndTime(new Date(testResult.getEndMillis()));
        method.setLogs(TestLogHandler.getReportLog(testResult));

        return method;
    }
}
