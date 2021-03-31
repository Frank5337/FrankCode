package com.zbl.wwj.concurrent.step2.p73;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 11:00
 * @Description:
 * @Version: $
 */
public class CounterTest {

    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        Thread.sleep(10_000L);

        counterIncrement.close();
    }

}
