package com.zbl.wwj.concurrent.step2.p51;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/13
 * @Description:
 * @Version: $
 */
public class ThreadLifeCycleObserverClient {

    public static void main(String[] args) {
        ThreadLifeCycleObserver threadLifeCycleObserver = new ThreadLifeCycleObserver();
        threadLifeCycleObserver.concurrentQuery(Arrays.asList("a", "b", "c", "d", "e"));
    }
}
