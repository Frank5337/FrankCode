package com.zbl.test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: zbl
 * @Date: 23:21 2020/1/1
 * @Description: 定时器
 */
public class TimerTastCus extends TimerTask {

    @Override
    public void run() {
        int count = 10;
        count = (count + 1) % 2;
        System.out.println("Boom boom");
        new Timer().schedule(new TimerTastCus(), 2000 + 2000 * count);
    }

}
