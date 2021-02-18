package com.zbl.wwj.concurrent.step1.p30;

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
                            booleanLock.lock(100);
                            Optional.of(Thread.currentThread().getName() + "  have the lock monitor").ifPresent(System.out::println);
                            work();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (LOCK.TimeOutException e) {
                            Optional.of(Thread.currentThread().getName() + " time out").ifPresent(System.out::println);
                        } finally {
                            booleanLock.unLock();
                        }

                    }, name).start();

                }

        );


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
