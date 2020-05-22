package com.zbl.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: 23:29 2020/5/16
 * @Description:
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);

    List<Tank> enemy = new ArrayList<>();

    List<Bullet> bullets = new ArrayList<>();

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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
     * 解决双缓冲
     * 现在内存中把图片画满
     * 然后把图片放到屏幕上
     * 把内存的内容复制到显存
     */
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0 , 0, GAME_WIDTH, GAME_HEIGHT);
        paint(gOffScreen);
        //画到屏幕
        g.drawImage(offScreenImage, 0, 0, null);
        g.setColor(c);
    }

    /**
     * 窗口重新绘制的时候 会触发
     * 会把背景清除重新画
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量: " + bullets.size(), 10 ,60);
        g.drawString("敌人的数量: " + enemy.size(), 10 ,80);
        g.setColor(color);
        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < enemy.size() ; i++) {
            enemy.get(i).paint(g);
        }
        //另一种删除方式
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
//            Bullet b = it.next();
//            b.paint(g);
//            if (!b.isLive()) it.remove();
//        }
        // forEach 遍历 删除 会有并发修改异常
        // 用内部的Iterator遍历的时候 不能删除
        // Iterator遍历的时候 不允许其他地方进行删除, 只允许当前遍历的地方删除
        // 有一个mutex锁
        //bullets.forEach(b -> b.paint(g));
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j <enemy.size() ; j++) {
                bullets.get(i).collideWith(enemy.get(j));
            }
        }
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
                case KeyEvent.VK_SPACE:
                    myTank.fire(myTank.getGroup());
                    break;
                case KeyEvent.VK_CONTROL:
                    addEnemy();
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bd && !bl && !bu && !br) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
            }
//            多个按键先注释, 子弹不斜着打
//            if (bl && bu) {
//                myTank.setDir(Dir.LEFT_UP);
//                return;
//            }
//            if (bl && bd) {
//                myTank.setDir(Dir.LEFT_DOWN);
//                return;
//            }
//            if (br && bu) {
//                myTank.setDir(Dir.RIGHT_UP);
//                return;
//            }
//            if (br && bd) {
//                myTank.setDir(Dir.RIGHT_DOWN);
//                return;
//            }
            if (bl) myTank.setDir(Dir.LEFT);
            if (br) myTank.setDir(Dir.RIGHT);
            if (bu) myTank.setDir(Dir.UP);
            if (bd) myTank.setDir(Dir.DOWN);

        }
    }

    private void addEnemy(){
        for (int i = 0; i <5 ; i++) {
            enemy.add(new Tank(50 + i * 80, 200, Dir.DOWN, this));
        }
    }
}
