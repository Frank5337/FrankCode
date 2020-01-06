package com.zbl.abstractfactory;

/**
 * @Author: zbl
 * @Date: Created in 14:22 2020/1/6
 * @Description: 抽象工厂  产品族, human/magic
 * @Version: $   名词用抽象类,  形容词用接口
 */
public abstract class AbstractFactory {

    abstract Food createFood();

    abstract VehicleInterface createVehicle();

    abstract Weapon createWeapon();

}
