package com.zbl.tank;

/**
 * @Author: zbl
 * @Date: Created in 15:37 2020/8/16
 * @Description:
 * @Version: $
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {

        //计算子弹xy位置
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.WIDTH / 2;
        new Bullet(bx, by, t.dir, t.group, t.tf);

        if (t.group == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
