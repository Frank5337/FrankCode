package com.zbl.test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: zbl
 * @Date: 23:20 2020/1/1
 * @Description:
 */
public class TimerTest {
    public static void main(String[] args) {
    /*    Timer timer = new Timer();
        try {
            //timer.schedule(new TimerTastCus(), 2000 + 2000 * 10);
            timer.schedule(new TimerTastCus(), 10 * 1_000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        timer.cancel();*/
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("10秒后执行了任务");
                timer.cancel();
            }
        }, 10 * 1_000);
    }
}
