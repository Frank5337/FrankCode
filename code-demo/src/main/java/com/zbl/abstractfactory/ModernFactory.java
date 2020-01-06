package com.zbl.abstractfactory;

/**
 * @Author: zbl
 * @Date: Created in 14:31 2020/1/6
 * @Description: 人类工厂
 * @Version: $
 */
public class ModernFactory extends AbstractFactory{
    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    VehicleInterface createVehicle() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new AK47();
    }
}
