package com.zbl.msb.unsafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zbl
 * @Date: Created in 17:36 2020/7/27
 * @Description:
 * @Version: $
 */
public class T01_SimpleDataFormat {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date) {
        return dateFormat.format(date);
    }

    public static Date parse(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr);
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] strs = new String[]{"2016-01-01 10:24:00", "2016-01-02 20:48:00", "2016-01-11 12:24:00"};
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i1 = 0; i1 < 10; i1++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "\t" + parse(strs[i1 % strs.length]));
                        Thread.sleep(100);
                    } catch (ParseException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        latch.countDown();
    }
}