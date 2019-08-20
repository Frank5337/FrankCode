package com.zbl.code.common.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 显示|隐藏状态
 * @Version: $
 */
public enum DisplayStatusEnum implements BaseEnum {
    HIDE(0, "隐藏"),
    SHOW(1, "显示");

    private final Integer code;
    private final String msg;

    DisplayStatusEnum(Integer code, String msg) {
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

    public static DisplayStatusEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, DisplayStatusEnum.class);
    }

    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, DisplayStatusEnum.class);
    }
}
