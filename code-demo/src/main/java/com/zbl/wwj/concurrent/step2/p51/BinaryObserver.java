package com.zbl.wwj.concurrent.step2.p51;


/**
 * @Author: zbl
 * @Date: Created in 2021/3/11 13:55
 * @Description:
 * @Version: $
 */
public class BinaryObserver extends ObServer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    protected void update() {
        System.out.println("BinaryObserver " + Integer.toBinaryString(subject.getState()));
    }
}
