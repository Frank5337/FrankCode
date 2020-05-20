package com.zbl.tank;

import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 18:16 2020/5/19
 * @Description:
 * @Version: $
 */
@Data
public class Tank {
    private int x, y;

    private Dir dir = Dir.DOWN;

    private static final int SPEED = 5;

    private boolean moving = false;

    private TankFrame tf = null;

    private boolean isEnemy;

    public Tank(int x, int y, Dir dir, TankFrame tf, boolean isEnemy) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.isEnemy = isEnemy;
    }

    public void paint(Graphics g) {
        Color rawColor = g.getColor();
        //填充矩形  从左上角开始 向右X 向下Y
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 50, 50);
        g.setColor(rawColor);
        if (isEnemy) {
            new Random().nextInt(3);
            //move();
        }
        if (!moving) {
            return;
        }
        move();
    }

    private void move() {
        if (isEnemy) {

        }
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
            case LEFT_UP:
                x -= SPEED;
                y -= SPEED;
                break;
            case LEFT_DOWN:
                x -= SPEED;
                y += SPEED;
                break;
            case RIGHT_UP:
                x += SPEED;
                y -= SPEED;
                break;
            case RIGHT_DOWN:
                x += SPEED;
                y += SPEED;
        }
    }

    public void fire() {
        tf.bullets.add(new Bullet(this.x + (int) 17.5, this.y + (int) 17.5, this.dir, this.tf));

    }

//    public static void main(String[] args) {
//        Random random = new Random();
//        System.out.println(random.nextInt(4));
//        for (;;) {
//            if (random.nextInt(4) == 0) {
//                System.out.println(123);
//                return;
//            }
//        }
//    }
}
