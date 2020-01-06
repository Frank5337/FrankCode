package com.zbl.factorymethod;

/**
 * @Author: zbl
 * @Date: Created in 18:45 2020/1/3
 * @Description: 交通工具 工厂
 * @Version: $ 简单工厂的可扩展性不好
 */
public class SimpleVehicleFactory {

    public Car createCar() {
        //before processing
        return new Car();
    }

    public Broom createBroom() {
        return new Broom();
    }
}
