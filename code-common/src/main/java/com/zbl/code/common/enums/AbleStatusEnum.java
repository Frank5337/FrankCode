package com.zbl.code.common.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;


/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 启用状态
 * @Version: $
 */
public enum AbleStatusEnum implements BaseEnum {
    DISABLE(0, "未启用"),
    ENABLE(1, "已启用");

    private final Integer code;
    private final String msg;

    AbleStatusEnum(Integer code, String msg) {
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

    public static AbleStatusEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, AbleStatusEnum.class);
    }

    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, AbleStatusEnum.class);
    }
}