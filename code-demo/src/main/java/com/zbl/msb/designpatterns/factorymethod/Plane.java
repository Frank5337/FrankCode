package com.zbl.msb.designpatterns.factorymethod;

/**
 * @Author: zbl
 * @Date: Created in 16:49 2020/1/2
 * @Description:
 * @Version: $
 */
public class Plane implements MoveAble{

    @Override
    public void go() {
        System.out.println("fly in the sky");
    }
}
