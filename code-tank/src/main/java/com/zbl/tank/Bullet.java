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

    private boolean living = true;

    private TankFrame tf = null;

    private Group group = Group.BAD;

    public Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        setValue(x, y, dir, null, tf);
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        setValue(x, y, dir, group, tf);
    }

    private void setValue(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        if (group != null) {
            this.group = group;
        }
        this.tf = tf;
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = WIDTH;
        this.rect.height = HEIGHT;

        tf.bullets.add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        //不删除会有内存泄露问题
        if (!living) {
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

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }

    /**
     *
     * @param tank
     */
    public void collideWith(Tank tank) {
        //友军伤害
        if (this.group == tank.getGroup())
            return;
        //如果坦克已经死了
        if (!tank.isLiving()) {
            //必须让子弹死亡, 否则变成了一个隐藏的不会爆炸的子弹, 会造成内存泄露
            die();
            return;
        }
        //TODO Rectangle太多了, 用一个rect来记录子弹的位置
        //判断是否相交
        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int boomX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int boomY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(new Explode(boomX, boomY, tf));
        }

    }

    private void die() {
        living = false;
    }
}
