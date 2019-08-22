package com.zbl.code.common.enums;

import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.util.EnumUtil;

/**
 * @Author: zbl
 * @Date: Created in 15:58 2019/8/20
 * @Description: 错误码及错误信息
 * @Version: $
 */
public enum  ErrorCodeEnum implements BaseEnum {
    GL_DEFAULT(100000000, "服务器繁忙，请稍后操作"),
    GL_ID_BAD_CLOCK(100000001, "时钟回拨"),
    GL_ID_BAD_WORK_ID(100000002, "工作机器id错误"),
    GL_ID_BAD_DATA_ID(100000003, "数据中心id错误"),
    GL_NEED_SIGN_IN(100000004, "请先登录"),
    GL_NO_PERMISSION(100000005, "无访问权限"),
    GL_BAD_PARAM(100000006, "参数错误"),
    GL_BAD_ACCOUNT(100000007, "用户名或密码错误"),
    GL_BAD_PASSWORD(100000008, "密码错误"),
    GL_PHONE_EXIST(100000009, "手机号已存在"),
    GL_BAD_EXCEL(100000010, "excel文件格式错误"),

    // 系统模块
    SYS_ROLE_EXIST(100010001, "已存在同名角色"),
    SYS_PERMISSION_EXIST(100010002, "已存在相同权限"),
    SYS_PERMISSION_NOT_EXIST(100010003, "未配置相关权限"),
    SYS_CONFIG_KEY_EXIST(100010004, "配置已存在"),
    SYS_CONFIG_KEY_NOT_EXIST(100010005, "未进行相关配置");

    private final Integer code;
    private final String msg;

    ErrorCodeEnum(Integer code, String msg) {
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

    public static ErrorCodeEnum getEnum(Integer code) {
        return EnumUtil.getEnum(code, ErrorCodeEnum.class);
    }

    public static String getMsg(Integer code) {
        return EnumUtil.getMsg(code, ErrorCodeEnum.class);
    }
}
