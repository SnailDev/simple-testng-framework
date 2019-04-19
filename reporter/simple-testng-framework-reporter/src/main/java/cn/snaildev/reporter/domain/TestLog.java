package cn.snaildev.reporter.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 10:36
 **/
@Data
public class TestLog implements Serializable {

    private static final long serialVersionUID = 8081681128864610870L;

    private String level;

    private String message;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date logTime;
}
