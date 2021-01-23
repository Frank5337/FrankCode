package com.zbl.concurrent.msb.threalLocal;

import org.apache.dubbo.common.threadlocal.InternalThread;
import org.apache.dubbo.common.threadlocal.InternalThreadLocal;
import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/24
 * @Description:
 * @Version: $
 */
public class Code04_InternalThreadLocal {

    private static final InternalThreadLocal<Integer> internalThreadLocal_0 = new InternalThreadLocal<>();

    public static void main(String[] args) {
        new InternalThread(() -> {
            for (int i = 0; i < 5; i++) {
                internalThreadLocal_0.set(i);
                Integer value = internalThreadLocal_0.get();
                System.out.println(Thread.currentThread().getName() + ":" + value);
            }
        }, "internalThread_have_set").start();

        new InternalThread(() -> {
            for (int i = 0; i < 5; i++) {
                Integer value = internalThreadLocal_0.get();
                System.out.println(Thread.currentThread().getName() + ":" + value);
            }
        }, "internalThread_no_set").start();
    }

    @Test
    public void test01() throws Exception {
        int newCapacity = 4;
        newCapacity |= newCapacity >>> 1;
        System.out.println(newCapacity);
        newCapacity |= newCapacity >>> 2;
        System.out.println(newCapacity);
        newCapacity |= newCapacity >>> 4;
        System.out.println(newCapacity);
        newCapacity |= newCapacity >>> 8;
        System.out.println(newCapacity);
        newCapacity |= newCapacity >>> 16;
        newCapacity++;
        System.out.println(newCapacity);
    }

}
