package com.zbl.tank;

/**
 * @Author: zbl
 * @Date: Created in 15:37 2020/8/16
 * @Description:
 * @Version: $
 */
public class FourDirFireStrategy implements FireStrategy {

    /**
     * 向4个方向发射炮弹
     *
     * @param t
     */
    @Override
    public void fire(Tank t) {
        if (!t.isLiving()) return;
        //计算子弹xy位置
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.WIDTH / 2;

        Dir[] dirs = new Dir[]{Dir.UP, Dir.DOWN, Dir.LEFT, Dir.RIGHT};
        for (Dir dir : dirs) {
            new Bullet(bx, by, dir, t.group, t.tf);
        }

        if (t.group == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }

    }
}
