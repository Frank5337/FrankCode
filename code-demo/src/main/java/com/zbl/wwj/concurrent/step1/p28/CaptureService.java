package com.zbl.wwj.concurrent.step1.p28;

import java.util.*;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/7 15:19
 * @Description:
 * @Version: $
 */
public class CaptureService {

    //当前运行多少个线程
    private static final LinkedList<Control> CONTROLS = new LinkedList<>();

    private static final int MAX_WORKER = 5;

    /**
     * 可以限制使用资源,  通知机制综合应用(简易版)
     * @param args
     */
    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10").stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });
        worker.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("All of capture work finished").ifPresent(System.out::println);

    }

    private static Thread createCaptureThread(String name) {
        return new Thread(name) {
            @Override
            public void run() {
                Optional.of("The worker {" + Thread.currentThread().getName() + "} begin capture data").ifPresent(System.out::println);
                synchronized (CONTROLS) {
                    while (CONTROLS.size() > MAX_WORKER) {
                        try {
                            CONTROLS.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    CONTROLS.addLast(new Control());
                }

                Optional.of("The worker {" + Thread.currentThread().getName() + "} is working").ifPresent(System.out::println);
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (CONTROLS) {
                    Optional.of("The worker {" + Thread.currentThread().getName() + "} end capture data").ifPresent(System.out::println);
                    CONTROLS.removeFirst();
                    CONTROLS.notifyAll();
                }
            }
        };
    }


    private static class Control {

    }

}


