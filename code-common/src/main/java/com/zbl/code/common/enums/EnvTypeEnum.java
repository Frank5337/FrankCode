package com.zbl.code.common.enums;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 环境类型
 * @Version: $
 */
public enum EnvTypeEnum {
    DEV("dev", "开发环境"),
    TEST("test", "测试环境"),
    PROD("prod", "生产环境");

    private final String code;
    private final String msg;

    EnvTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean is(String code) {
        return this.code.equals(code);
    }

    public static EnvTypeEnum getEnum(String code) {
        for (EnvTypeEnum ete : EnvTypeEnum.values()) {
            if (ete.getCode().equals(code)) {
                return ete;
            }
        }

        return null;
    }

    public static String getMsg(String code) {
        EnvTypeEnum ete = getEnum(code);
        return ete == null ? null : ete.getMsg();
    }
}
