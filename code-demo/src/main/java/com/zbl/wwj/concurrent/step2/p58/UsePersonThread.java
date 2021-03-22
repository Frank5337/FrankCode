package com.zbl.wwj.concurrent.step2.p58;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/21
 * @Description:
 * @Version: $
 */
public class UsePersonThread extends Thread {

    private Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " print " + person);
        }
    }
}
