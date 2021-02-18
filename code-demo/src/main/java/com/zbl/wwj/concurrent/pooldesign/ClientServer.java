package com.zbl.wwj.concurrent.pooldesign;

/**
 * Create By : FanXiaoYun
 * Date      : 2019-10-23
 * Describe  : simulate  putting RequestTask into RequestQueue
 *
 * @author wyk
 */
public class ClientServer {

    private RequestQueue queue = RequestQueue.getInstance();


    public void submitRequest(Request request) {

//        System.out.println(queue+"ssssssssss");


        synchronized (queue) {
            queue.submit(request);
//        System.out.println(queue+">>>>>client");
            queue.notifyAll();
        }

    }


}
