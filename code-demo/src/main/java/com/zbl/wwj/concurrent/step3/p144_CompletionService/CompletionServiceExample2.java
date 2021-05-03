package com.zbl.wwj.concurrent.step3.p144_CompletionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/3
 * @Description:
 * @Version: $
 */
public class CompletionServiceExample2 {

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callables = Arrays.asList(
                () -> {
                    sleep(10);
                    System.out.println("1 finished.");
                    return 1;
                },
                () -> {
                    sleep(20);
                    System.out.println("2 finished.");
                    return 2;
                });

        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(service);
        List<Future<Integer>> futures = new ArrayList<>();
        callables.stream().forEach(callable -> {
            futures.add(completionService.submit(callable));
        });
        //谁先执行结束 就拿谁
        Future<Integer> take = null;
        //take 会阻塞
//        while ((take = completionService.take()) != null) {
//            System.out.println(take.get());
//        }

        Future<Integer> poll;
//        Future<Integer> poll = completionService.poll();
//        System.out.println(poll);

        System.out.println(completionService.poll(11, TimeUnit.SECONDS).get());

    }


    /**
     * sleep等待.
     */
    public static void sleep(long s) {
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
