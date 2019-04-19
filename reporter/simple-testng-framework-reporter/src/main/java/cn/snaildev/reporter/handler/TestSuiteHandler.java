package cn.snaildev.reporter.handler;

import cn.snaildev.reporter.domain.TestClass;
import cn.snaildev.reporter.domain.TestSuite;
import org.assertj.core.util.Lists;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 15:31
 **/
//@Component
public class TestSuiteHandler {
//    @Autowired
//    TestResultHandler resultHandler;
//    @Autowired
//    TestClassHandler classHandler;

    public static TestSuite getTestSuite(ISuite suite) {
        Map<String, List<ITestResult>> resultMap = TestResultHandler.getTestResultMap(suite);
        return getTestSuite(suite, resultMap);
    }

    private static TestSuite getTestSuite(ISuite suite, Map<String, List<ITestResult>> resultMap) {
        int passed = 0, failed = 0, skipped = 0;
        List<TestClass> classes = Lists.newArrayList();
        for (Map.Entry<String, List<ITestResult>> resultEntry : resultMap.entrySet()) {
            classes.add(TestClassHandler.getTestClass(resultEntry));
            for (ITestResult result : resultEntry.getValue()) {
                ITestContext context = result.getTestContext();
                passed += context.getPassedTests().size();
                failed += context.getFailedTests().size();
                skipped += context.getSkippedTests().size();
            }
        }

        TestSuite testSuite = new TestSuite();
        testSuite.setName(suite.getName());
        testSuite.setPassCount(passed);
        testSuite.setFailCount(failed);
        testSuite.setSkipCount(skipped);
        testSuite.setSuiteStartTime((Date) suite.getAttribute("suiteStartTime"));
        testSuite.setSuiteEndTime((Date) suite.getAttribute("suiteEndTime"));
        testSuite.setClasses(classes);

        return testSuite;
    }
}
