package cn.snaildev.reporter.common;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

/**
 * ...
 * @author: Ming.Zhao
 * @create: 2019-04-19 14:24
 **/
public class TestNGReportAppender extends AppenderSkeleton {

    @Override
    protected void append(LoggingEvent loggingEvent) {
        Reporter.log(eventToString(loggingEvent));
    }

    private String eventToString(final LoggingEvent event) {
        final StringBuilder result = new StringBuilder(layout.format(event));

        if(layout.ignoresThrowable()) {
            final String[] s = event.getThrowableStrRep();
            if (s != null) {
                for (final String value : s) {
                    result.append(value).append(Layout.LINE_SEP);
                }
            }
        }
        return result.toString();
    }

    public void close() {

    }

    public boolean requiresLayout() {
        return false;
    }
}
