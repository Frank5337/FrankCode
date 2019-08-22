package com.zbl.code.system.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;

/**
 * @Author: Created by hook on 2018/8/2.
 * <p>
 * 角色类型
 */
public enum RoleTypeEnum implements BaseEnum {
    INNER(1, "内置"),
    NORMAL(2, "普通");

    private final Integer code;
    private final String msg;

    RoleTypeEnum(Integer code, String msg) {
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

    public static RoleTypeEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, RoleTypeEnum.class);
    }

    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, RoleTypeEnum.class);
    }
}
