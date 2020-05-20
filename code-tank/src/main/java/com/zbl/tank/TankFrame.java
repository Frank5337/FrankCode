package com.zbl.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: zbl
 * @Date: 23:29 2020/5/16
 * @Description:
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.DOWN);

    public TankFrame() {
        this.setSize(800, 600);
        this.setTitle("坦克大战");
        //设为可调整大小
        this.setResizable(false);
        //设置可见的
        this.setVisible(true);

        //按键监听
        this.addKeyListener(new MyKeyListener());

        this.addWindowListener(new WindowAdapter() {
            /**
             * 点击右上角的X会触发此方法
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                //系统退出
                System.exit(0);
            }
        });
    }

    /**
     * 窗口重新绘制的时候 会触发
     * 会把背景清除重新画
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
    }

    /**
     * 键盘监听处理对象
     */
    class MyKeyListener extends KeyAdapter {

        boolean bl = false;
        boolean bu = false;
        boolean br = false;
        boolean bd = false;

        /**
         * keycode 37 = Left ←
         * keycode 38 = Up ↑
         * keycode 39 = Right →
         * keycode 40 = Down ↓
         * 会在一个键被按下去的调用
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                default:
                    break;
            }
            System.out.println(e.getKeyCode());
            setMainTankDir();
//            if (bl && bu) {
//                x -= 10;
//                y -= 10;
//                return;
//            }
//            if (bl && bd) {
//                x -= 10;
//                y += 10;
//                return;
//            }
//            if (br && bu) {
//                x += 10;
//                y -= 10;
//                return;
//            }
//            if (br && bd) {
//                x += 10;
//                y += 10;
//                return;
//            }
//            if (bu) {
//                y -= 10;
//            }
//            if (bd) {
//                y += 10;
//            }
//            if (bl) {
//                x -= 10;
//            }
//            if (br) {
//                x += 10;
//            }

        }

        /**
         * 会在一个键抬起来的时候调用
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (bl) myTank.setDir(Dir.LEFT);
            if (br) myTank.setDir(Dir.RIGHT);
            if (bu) myTank.setDir(Dir.UP);
            if (bd) myTank.setDir(Dir.DOWN);
        }
    }
}
