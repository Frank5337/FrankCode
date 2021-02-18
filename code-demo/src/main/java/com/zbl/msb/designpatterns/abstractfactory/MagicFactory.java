package com.zbl.msb.designpatterns.abstractfactory;

/**
 * @Author: zbl
 * @Date: Created in 14:31 2020/1/6
 * @Description: 魔法工厂
 * @Version: $
 */
public class MagicFactory extends AbstractFactory{
    @Override
    Food createFood() {
        return new MushRoom();
    }

    @Override
    VehicleInterface createVehicle() {
        return new Broom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }
}
