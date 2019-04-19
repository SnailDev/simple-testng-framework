package cn.snaildev.reporter.handler;

import cn.snaildev.reporter.domain.TestLog;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 15:51
 **/
//@Component
public class TestLogHandler {

    public static List<TestLog> getReportLog(ITestResult testResult) {
        List<TestLog> testLogs = new ArrayList<>();
        List<String> logs = Reporter.getOutput(testResult);
        logs.forEach(log -> {
            TestLog testLog = new TestLog();
            String[] logInfo = log.split("#");
            if (logInfo.length == 3) {
                testLog.setLevel(logInfo[0]);
                testLog.setMessage(logInfo[1]);
                testLog.setLogTime(new Date(Long.parseLong(logInfo[2])));
                testLogs.add(testLog);
            }
        });

        Set<ITestResult> allMethodResults = testResult.getTestContext().getPassedConfigurations().getAllResults();
        allMethodResults.forEach(allMethodResult -> {
            List<String> configMethodLogs = Reporter.getOutput(allMethodResult);
            configMethodLogs.forEach(configMethodLog -> {
                String[] logInfo = configMethodLog.split("#");
                if (logInfo.length == 3 && logInfo[1].contains("@")) {
                    String[] detail = logInfo[1].split("@");
                    if (detail[1].equals(String.format("%s.%s", testResult.getTestClass().getName(), testResult.getName()))) {
                        TestLog testLog = new TestLog();
                        testLog.setLevel(logInfo[0]);
                        testLog.setMessage(detail[0]);
                        testLog.setLogTime(new Date(Long.parseLong(logInfo[2])));

                        testLogs.add(testLog);
                    }
                }
            });
        });

        return testLogs;
    }
}
