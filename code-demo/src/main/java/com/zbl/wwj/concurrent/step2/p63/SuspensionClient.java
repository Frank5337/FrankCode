package com.zbl.wwj.concurrent.step2.p63;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/24
 * @Description:
 * @Version: $
 */
public class SuspensionClient {

    public static void main(String[] args) throws InterruptedException {
        RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Alex").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();
        Thread.sleep(10_000);
        serverThread.close();


    }


}
