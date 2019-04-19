package cn.snaildev.reporter.domain;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-19 10:36
 **/
@Data
public class TestClass implements Serializable {

    private static final long serialVersionUID = -3444528267979455558L;

    private String name;

    private List<TestMethod> methods;

    public TestClass() {
        methods = Lists.newArrayList();
    }
}
