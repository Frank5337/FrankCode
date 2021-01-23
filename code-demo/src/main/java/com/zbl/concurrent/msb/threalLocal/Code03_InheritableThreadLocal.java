package com.zbl.concurrent.msb.threalLocal;

import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 10:32 2020/7/27
 * @Description:
 * @Version: $
 *
 * 主线程持有, 子线程共享的ThreadLocal,  父子之间传递的
 */
public class Code03_InheritableThreadLocal {










    @Test
    public void test() {
        final ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("帅得一匹");
        Thread t = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("张三帅么 = " + threadLocal.get());
            }
        };
        t.start();
    }

}
