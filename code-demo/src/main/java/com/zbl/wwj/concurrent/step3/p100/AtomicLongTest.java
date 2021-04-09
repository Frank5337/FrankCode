package com.zbl.wwj.concurrent.step3.p100;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/7 15:16
 * @Description:
 * @Version: $
 */
public class AtomicLongTest {

    @Test
    public void test01() throws Exception {
        AtomicLong l = new AtomicLong(100L);
        /**
         * 32
         * long 64位  无锁 cmpchg
         * high 32
         * low  32
         *
         */
        Assert.assertEquals(100L, l.get());

    }

}
