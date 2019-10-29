package com.zbl.code.common.util;

import com.zbl.code.common.base.BaseEnum;

/**
 * @Author: zbl
 * @Date: Created in 16:01 2019/8/20
 * @Description: 通过code获取枚举类型
 * @Version: $
 */
public class EnumUtil {

    public static <T extends BaseEnum> T getEnum(Integer code, Class<T> cls) {
        if (code == null) {
            return null;
        }

        for (T each : cls.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }

        return null;
    }

    public static <T extends BaseEnum> T getEnum(String msg, Class<T> cls) {
        for (T each : cls.getEnumConstants()) {
            if (msg.equals(each.getMsg())) {
                return each;
            }
        }

        return null;
    }

    public static <T extends BaseEnum> String getMsg(Integer code, Class<T> cls) {
        T t = getEnum(code, cls);
        return t == null ? null : t.getMsg();
    }

    public static <T extends BaseEnum> Integer getCode(String msg, Class<T> cls) {
        T t = getEnum(msg, cls);
        return t == null ? null : t.getCode();
    }
}
