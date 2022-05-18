package com.zbl.wwj.concurrent.step3.p145_146_ScheduledExecutorService;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/4
 * @Description:
 * @Version: $
 */
public class ComplexExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        final ExecutorService service = Executors.newSingleThreadExecutor();
//        List<Runnable> tasks = IntStream.range(0, 5).boxed().map(ComplexExample2::toTask).collect(Collectors.toList());
//        final CompletionService<Object> completionService = new ExecutorCompletionService<>(service);
//
//        List<Future<?>> futureList = new ArrayList<>();
//        //completion 关注的是完成
//        tasks.forEach(r -> futureList.add(completionService.submit(Executors.callable(r))));
//
//        TimeUnit.SECONDS.sleep(11);
//
//        //执行中的任务被中断  不会返回  trap 1
//        List<Runnable> runnables = service.shutdownNow();
//        //[java.util.concurrent.ExecutorCompletionService$QueueingFuture@65b3120a, java.util.concurrent.ExecutorCompletionService$QueueingFuture@6f539caf]
//        System.out.println(runnables);


        final ExecutorService service = Executors.newSingleThreadExecutor();
        List<Callable<Integer>> tasks = IntStream.range(0, 5).boxed().map(MyTask::new).collect(Collectors.toList());
        final CompletionService<Integer> completionService = new ExecutorCompletionService<>(service);

        //completion 关注的是完成
        tasks.forEach(completionService::submit);

        TimeUnit.SECONDS.sleep(15);

        List<Runnable> runnables = service.shutdownNow();
        System.out.println(runnables.size());
        System.out.println(runnables);

        tasks.stream().filter(call -> !((MyTask) call).isSucc()).forEach(System.out::println);

    }

    private static class MyTask implements Callable<Integer> {

        private final Integer i;

        private boolean succ = false;

        MyTask(int value) {
            this.i = value;
        }

        @Override
        public Integer call() throws Exception {
            System.out.printf("The task [%d] will be executed. \n", i);
            TimeUnit.SECONDS.sleep(i * 6);
            System.out.printf("The task [%d] will be done. \n", i);
            succ = true;
            return i;
        }

        public boolean isSucc() {
            return succ;
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


    @Test
    public void test() throws Exception {
        Map map = new HashMap<>();
        map.put(1, 11);
        map.put(2, 12);
        System.out.println(map.keySet());
    }


}
