package cn.snaildev.reporter.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 10:35
 **/
@Data
public class TestMethod implements Serializable {

    private static final long serialVersionUID = -323089869718874397L;

    private String name;

    private List<TestLog> logs;

    private int isPass;

    private String exception;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date testStartTime;

    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date testEndTime;
}
