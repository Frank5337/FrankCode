package com.zbl.wwj.concurrent.step2.p68;

import java.io.IOException;
import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/28
 * @Description:
 * @Version: $
 */
public class WaiterThread extends Thread {

    private final BalkingData balkingData;

    private final Random random = new Random(System.currentTimeMillis());

    public WaiterThread(BalkingData balkingData) {
        super("Waiter");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                balkingData.save();
                Thread.sleep(random.nextInt(1000));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
