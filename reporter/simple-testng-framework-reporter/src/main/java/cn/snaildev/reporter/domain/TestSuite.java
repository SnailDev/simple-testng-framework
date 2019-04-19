package cn.snaildev.reporter.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 10:35
 **/
@Data
public class TestSuite implements Serializable {

    private static final long serialVersionUID = 2714008091620428067L;

    private String name;

    private int passCount;

    private int failCount;

    private int skipCount;

    private int totalCount;

    private List<TestClass> classes;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date suiteStartTime;

    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date suiteEndTime;

    public TestSuite() {
        classes = Lists.newArrayList();
    }
}
