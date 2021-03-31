package com.zbl.wwj.concurrent.step2.p75;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 13:46
 * @Description:
 * @Version: $
 */
public class Request {

    private final String name;

    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    //this 默认打类名+地址值
    public void execute() {
        System.out.println(Thread.currentThread().getName()+ " execute " + this);
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
