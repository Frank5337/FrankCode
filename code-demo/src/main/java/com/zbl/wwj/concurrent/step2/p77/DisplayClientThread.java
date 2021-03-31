package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 15:37
 * @Description:
 * @Version: $
 */
public class DisplayClientThread extends Thread {

    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        for (int i = 0; ; i++) {
            String text = Thread.currentThread().getName() + "=>" + i;
            activeObject.displayString(text);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
