package cn.snaildev.reporter.common;

import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.testng.Reporter;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 14:41
 **/
public class TestLogger {
    public static final String SplitChar = "#";

    public static void info(Logger logger, String message) {
        logger.info(message);
        logToTestNG(Level.INFO, message);
    }

    public static void warn(Logger logger, String message) {
        logger.warn(message);
        logToTestNG(Level.WARN, message);
    }

    public static void error(Logger logger, String message) {
        logger.error(message);
        logToTestNG(Level.ERROR, message);
    }

    private static void logToTestNG(Level level, String message) {
        Reporter.log(String.format("%s#%s#%s", level, message, System.currentTimeMillis()));
    }
}
