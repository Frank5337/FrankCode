package com.zbl.wwj.concurrent.step3.p95_105_Atomic.p99;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/7 15:01
 * @Description:
 * @Version: $
 */
public class AtomicBooleanTest {

    @Test
    public void test01() throws Exception {
        AtomicBoolean bool = new AtomicBoolean();
        //private volatile int value 未设置 默认值 0 false   initValue == 0 ?
        Assert.assertFalse(bool.get());
    }

    @Test
    public void test02() throws Exception {
        AtomicBoolean bool = new AtomicBoolean(true);
        Assert.assertTrue(bool.get());
    }

    @Test
    public void test03() throws Exception {
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.getAndSet(false);
        Assert.assertTrue(result);
        Assert.assertFalse(bool.get());
    }

    @Test
    public void test04() throws Exception {
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(true, false);
        Assert.assertTrue(result);
        Assert.assertFalse(bool.get());
    }

    @Test
    public void test05() throws Exception {
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(false, true);
        Assert.assertFalse(result);
        Assert.assertTrue(bool.get());
    }







}
