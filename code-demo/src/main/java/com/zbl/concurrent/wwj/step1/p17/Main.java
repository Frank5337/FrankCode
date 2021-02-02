package com.zbl.concurrent.wwj.step1.p17;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/2 12:30
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        Thread task = new Thread(() -> {
            System.out.println("task execute");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task execute");
        });

        ThreadService threadService = new ThreadService();
        threadService.execute(task);
        threadService.shutdown(5_000);
    }
}
