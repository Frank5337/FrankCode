package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 14:33
 * @Description: 接受异步消息的主动方法
 * @Version: $
 */
public interface ActiveObject {

    Result makeString(int count, char fillChar);

    void displayString(String text);
}
