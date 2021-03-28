package com.zbl.wwj.concurrent.step2.p65;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/25
 * @Description:
 * @Version: $
 */

//始终以当前线程作为key值
public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if (value == null) {
                return initialValue();
            }
            return value;
        }
    }

    public T initialValue() {
        return null;
    }

}
