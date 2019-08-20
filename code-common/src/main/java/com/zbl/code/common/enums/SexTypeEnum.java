package com.zbl.code.common.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 性别
 * @Version: $
 */
public enum SexTypeEnum implements BaseEnum {
    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private final Integer code;
    private final String msg;

    SexTypeEnum(Integer code, String msg) {
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

    public static SexTypeEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, SexTypeEnum.class);
    }

    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, SexTypeEnum.class);
    }

    public static Integer getCode(String msg) {
        return EnumUtil.getCode(msg, SexTypeEnum.class);
    }
}
