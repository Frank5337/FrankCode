package com.zbl.wwj.concurrent.step2.p60;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/23
 * @Description:
 * @Version: $
 */
public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task) {
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
        }).start();

        return asynFuture;
    }

}
