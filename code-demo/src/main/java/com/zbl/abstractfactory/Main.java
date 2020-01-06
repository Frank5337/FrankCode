package com.zbl.abstractfactory;

/**
 * @Author: zbl
 * @Date: Created in 16:43 2020/1/2
 * @Description: 工厂方法
 * @Version: $
 *                            AbstractFactory
 *                                   ↓
 *            Food        Vehicle/VehicleInterface        Weapon
 *
 *                    MagicFactory        ModernFactory
 * 工厂方法:  比较方便产品方面的扩展
 * 工厂方法 产品维度扩展
 * 抽象工厂 产品一族进行扩展
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory factory = new MagicFactory();
        VehicleInterface c = factory.createVehicle();
        c.go();
        Weapon w = factory.createWeapon();
        w.shoot();
        Food f = factory.createFood();
        f.printName();
    }
}
