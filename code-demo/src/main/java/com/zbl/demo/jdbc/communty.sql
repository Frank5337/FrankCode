DROP
TABLES IF EXISTS `t_activity`;
CREATE TABLE `t_activity`
(
    `id`                VARCHAR(32) COLLATE utf8mb4_unicode_ci  NOT NULL,
    `name`              VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动名称',
    `registration_time` DATETIME COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '报名时间',
    `start_time`        DATETIME COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '举办开始时间',
    `end_time`          DATETIME COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '举办结束时间',
    `type`              INT(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '活动类型(线上活动0;线下活动1)',
    `status`            INT(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '活动状态(0未开始1进行中2已结束)',
    `able_status`       INT(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '0下架,1上架',
    `browse_quantity`   INT(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '累计访问次数',
    `attendance`        INT(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '参与人数',
    `introduction`      TEXT COLLATE utf8mb4_unicode_ci COMMENT '简介',

    `dg_title`          VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '讨论组(discussion_group)名称',
    `dg_introduction`   TEXT COLLATE utf8mb4_unicode_ci COMMENT '讨论组简介',
    `dg_explain`        TEXT COLLATE utf8mb4_unicode_ci COMMENT '讨论组说明',
    `audit`             INT(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '是否需要审核(0否;1是)',

    `create_user`       VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
    `create_time`       DATETIME                                DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user`       VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
    `update_time`       DATETIME                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    PRIMARY KEY (`id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='活动表';


DROP
TABLES IF EXISTS `t_activity_detail`;

CREATE TABLE `t_activity_detail`
(
    `id`           VARCHAR(32) COLLATE utf8mb4_unicode_ci NOT NULL,
    `activity_id`  VARCHAR(32) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '活动表id',
    `url`          VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片，视频，音频 url',
    `content`      TEXT COLLATE utf8mb4_unicode_ci COMMENT '文本内容',
    `title`        VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片，视频，音频 标题',
    `introduction` TEXT COLLATE utf8mb4_unicode_ci COMMENT '图片，视频，音频 简介',
    `upload_time`  DATETIME                                DEFAULT NULL COMMENT '图片，视频，音频 上传时间',
    `vf_picture`   VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '音频背景图片',
    `sort`         INT(11) DEFAULT NULL COMMENT '内容排序序号',
    `type`         INT(11) DEFAULT NULL COMMENT '内容类型',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_activity_id` (`activity_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='活动详情表';


DROP
TABLES IF EXISTS `t_activity_user`;

CREATE TABLE `t_activity_user`
(
    `id`                VARCHAR(32) COLLATE utf8mb4_unicode_ci NOT NULL,
    `activity_id`       VARCHAR(32) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '活动表id',
    `user_id`           VARCHAR(32) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '学员id',
    `registration_time` DATETIME COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '报名时间',
    `audit_status`      INT(11) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审核状态(0不通过;1通过)',
    `attend_status`     INT(11) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '出席状态 0否1是',
    `remark`            VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `create_user`       VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
    `create_time`       DATETIME                                DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user`       VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
    `update_time`       DATETIME                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                 `idx_activity_id` (`activity_id`) USING BTREE,
    KEY                 `idx_user_id` (`user_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='活动学员报名表';

SELECT table_name ,table_comment FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'l0x1000db'
                                                                  AND table_name IN ('t_activity','t_activity_detail','t_activity_user');