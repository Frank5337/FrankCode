package com.zbl.code.common.base;

/**
 * @Author: zbl
 * @Date: Created in 15:29 2019/8/20
 * @Description: 基础枚举
 * @Version: $
 */
public interface BaseEnum {
    Integer getCode();

    String getMsg();

    boolean is(Integer code);
}
