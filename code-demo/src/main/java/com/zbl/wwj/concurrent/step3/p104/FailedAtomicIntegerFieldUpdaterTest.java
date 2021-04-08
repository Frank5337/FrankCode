package com.zbl.wwj.concurrent.step3.p104;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/8
 * @Description:
 * @Version: $
 */
public class FailedAtomicIntegerFieldUpdaterTest {

    /**
     * can't access the private field of object
     *
     * @throws Exception
     */
    @Test(expected = RuntimeException.class)
    public void test01() throws Exception {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();
        updater.compareAndSet(me, 0, 1);
        //java.lang.RuntimeException:
        // java.lang.IllegalAccessException:
        // Class com.zbl.wwj.concurrent.step3.p104.FailedAtomicIntegerFieldUpdaterTest
        // can not access a member of class
        // com.zbl.wwj.concurrent.step3.p104.FailedAtomicIntegerFieldUpdaterTest$TestMe with modifiers "private volatile"
    }

    @Test
    public void test02() throws Exception {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        //java.lang.ClassCastException
        updater.compareAndSet(null, 0, 1);
    }

    @Test
    public void test03() throws Exception {
        //java.lang.RuntimeException: java.lang.NoSuchFieldException: i1
        //反射去拿, 没有这个字段
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i1");
        updater.compareAndSet(null, 0, 1);
    }

    @Test
    public void test04() throws Exception {
        AtomicReferenceFieldUpdater<TestMe, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe.class, Integer.class, "i");
        TestMe me = new TestMe();
        updater.compareAndSet(me, null, 1);
    }

    @Test
    public void test05() throws Exception {
        //java.lang.ClassCastException
        AtomicReferenceFieldUpdater<TestMe, Long> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe.class, Long.class, "i");
        TestMe me = new TestMe();
        updater.compareAndSet(me, null, 1L);
    }

    @Test
    public void test06() throws Exception {
        //java.lang.IllegalArgumentException: Must be volatile type
        AtomicReferenceFieldUpdater<TestMe, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe.class, Integer.class, "i");
        TestMe me = new TestMe();
        updater.compareAndSet(me, null, 1);
    }

    static class TestMe {
        volatile Integer i;
    }
}
