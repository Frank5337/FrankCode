package com.zbl.wwj.concurrent.step2.p72;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 10:42
 * @Description:
 * @Version: $
 */
public class Message {

    private final String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
