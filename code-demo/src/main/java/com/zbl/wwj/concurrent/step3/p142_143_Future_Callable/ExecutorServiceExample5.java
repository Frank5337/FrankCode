package com.zbl.wwj.concurrent.step3.p142_143_Future_Callable;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/2
 * @Description:
 * @Version: $
 */
public class ExecutorServiceExample5 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        //execute 只后, 里面就有active的线程的
        executor.execute(() -> System.out.println("I will be process. because of triggered the execute."));
        //直接加进来, 线程池不知道, 类似wait notify
        executor.getQueue().add(() -> System.out.println("I am added directly into the queue "));
    }
}
