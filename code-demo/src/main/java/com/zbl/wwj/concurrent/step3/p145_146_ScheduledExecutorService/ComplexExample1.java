package com.zbl.wwj.concurrent.step3.p145_146_ScheduledExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/4
 * @Description:
 * @Version: $
 */
public class ComplexExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(5);

        List<Runnable> tasks = IntStream.range(0, 5).boxed().map(ComplexExample1::toTask).collect(Collectors.toList());

        List<Future<?>> futureList = new ArrayList<>();
        final CompletionService<Object> completionService = new ExecutorCompletionService<>(service);
//        tasks.forEach( r -> futureList.add(service.submit(r)));

        //completion 关注的是完成
        tasks.forEach( r -> futureList.add(completionService.submit(Executors.callable(r))));

//        futureList.get(4).get();
//        System.out.println("==4==");
//
//        futureList.get(3).get();
//        System.out.println("==3==");
//
//        futureList.get(2).get();
//        System.out.println("==2==");
//
//        futureList.get(1).get();
//        System.out.println("==1==");
//
//        futureList.get(0).get();
//        System.out.println("==0==");

        Future<?> future;
        while ((future = completionService.take()) != null) {
            System.out.println(future.get());
        }

    }

    private static Runnable toTask(int i) {
        return () -> {
            try {
                System.out.printf("The task [%d] will be executed. \n", i);
                TimeUnit.SECONDS.sleep(i * 6);
                System.out.printf("The task [%d] will be done. \n", i);
            } catch (InterruptedException e) {
                System.out.printf("The task [%d] interrupted. \n", i);
            }
        };
    }
}
