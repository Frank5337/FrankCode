package com.zbl.wwj.concurrent.step3.p95_105_Atomic.p103;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/8
 * @Description:
 * @Version: $
 */
public class AtomicIntegerArrayTest {

    @Test
    public void test01() throws Exception {
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        Assert.assertEquals(0, array.length());
    }

    @Test
    public void test02() throws Exception {
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        Assert.assertEquals(0, array.get(5));
    }

    @Test
    public void test03() throws Exception {
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        array.set(3,3);
        Assert.assertEquals(3, array.get(3));
    }

    @Test
    public void test04() throws Exception {
        int[] or = new int[10];
        or[5] = 5;
        AtomicIntegerArray array = new AtomicIntegerArray(or);
        int get = array.getAndSet(5,6);
        Assert.assertEquals(5, get);
        Assert.assertEquals(6, array.get(5));
    }


}
