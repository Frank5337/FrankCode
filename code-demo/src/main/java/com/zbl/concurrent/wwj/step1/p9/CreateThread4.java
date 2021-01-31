package com.zbl.concurrent.wwj.step1.p9;

import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/24
 * @Description:
 * @Version: $
 */
public class CreateThread4 {

    private static int counter;

    public static void main(String[] args) {
        Thread t1 = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                } catch (Error e) {
                    System.out.println(counter);
                }
            }

            private void add(int i) {
                ++counter;
                add(i + 1);
            }
        }, "test", 1 << 24);

        t1.start();
    }

    @Test
    public void test01() throws Exception {
        System.out.println(1 << 24);
    }


}
