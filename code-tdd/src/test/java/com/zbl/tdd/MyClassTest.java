package com.zbl.tdd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: zbl
 * @Date: 23:52 2020/6/16
 * @Description:
 */
public class MyClassTest {

    private MyClass myClass;

    @Before
    public void setUp() throws Exception {
        myClass = new MyClass();
    }

    @Test
    public void testSayHello() {
        Assert.assertEquals("hello", myClass.sayHello());
    }
}
