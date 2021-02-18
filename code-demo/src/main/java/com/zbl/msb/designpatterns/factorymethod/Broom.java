package com.zbl.msb.designpatterns.factorymethod;

/**
 * @Author: zbl
 * @Date: Created in 18:47 2020/1/3
 * @Description:
 * @Version: $
 */
public class Broom implements MoveAble{


    @Override
    public void go() {
        System.out.println("Broom broom");
    }
}
