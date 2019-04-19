package cn.snaildev.reporter.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 15:50
 **/
public class ExceptionUtils {
    public static String buildStackTrace(Throwable e) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        printWriter.flush();
        return writer.toString();
    }
}
