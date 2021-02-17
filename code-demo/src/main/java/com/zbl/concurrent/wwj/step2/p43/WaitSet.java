package com.zbl.concurrent.wwj.step2.p43;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/16
 * @Description:
 * @Version: $
 */
public class WaitSet {

    private static final Object LOCK = new Object();

    private static void work() {
        synchronized (LOCK) {
            System.out.println("Begin.....");

            try {
                System.out.println("Thread will coming");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread will out");

        }
    }

    /**
     * 1.所有的对象都会有一个wait set, 用来存放调用了该对象wait方法之后进入block状态的线程
     * 2.线程被notify之后, 不一定立即得到执行(线程状态由block变为runnable 获取到cpu执行权之后才会变为running)
     * 3.线程从wait set中被唤醒的顺序不是FIFO的.
     * 4.线程被唤醒后, 必须重新获取锁
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(WaitSet::work).start();

        Thread.sleep(1000);

        synchronized (LOCK) {
            LOCK.notify();
        }

//        IntStream.rangeClosed(1, 10)
//                .forEach(i -> {
//                    new Thread(String.valueOf(i)) {
//                        @Override
//                        public void run() {
//                            synchronized (LOCK) {
//                                try {
//                                    Optional.of(Thread.currentThread().getName() + " will come to wait set")
//                                            .ifPresent(System.out::println);
//                                    //这个方法会导致当前线程把他放到这个对象的waitSet里面去
//                                    //并且释放任何有关sync的执行权
//                                    //谎言式休眠 放到了waitSet里面去
//                                    LOCK.wait();
//                                    Optional.of(Thread.currentThread().getName() + " will leave to wait set")
//                                            .ifPresent(System.out::println);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }.start();
//                });
//
//        Thread.sleep(3_000);
//
//        IntStream.rangeClosed(1, 10)
//                .forEach(i -> {
//                            synchronized (LOCK) {
//                                LOCK.notify();
//                                try {
//                                    Thread.sleep(1000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                );
    }

}
