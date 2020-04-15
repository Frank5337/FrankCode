package com.zbl.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 17:34 2020/4/13
 * @Description: 内存溢出demo
 * @Version: $
 */
public class OOMDemo {

    private static final Integer K = 1024;

    public static void main(String[] args) {
        int size = K * K * 8;
        List<byte[] > list = new ArrayList<>();
        for (int i = 0; i < K ; i++) {
            System.out.println("JVM 写入数据" + (i*8) + "M");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new byte[size]);
        }
    }


}
