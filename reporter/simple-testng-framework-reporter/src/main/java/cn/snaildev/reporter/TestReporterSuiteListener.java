package cn.snaildev.reporter;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.Date;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 10:41
 **/
public class TestReporterSuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite iSuite) {
        iSuite.setAttribute("suiteStartTime", new Date());
    }

    @Override
    public void onFinish(ISuite iSuite) {
        iSuite.setAttribute("suiteEndTime", new Date());
    }
}
