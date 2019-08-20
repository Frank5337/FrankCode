package com.zbl.code.common.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 证件类型
 * @Version: $
 */
public enum IdCardTypeEnum implements BaseEnum {
    ID_CARD(1, "身份证"),
    HONG_KONG_MACAU(2, "港澳通行证"),
    OFFICIAL_CARD(3, "军官证/士兵证");

    private final Integer code;
    private final String msg;

    IdCardTypeEnum(Integer code, String msg) {
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

    public static IdCardTypeEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, IdCardTypeEnum.class);
    }

    public static IdCardTypeEnum getEnum(String msg) {
        return EnumUtil.getEnum(msg, IdCardTypeEnum.class);
    }


    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, IdCardTypeEnum.class);
    }

    public static Integer getCode(String msg) {
        return EnumUtil.getCode(msg, IdCardTypeEnum.class);
    }
}
