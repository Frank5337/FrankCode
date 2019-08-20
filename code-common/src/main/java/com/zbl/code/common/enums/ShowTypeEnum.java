package com.zbl.code.common.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 设备类型
 * @Version: $
 */
public enum ShowTypeEnum implements BaseEnum {
    PC(1, "PC"),
    APP(2, "APP"),
    ALL(3, "ALL"),
    ;

    private final Integer code;
    private final String msg;

    ShowTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public boolean is(Integer code) {
        return this.code.equals(code);
    }

    public static ShowTypeEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, ShowTypeEnum.class);
    }

    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, ShowTypeEnum.class);
    }
}
