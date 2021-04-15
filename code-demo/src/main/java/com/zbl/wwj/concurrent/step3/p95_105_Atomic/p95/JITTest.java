package com.zbl.wwj.concurrent.step3.p95_105_Atomic.p95;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/6 17:23
 * @Description:
 * @Version: $
 */
public class JITTest {

    private static boolean init = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                while (!init) {
//                    System.out.println(".");
                }
                /**
                 * 非volatile修饰的 里面没有代码的情况下 jit会优化成while(true){}
                 * while(true){}
                 */
            }
        }.start();

        Thread.sleep(1000);

        new Thread() {
            @Override
            public void run() {
                init = true;
                System.out.println("Set init to true");
            }
        }.start();
    }
}
