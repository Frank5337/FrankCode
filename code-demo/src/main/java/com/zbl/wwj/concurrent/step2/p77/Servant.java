package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 14:53
 * @Description:
 * @Version: $
 */
class Servant implements ActiveObject {

    @Override
    public Result makeString(int count, char fillChar) {
        char[] buff = new char[count];
        for (int i = 0; i < count; i++) {
            buff[i] = fillChar;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RealResult(new String(buff));
    }

    @Override
    public void displayString(String text) {
        try {
            System.out.println("Display: " + text);
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
