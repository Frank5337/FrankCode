package com.zbl.msb.juc;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: 18:51 2020/7/26
 * @Description: DelayQueue 可以实现时间上的排序, 也是用于阻塞的队列
 * 这个阻塞队列装任务的时候必须实现Delay接口  Delayed 往后拖延推迟,
 * Delayed需要做一个比较compareTo, 最后用这个队列实现
 * 这个时间等待越短的就会优先得到运行
 * 这里面有一个时间的排序
 * 需要实现Comparable接口 重写compareTo方法
 * getDelay 去拿Delay时间了
 * <p>
 * DelayQueue 本质上用的是一个PriorityQueue,  PriorityQueue是从AbstractQueue继承的,
 * PriorityQueue的特点是 往里装的时候并不是按顺序装的, 而是内部进行了一个排序, 按照优先级 最小的优先
 * 内部实现的结构是一个二叉树, 这个二叉树可以认为是堆排序里面的那个最小的堆值排在最上面
 */
public class T07_DelayQueue {

    static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    static Random r = new Random();

    static class MyTask implements Delayed {
        String name;
        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    ", runningTime=" + runningTime +
                    '}';
        }

    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", now + 1000);
        MyTask t2 = new MyTask("t2", now + 2000);
        MyTask t3 = new MyTask("t3", now + 1500);
        MyTask t4 = new MyTask("t4", now + 2500);
        MyTask t5 = new MyTask("t5", now + 500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
