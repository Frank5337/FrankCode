package com.zbl.wwj.concurrent.step3.p159_PriorityBlockingQueue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author: zbl
 * @Date: Created in 2021/12/13 15:54
 * @Description:
 * @Version: $
 */
public class PriorityBlockingQueueExample {

    public <T> PriorityBlockingQueue<T> create(int size) {
        return new PriorityBlockingQueue<>(size);
    }

    public <T> PriorityBlockingQueue<T> create(int size, Comparator<T> comparator) {
        return new PriorityBlockingQueue<>(size, comparator);
    }

}
