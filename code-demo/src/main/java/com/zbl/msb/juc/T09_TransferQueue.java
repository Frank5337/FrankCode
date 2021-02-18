package com.zbl.msb.juc;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @Author: zbl
 * @Date: 19:39 2020/7/26
 * @Description: 传递, 实际上是前面各种各样Queue的组合
 * 可以用来传递任务, 不像Synchronous只能传递一个,
 * TransferQueue做成列表 可以传好多个
 * 比较牛X的就是它添加了一个方法叫transfer 如果我们用put就相当于一个线程往里装一装就走了
 * transfer就是装完在这等着, 阻塞等着有人把它取走我这个线程才回去干我自己的事情
 * <p>
 * 一般使用场景:
 * 是我做了一件事情, 我这个事情要求有一个结果, 有了这个结果之后我可以继续进行我下面的这个事情的时候,
 * 比方说我付了钱,  这个订单我付账完成了,  但是我一直要等着这个付账结果完成才能给客户反馈
 */
public class T09_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                int i = 0;
                for (;;) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " take " + ++i);
                    System.out.println(strs.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("transfer 1 ---");
        strs.transfer("aaa");
        System.out.println("transfer 2 ---");
        strs.transfer("bbb");
        System.out.println("transfer 3 ---");
        strs.transfer("ccc");

        System.out.println("transfer 4 ---");
        strs.put("aaa");

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " take");
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
