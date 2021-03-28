package com.zbl.wwj.concurrent.step2.p63;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/24
 * @Description:
 * @Version: $
 */
public class Request {

    private final String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
