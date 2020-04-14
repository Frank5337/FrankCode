package com.zbl.demo;

/**
 * @Author: zbl
 * @Date: 1:59 2020/4/11
 * @Description:
 */
public class PublicPrivateFinalDemo {

    public static void main(String[] args) {
        PublicPrivateFinalDemo p = new PublicPrivateFinalDemo();
//        p.m1();
        p.m2();
        p.m3();
        p.m2();
        p.m1();
    }

    public void m1() {
        long s = System.currentTimeMillis();
        Object o;
        for (int i = 0; i <1_0000_000 ; i++) {
            o = new Object().hashCode();
        }
        System.out.print("m1: ");
        System.out.println(System.currentTimeMillis() - s);
    }

    private void m2() {
        long s = System.currentTimeMillis();
        Object o;
        for (int i = 0; i <1_0000_000 ; i++) {
            o = new Object().hashCode();
        }
        System.out.print("m2: ");
        System.out.println(System.currentTimeMillis() - s);
    }

    final void m3() {
        long s = System.currentTimeMillis();
        Object o;
        for (int i = 0; i <1_0000_000 ; i++) {
            o = new Object().hashCode();
        }
        System.out.print("m3: ");
        System.out.println(System.currentTimeMillis() - s);
    }

}
