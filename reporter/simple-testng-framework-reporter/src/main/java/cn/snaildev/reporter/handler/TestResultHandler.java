package cn.snaildev.reporter.handler;

import org.testng.ISuite;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 15:33
 **/
//@Component
public class TestResultHandler {

    public static Map<String, List<ITestResult>> getTestResultMap(ISuite suite) {
        List<ITestResult> results = getTestResults(suite);

        Map<String, List<ITestResult>> resultMap = new HashMap<>();
        results.forEach(result -> {
            String className = result.getTestClass().getName();
            List resultItems = resultMap.get(className);
            if (resultItems == null) {
                resultItems = new ArrayList();
                resultMap.put(className, resultItems);
            }

            resultItems.add(result);
        });

        return resultMap;
    }

    private static List<ITestResult> getTestResults(ISuite suite) {
        List<ITestResult> results = new ArrayList<>();
        suite.getResults().values().forEach(result -> {
            results.addAll(result.getTestContext().getPassedTests().getAllResults());
            results.addAll(result.getTestContext().getFailedTests().getAllResults());
            results.addAll(result.getTestContext().getSkippedTests().getAllResults());
        });

        return results;
    }
}
