package com.zbl.abstractfactory;

/**
 * @Author: zbl
 * @Date: Created in 16:43 2020/1/2
 * @Description:
 * @Version: $
 */
public class Car extends Vehicle implements VehicleInterface {

    public void go(){
        System.out.println("go go go");
    }
}
