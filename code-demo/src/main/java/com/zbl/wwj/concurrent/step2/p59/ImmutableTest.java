package com.zbl.wwj.concurrent.step2.p59;

import java.util.Collections;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/22
 * @Description:
 * @Version: $
 */
public class ImmutableTest {

    private final int age;

    private final String name;

    private final List<String> list;

    public ImmutableTest(int age, String name, List<String> list) {
        this.age = age;
        this.name = name;
        this.list = list;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    /**
     * 不可变
     * @return
     */
    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }
}
