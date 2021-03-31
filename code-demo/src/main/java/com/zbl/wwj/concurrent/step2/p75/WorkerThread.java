package com.zbl.wwj.concurrent.step2.p75;

import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 13:47
 * @Description:
 * @Version: $
 */
public class WorkerThread extends Thread {

    private final Channel channel;

    private static final Random r = new Random(System.currentTimeMillis());

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            channel.take().execute();
            try {
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
