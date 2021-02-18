package com.zbl.wwj.concurrent.step1.p17;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/2 12:34
 * @Description:
 * @Version: $
 */
public class ThreadCloseForce {

    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(() -> {
//            while (true) {
//
//            }
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(1_0000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
