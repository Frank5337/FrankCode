package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 15:40
 * @Description:
 * @Version: $
 */
public class MakerClientThread extends Thread {


    private final ActiveObject activeObject;

    private final char fillChar;

    public MakerClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; ; i++) {
                Result result = activeObject.makeString(i + 1, fillChar);
                Thread.sleep(20);
                String value = (String) result.getResultValue();
                System.out.println(Thread.currentThread().getName() + ": value= " + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
