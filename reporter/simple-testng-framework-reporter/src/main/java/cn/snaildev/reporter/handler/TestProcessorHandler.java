package cn.snaildev.reporter.handler;

import cn.snaildev.reporter.domain.TestJob;
import cn.snaildev.reporter.domain.TestSuite;
import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.ISuite;

import java.util.Date;
import java.util.List;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 15:25
 **/
//@Component
public class TestProcessorHandler {
//    @Autowired
//    TestSuiteHandler suiteHandler;

    public static String parseTestResultToString(List<ISuite> suites) {
        int total = 0, pass = 0, fail = 0, skip = 0;
        Date jobStartTime = null, jobEndTime = null;

        List<TestSuite> testSuites = Lists.newArrayList();
        for (ISuite suite : suites) {
            TestSuite testSuite = TestSuiteHandler.getTestSuite(suite);
            testSuites.add(testSuite);
            total += testSuite.getTotalCount();
            pass += testSuite.getPassCount();
            fail += testSuite.getFailCount();
            skip += testSuite.getSkipCount();

            if (jobStartTime == null || jobEndTime == null) {
                jobStartTime = testSuite.getSuiteStartTime();
                jobEndTime = testSuite.getSuiteEndTime();
            }
            if (jobStartTime.after(testSuite.getSuiteStartTime())) {
                jobStartTime = testSuite.getSuiteStartTime();
            }
            if (jobEndTime.before(testSuite.getSuiteEndTime())) {
                jobEndTime = testSuite.getSuiteEndTime();
            }
        }

        TestJob job = new TestJob();
        job.setTotalCount(total);
        job.setPassCount(pass);
        job.setFailCount(fail);
        job.setSkipCount(skip);
        job.setJobStartTime(jobStartTime);
        job.setJobEndTime(jobEndTime);
        job.setSuites(testSuites);

        return JSON.toJSONString(job);
    }

    public void test() {
        System.out.println(123);
    }
}
