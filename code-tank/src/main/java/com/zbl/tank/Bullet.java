package com.zbl.tank;

import java.awt.*;

/**
 * @Author: zbl
 * @Date: Created in 10:25 2020/5/20
 * @Description:
 * @Version: $
 */
public class Bullet {
    private static final int SPEED = 10;

    public static int WIDTH = ResourceManager.bulletD.getWidth(), HEIGHT = ResourceManager.bulletD.getHeight();

    private int x, y;

    private Dir dir;

    private boolean live = true;

    private TankFrame tf = null;

    public boolean isLive() {
        return live;
    }

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        //不删除会有内存泄露问题
        if (!live) {
            tf.bullets.remove(this);
        }
//        Color c = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(c);
        Image image = null;
        switch (dir) {
            case LEFT:
                image = ResourceManager.bulletL;
                break;
            case RIGHT:
                image = ResourceManager.bulletR;
                break;
            case UP:
                image = ResourceManager.bulletU;
                break;
            case DOWN:
                image = ResourceManager.bulletD;
                break;
        }
        g.drawImage(image, x, y, null);

        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;
    }

}
