package com.zbl.code.system.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;

/**
 *
 * @author zbl
 * @date 2019/9/3
 * <p>
 * 管理员级别
 */
public enum AdminLevelEnum implements BaseEnum {
    PLATFORM(0, "平台", ""),
    HEAD_OFFICE(1, "总公司", "inner_role_head_office"),
    BRANCH_OFFICE(2, "分公司", "inner_role_branch_office"),
    ORGANIZATION(3, "机构", "inner_role_organization"),
    TEACHER(4, "老师", "inner_role_teacher");

    private final Integer code;
    private final String msg;
    private final String key;

    AdminLevelEnum(Integer code, String msg, String key) {
        this.code = code;
        this.msg = msg;
        this.key = key;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean is(Integer code) {
        return this.code.equals(code);
    }

    public static AdminLevelEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, AdminLevelEnum.class);
    }

    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, AdminLevelEnum.class);
    }
}
