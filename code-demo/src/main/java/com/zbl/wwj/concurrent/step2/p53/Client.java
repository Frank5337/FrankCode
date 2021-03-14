package com.zbl.wwj.concurrent.step2.p53;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/14
 * @Description:
 * @Version: $
 */


public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User("Baobao", "Beijing", gate);
        User sh = new User("Shang", "ShangHai", gate);
        User gz = new User("Guang", "GuangZhou", gate);
        bj.start();
        sh.start();
        gz.start();
    }
}
