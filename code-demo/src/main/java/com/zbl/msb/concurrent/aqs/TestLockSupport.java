package com.zbl.msb.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: zbl
 * @Date: 17:16 2020/6/14
 * @Description:
 */
public class TestLockSupport {

    public static void main(String[] args) {
        Thread t = new Thread( () -> {
            for (int i = 0; i <10 ; i++) {
                System.out.println(i);
                if (i == 5) {
                    //阻塞
                    LockSupport.park();
                }
                if (i == 6) {
                    //阻塞
                    LockSupport.park();
                }

            }
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.start();

        //唤醒
        LockSupport.unpark(t);
        //令牌只能用一次 第二次unpark没用了  所以,  如果两次park 就会永远阻塞下去
        //LockSupport.unpark(t);

    }
}
