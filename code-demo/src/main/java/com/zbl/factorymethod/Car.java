package com.zbl.factorymethod;

/**
 * @Author: zbl
 * @Date: Created in 16:43 2020/1/2
 * @Description:
 * @Version: $
 */
public class Car implements MoveAble{

    @Override
    public void go(){
        System.out.println("go go go");
    }
}
