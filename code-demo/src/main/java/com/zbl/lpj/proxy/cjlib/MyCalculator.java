package com.zbl.lpj.proxy.cjlib;

public class MyCalculator {

    public int add(int i, int j) {
        int result = i + j;
        System.out.println("result = i + j");
        return result;
    }

    public int sub(int i, int j) {
        int result = i - j;
        return result;
    }

    public int mult(int i, int j) {
        int result = i * j;
        return result;
    }

    public int div(int i, int j) {
        int result = i / j;
        return result;
    }
}