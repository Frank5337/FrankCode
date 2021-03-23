package com.zbl.wwj.concurrent.step2.p61;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/23
 * @Description:
 * @Version: $
 */

/**
 * Future        -> 代表的是未来的一个平局
 * FutureTask    -> 将你的调用逻辑进行了隔离
 * FutureService -> 桥接 Future 和 FutureTask
 *
 * 给电话 送蛋糕
 */
public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
//        String result = get();
//        System.out.println(result);

        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, System.out::println);

        System.out.println("============");
        System.out.println("do other thing");
        Thread.sleep(1000);
        System.out.println("============");

    }

    private static String get() throws InterruptedException {
        Thread.sleep(10_000);
        return "FINISH";
    }


}
