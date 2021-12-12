package com.zbl.test;

/**
 * @Author: zbl
 * @Date: Created in 2021/7/29 10:08
 * @Description:
 * @Version: $
 */
public class Main {

    static class Father {
        private int money = 1;

        public Father() {
            this.money = 2;
//            this.showMoney();
        }

        public void showMoney() {
            System.out.println("I am father, I have $" + this.money + ".");
        }
    }

    static class Son extends Father {
        private int money = 3;

        public Son() {
            this.money = 4;
            this.showMoney();
        }

        @Override
        public void showMoney() {
            System.out.println("I am son, I have $" + this.money + ".");
        }
    }

    public static void main(String[] args) {
        final Son son = new Son();
        System.out.println("====================================");
        System.out.println("I am son, I have $" + son.money + ".");

        Father father = new Father();

        System.out.println(System.currentTimeMillis() / 1000);
    }

}
