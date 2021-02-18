package com.zbl.wwj.concurrent.step1.p32;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/10
 * @Description: 捕获线程的异常
 * @Version: $
 */
public class ThreadException {

    private final static int A = 10;

    private final static int B = 0;

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1_000L);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //未捕获的异常处理
        t.setUncaughtExceptionHandler((thread ,e) -> {
            System.out.println(e);
            System.out.println(thread.getName());
            e.printStackTrace();
        });

        t.start();
    }

}
