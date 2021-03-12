package com.zbl.wwj.concurrent.step1.p17;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/2 12:30
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread task = new Thread(() -> {
            System.out.println("task execute");
            //                Thread.sleep(10_000);
            for (int i = 0; i < 100000; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task .... " + i);
            }
            System.out.println("task execute end");
        });

        ThreadService threadService = new ThreadService();
        threadService.execute(task);
        Thread.sleep(5_000);
        threadService.shutdown();
//        threadService.shutdown(5_000);
    }
}
