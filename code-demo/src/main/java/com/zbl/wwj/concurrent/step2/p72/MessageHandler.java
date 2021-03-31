package com.zbl.wwj.concurrent.step2.p72;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 10:42
 * @Description:
 * @Version: $
 */
public class MessageHandler {

    private static final Random r = new Random(System.currentTimeMillis());

    private static final Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
//        new Thread(() -> {
//            String value = message.getValue();
//            try {
//                Thread.sleep(r.nextInt(1000));
//                System.out.println("The message will be handle by " + Thread.currentThread().getName() + " " + value);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
        executor.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(r.nextInt(1000));
                System.out.println("The message will be handle by " + Thread.currentThread().getName() + " " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown() {
        System.out.println("shutdown");
        ((ExecutorService)executor).shutdown();
    }
}
