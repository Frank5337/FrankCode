package com.zbl.pooldesign;

import java.util.stream.IntStream;

/**
 * Create By : FanXiaoYun
 * Date      : 2019-10-23
 * Describe  : Create some Task and put them into RequestQueue
 *
 * @author wyk
 */

public class TestThread {

    public static void main(String[] args) {

//        Stream.of("服务线程","客户端线程").forEach(new Thread(()->new com.zbl.pooldesign.ServerTread(),name));


        new Thread(() -> {

            try {
                Thread.sleep(20_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            try {
//                Thread.sleep(10_000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            new ServerTread();
        }).start();


        new Thread(() -> {

            ClientServer clientServer = new ClientServer();
            IntStream.rangeClosed(1, 100).forEach(name -> new Thread(() ->
                    clientServer.submitRequest(new Request(Thread.currentThread().getName())), String.valueOf(name)).start());

        }).start();


    }


}
