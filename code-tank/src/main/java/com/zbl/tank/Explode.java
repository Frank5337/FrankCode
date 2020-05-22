package com.zbl.tank;

import java.awt.*;

/**
 * @Author: zbl
 * @Date: Created in 10:25 2020/5/20
 * @Description:
 * @Version: $
 */
public class Explode {
    public static int WIDTH = ResourceManager.explodes[0].getWidth(), HEIGHT = ResourceManager.explodes[0].getHeight();

    private int x, y;

    private boolean living = true;

    private TankFrame tf = null;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    private int step = 0;

    public void paint(Graphics g) {
        if (!living) {
            tf.explodes.remove(this);
        }
        g.drawImage(ResourceManager.explodes[step++], x, y, null);
        if (step >= ResourceManager.explodes.length) {
            step = 0;
            die();
        }

    }

    private void die() {
        living = false;
    }
}
