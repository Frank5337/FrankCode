package com.zbl.wwj.concurrent.step2.p51;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/11 14:01
 * @Description:
 * @Version: $
 */
public class ObserverClient {

    public static void main(String[] args) {
        final Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        System.out.println("==================");
        subject.update(10);
        System.out.println("==================");
        subject.update(10);
        System.out.println("==================");
        subject.update(15);
    }
}
