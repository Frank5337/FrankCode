package com.zbl.tank;

/**
 * @Author: zbl
 * @Date: 23:15 2020/5/16
 * @Description:
 */
public class TankRun {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        //初始化敌方坦克
        for (int i = 0; i <5 ; i++) {
            tf.enemy.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, tf));
        }

        //背景音乐
        //new Thread(() -> new Audio("main/resources/audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
