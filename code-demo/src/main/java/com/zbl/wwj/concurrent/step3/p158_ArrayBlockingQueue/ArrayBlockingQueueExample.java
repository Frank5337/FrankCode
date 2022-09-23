package com.zbl.wwj.concurrent.step3.p158_ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: zbl
 * @Date: Created in 2021/12/13 10:03
 * @Description:
 * @Version: $
 */
public class ArrayBlockingQueueExample {


    /**
     * FIFO的  first in, first out
     *
     * Once created, the capacity cannot be changed.
     *  一经创建, 容量不可变
     * @param size
     * @param <T>
     * @return
     */
    public <T> ArrayBlockingQueue<T> create(int size) {
        return new ArrayBlockingQueue<>(size);
    }

}
