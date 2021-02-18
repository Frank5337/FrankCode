package com.zbl.msb.designpatterns.factorymethod;

/**
 * @Author: zbl
 * @Date: Created in 18:50 2020/1/3
 * @Description:
 * @Version: $
 */
public class CarFactory {

    public MoveAble createCar() {
        System.out.println("a car created");
        return new Car();
    }
}
