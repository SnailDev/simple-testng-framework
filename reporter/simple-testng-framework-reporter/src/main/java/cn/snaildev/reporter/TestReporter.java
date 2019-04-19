package cn.snaildev.reporter;

import cn.snaildev.reporter.handler.TestProcessorHandler;
import com.google.common.reflect.ClassPath;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 10:40
 **/
@Slf4j
public class TestReporter implements IReporter {
//    @Autowired
//    TestProcessorHandler processorHandler;

    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> suites, String outputDirectory) {
        try {
            generateReport(suites, outputDirectory);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private void generateReport(List<ISuite> suites, String outputDirectory) throws IOException {
        String result = TestProcessorHandler.parseTestResultToString(suites);
        copyFiles(new File(outputDirectory, "html-reporter"));
        FileUtils.write(new File(outputDirectory + "/html-reporter/js/data.js"), "var result = " + result, "UTF-8");

        // TODO: report history backup
    }

    private void copyFiles(File descDir) throws IOException {
        ClassPath classPath = ClassPath.from(TestReporter.class.getClassLoader());
        Set<ClassPath.ResourceInfo> resources = classPath.getResources();
        String dir = TestReporter.class.getPackage().getName().replace('.', '/') + "/" + "html-reporter";
        Iterator i$ = resources.iterator();

        while (i$.hasNext()) {
            ClassPath.ResourceInfo info = (ClassPath.ResourceInfo) i$.next();
            String resourceName = info.getResourceName();
            if (info.getResourceName().startsWith(dir) && !StringUtils.endsWithIgnoreCase(resourceName, ".class")) {
                InputStream stream = TestReporter.class.getClassLoader().getResourceAsStream(resourceName);
                FileUtils.copyInputStreamToFile(stream, new File(descDir, StringUtils.replace(resourceName, dir + "/", "")));
            }
        }
    }
}
