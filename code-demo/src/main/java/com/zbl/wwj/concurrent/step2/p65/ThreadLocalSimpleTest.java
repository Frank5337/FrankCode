package com.zbl.wwj.concurrent.step2.p65;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/25
 * @Description:
 * @Version: $
 */
public class ThreadLocalSimpleTest {

//    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
//        @Override
//        protected String initialValue() {
//            return "Alex";
//        }
//    };

    private static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Alex");

    public static void main(String[] args) throws InterruptedException {
//        threadLocal.set("Alex");
        Thread.sleep(1000);
        String value = threadLocal.get();
        System.out.println(value);

    }

}
