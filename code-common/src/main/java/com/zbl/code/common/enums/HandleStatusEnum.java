package com.zbl.code.common.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 处理状态
 * @Version: $
 */
public enum HandleStatusEnum implements BaseEnum {
    DOING(0, "处理中"),
    SUCCESS(1, "已处理"),
    FAILED(2, "已处理");

    private final Integer code;
    private final String msg;

    HandleStatusEnum(Integer code, String msg) {
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

    public static HandleStatusEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, HandleStatusEnum.class);
    }

    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, HandleStatusEnum.class);
    }
}
