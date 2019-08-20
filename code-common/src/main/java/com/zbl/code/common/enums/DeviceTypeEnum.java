package com.zbl.code.common.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;


/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 设备类型
 * @Version: $
 */
public enum DeviceTypeEnum implements BaseEnum {
    PC(1, "PC"),
    ANDROID(2, "ANDROID"),
    IOS(3, "IOS"),
    WAP(4, "WAP");

    private final Integer code;
    private final String msg;

    DeviceTypeEnum(Integer code, String msg) {
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

    public static DeviceTypeEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, DeviceTypeEnum.class);
    }

    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, DeviceTypeEnum.class);
    }
}
