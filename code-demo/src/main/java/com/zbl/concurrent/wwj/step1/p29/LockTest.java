package com.zbl.concurrent.wwj.step1.p29;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/7 15:37
 * @Description:
 * @Version: $
 */
public class LockTest {


    public static void main(String[] args) throws InterruptedException {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4", "T5")
                .forEach(name -> {
                    new Thread(() -> {
                        try {
                            booleanLock.lock();
                            Optional.of(Thread.currentThread().getName() + "  have the lock monitor").ifPresent(System.out::println);
                            work();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            booleanLock.unLock();
                        }

                    }, name).start();

                }

        );

        Thread.sleep(100);
        booleanLock.unLock();


    }

    private static void work() {
        Optional.of(Thread.currentThread().getName() + "  is Working...").ifPresent(System.out::println);
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
