package com.zbl.code.common.base;

/**
 * @Author: zbl
 * @Date: Created in 16:23 2019/8/20
 * @Description: 全局常量
 * @Version: $
 */
public class Global {
    // 验证相关key（登录、图形验证码、验证码）
    public static final String KEY_USER_ID = "code_user_id";
    public static final String KEY_ADMIN_ID = "code_admin_id";
    public static final String KEY_ADMIN_NAME = "code_admin_name";
    public static final String KEY_ADMIN_LEVEL = "code_admin_level";
    public static final String KEY_ADMIN_ROLE_ID = "code_admin_role_id";
    public static final String KEY_ADMIN_HEAD_OFFICE_ID = "code_admin_head_office_id";
    public static final String KEY_ADMIN_BRANCH_OFFICE_ID = "code_admin_branch_office_id";
    public static final String KEY_ADMIN_ORGANIZATION_ID = "code_admin_organization_id";

    // 默认分页
    public static final String PAGE_NUM = "1";
    public static final String PAGE_SIZE = "10";

    // 默认时间格式
    public static final String DEF_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    // 锁时间(5分钟)
    public static final Long LOCK_TIME = 300000L;

    // 登录会话保持时间
    public static final Integer SESSION_LIVE_TIME = 36000;

    // 错误登录冻结时间
    public static final Integer FAILED_SIGN_IN_LIVE_TIME = 7200;

    // 系统默认密码
    public static final String DEF_PASSWORD = "1234567890";

    // 验证码
    public static final Integer LEN_VALIDATE_CODE = 4;
    public static final Integer LEN_VERIFY_CODE = 6;
    public static final String DEF_VERIFY_CODE = "555555";
    public static final Integer VERIFY_LIVE_TIME = 300;
    public static final Integer VERIFY_LIMIT_TIME = 60;

    // 默认(相对地址)
    public static final String DEF_USER_AVATAR = "system/default/avatar";
    public static final String DEF_SHARE_ICON = "system/default/share_icon";
    public static final String DEF_USER_SUMMARY = "happy code";

    // 微信token和ticket有效期
    public static final Integer WE_CHAT_LIVE_TIME = 7000;
}
