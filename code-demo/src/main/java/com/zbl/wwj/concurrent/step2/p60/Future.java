package com.zbl.wwj.concurrent.step2.p60;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/23
 * @Description:
 * @Version: $
 */
public interface Future<T> {

    T get() throws InterruptedException;
}
