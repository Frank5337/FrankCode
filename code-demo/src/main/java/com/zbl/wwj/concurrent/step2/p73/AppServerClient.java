package com.zbl.wwj.concurrent.step2.p73;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 11:18
 * @Description:
 * @Version: $
 */
public class AppServerClient {

    public static void main(String[] args) throws InterruptedException {
        AppServer server = new AppServer(13345);
        server.start();

        Thread.sleep(45_000L);

        server.shutdown();
    }

}
