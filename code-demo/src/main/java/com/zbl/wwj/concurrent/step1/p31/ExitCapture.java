package com.zbl.wwj.concurrent.step1.p31;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/8 13:01
 * @Description:   Create by Frozen 2021-02-08
 * @Version: $
 */
public class ExitCapture {
    //测试钩子函数
    //javac ExitCapture.java
    //java -cp . ExitCapture
    //nohup java -cp . ExitCapture & 后台启动
    //jps找到进程后 测试 kill 与 kill -9的区别
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The application will be exit.");
            notifyAndRelease();
        }));
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1_000L);
                System.out.println("I'm working");
                i++;

            } catch (Throwable e) {
                //ignore
            }

            if (i > 20) {
                throw new RuntimeException("Error");
            }
        }


    }

    public static void notifyAndRelease() {
        System.out.println("notify to admin.");
        try {
            Thread.sleep(1_000L);
        } catch (Throwable e) {

        }

        System.out.println("will release resource (socket.file.collection)");
        try {
            Thread.sleep(1_000L);
        } catch (Throwable e) {

        }

        System.out.println("notify and release done.");

    }

}

