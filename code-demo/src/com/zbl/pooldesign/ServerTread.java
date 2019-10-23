package com.zbl.pooldesign;

import java.util.stream.IntStream;

/**
 * Create By : FanXiaoYun
 * Date      : 2019-10-23
 * Describe  : simulate executing task from RequestQueue
 */


public class ServerTread {

    private  RequestQueue queue =RequestQueue.getInstance();


    public ServerTread() {
        createThread();
    }

    public RequestQueue getQueue() {
        return queue;
    }

    private void createThread() {

        System.out.println(queue+">>>>>>server");
        IntStream.rangeClosed(1, 10).forEach(name -> new Thread(() -> {

            Request request;
            OUTER :
            while(true){

                     while (queue.getSize() <= 0) {
                         synchronized (queue) {
                             System.out.println("很遗憾，没有任务。我回来睡觉。");
                             try {
                                 queue.wait();
                                 System.out.println("收到任务通知，准备去抢任务");
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                                 break OUTER;
                             }
                         }
                     }

                    request=queue.getRequest();
                    System.out.println("抢到任务【"+request.getValue()+"】"+">>开始执行");
                    try {
                        Thread.sleep(3_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(request.getValue()+"执行完成");

                }

        },String.valueOf(name)).start());
    }





}
