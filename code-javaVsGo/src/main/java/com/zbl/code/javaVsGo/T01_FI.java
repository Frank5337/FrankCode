package com.zbl.code.javaVsGo;

/**
 * @Author: zbl
 * @Date: Created in 11:03 2020/8/10
 * @Description:
 * @Version: $
 */
public class T01_FI {

    /**
     * 相当于go中的
     * var f1 = func(word string) {
     *      fmt.Println(word, "f1")
     * }
     *
     * @param args
     */
    public static void main(String[] args) {
        Runnable r = () -> System.out.print("hello go");
        r.run();
        // lambda expressions
    }
}
