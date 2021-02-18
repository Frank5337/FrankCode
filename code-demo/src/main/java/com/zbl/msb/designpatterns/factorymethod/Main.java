package com.zbl.msb.designpatterns.factorymethod;

/**
 * @Author: zbl
 * @Date: Created in 16:43 2020/1/2
 * @Description: 工厂方法
 * @Version: $
 */
public class Main {
    public static void main(String[] args) {
        MoveAble m = new CarFactory().createCar();
        m.go();
    }
}
