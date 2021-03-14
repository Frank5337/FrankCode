package com.zbl.wwj.concurrent.step2.p56;

import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/14
 * @Description:
 * @Version: $
 */
public class WriterWorker extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());

    private final SharedData data;

    private final String filler;

    private int index = 0;

    public WriterWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
        super.setName("Writer-" + random.nextInt());
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                System.out.println(Thread.currentThread().getName() + " write start   " + c);
                data.write(c);
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " write complete " + c);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }


}

