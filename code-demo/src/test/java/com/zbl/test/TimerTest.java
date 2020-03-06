package com.zbl.test;

import java.util.Timer;

/**
 * @Author: zbl
 * @Date: 23:20 2020/1/1
 * @Description:
 */
public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        try {
            timer.schedule(new TimerTastCus(), 2000 + 2000 * 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
