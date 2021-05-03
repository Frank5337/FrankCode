package com.zbl.wwj.concurrent.step3.p144_CompletionService;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/3
 * @Description:
 * @Version: $
 */
public class CompletionServiceExample3 {

    /**
     * 通过这种方式 可以批量提交任务之类的, 先处理先跑完的任务 不用一直等待
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);

        ExecutorCompletionService<Event> completionService = new ExecutorCompletionService<>(service);
        final Event event = new Event(1);
        System.out.println(completionService.take().get().eventId);


    }

    private static class MyTask implements Runnable {
        private final Event event;

        public MyTask(Event event) {
            this.event = event;
        }

        @Override
        public void run() {
            sleep(10);
            event.setResult("I am successful");
        }
    }

    private static class Event {

        final private int eventId;

        private String result;

        public Event(int eventId) {
            this.eventId = eventId;
        }

        public int getEventId() {
            return eventId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
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
