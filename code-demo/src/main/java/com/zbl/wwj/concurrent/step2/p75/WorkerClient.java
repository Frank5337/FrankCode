package com.zbl.wwj.concurrent.step2.p75;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 14:09
 * @Description:
 * @Version: $
 */
public class WorkerClient {

    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorkers();
        new TransportThread("Alex", channel).start();
        new TransportThread("Jack", channel).start();
        new TransportThread("Flank", channel).start();
    }

}
