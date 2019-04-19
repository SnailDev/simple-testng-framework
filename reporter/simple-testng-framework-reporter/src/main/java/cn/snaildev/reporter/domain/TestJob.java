package cn.snaildev.reporter.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 10:34
 **/
@Data
public class TestJob implements Serializable {

    private static final long serialVersionUID = -5698441800218847195L;

    private int passCount;

    private int failCount;

    private int skipCount;

    private int totalCount;

    private List<TestSuite> suites;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date jobStartTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date jobEndTime;

    public TestJob() {
        suites = Lists.newArrayList();
    }

//    public int getTotalCount() {
//        return this.passCount + this.failCount + this.skipCount;
//    }
}
