package com.zbl.demo;

import java.util.HashMap;

/**
 * @Author: zbl
 * @Date: Created in 16:56 2020/4/9
 * @Description:
 * @Version: $
 */
public class HashMapDemo {

    /**
     * ^ 异或, 相同为0 不同为1
     * & 与  , 都为1才为1
     * | 或  , 只要有1个为1 则为1
     * @param args
     */
    public static void main(String[] args) {
        HashMap map = new HashMap(10);
        System.out.println(15 >>> 1);
        System.out.println(15 | 7);
    }

}
