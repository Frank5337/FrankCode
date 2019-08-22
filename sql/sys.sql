DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL,
  `module` varchar(32) NOT NULL COMMENT '模块名称',
  `submodule` varchar(32) NOT NULL COMMENT '子模块名称',
  `operate` varchar(32) NOT NULL COMMENT '操作名称',
  `ctrl_name` varchar(64) NOT NULL COMMENT '控制器名字',
  `ctrl_method` varchar(32) NOT NULL COMMENT '控制器类方法名字',
  `module_sort` int(3) NOT NULL COMMENT '模块排序',
  `submodule_sort` int(3) NOT NULL COMMENT '子模块排序',
  `display_status` smallint(1) NOT NULL DEFAULT '1' COMMENT '显示状态(1显示0隐藏)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_permission_id_uindex` (`id`) USING BTREE,
  KEY `sys_permission_ctrl_method` (`ctrl_method`) USING BTREE,
  KEY `sys_permission_ctrl_name` (`ctrl_name`) USING BTREE,
  KEY `sys_permission_module` (`module`) USING BTREE,
  KEY `sys_permission_submodule` (`submodule`) USING BTREE,
  KEY `sys_permission_module_sort` (`module_sort`) USING BTREE,
  KEY `sys_permission_submodule_sort` (`submodule_sort`) USING BTREE,
  KEY `sys_permission_display_status` (`display_status`) USING BTREE,
  KEY `sys_permission_create_time` (`create_time`) USING BTREE,
  KEY `sys_permission_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='操作权限';


DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log` (
  `id` bigint(20) NOT NULL,
  `operator_id` bigint(20) DEFAULT NULL COMMENT '操作人id',
  `operate_ip` varchar(40) NOT NULL COMMENT '操作ip',
  `permission_id` bigint(20) NOT NULL COMMENT '对应操作权限id',
  `req_url` varchar(512) NOT NULL COMMENT '提交地址',
  `req_params` varchar(4096) NOT NULL COMMENT '提交参数',
  `resp_code` int(11) NOT NULL COMMENT '操作结果码',
  `resp_message` varchar(128) NOT NULL COMMENT '操作结果消息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_operate_log_id_uindex` (`id`) USING BTREE,
  KEY `sys_operate_log_operator_id` (`operator_id`) USING BTREE,
  KEY `sys_operate_log_permission_id` (`permission_id`) USING BTREE,
  KEY `sys_operate_log_resp_code` (`resp_code`) USING BTREE,
  KEY `sys_operate_log_update_time` (`update_time`) USING BTREE,
  KEY `sys_operate_log_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='操作日志'


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `father_id` bigint(20) DEFAULT NULL COMMENT '父id，如果是超管创建的则为0',
  `type` smallint(1) NOT NULL DEFAULT '2' COMMENT '角色类型(1内置角色（无法编辑）2普通)',
  `name` varchar(32) NOT NULL COMMENT '角色名称(角色名称不可重复)',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_role_id_uindex` (`id`) USING BTREE,
  KEY `sys_role_crate_time` (`create_time`) USING BTREE,
  KEY `sys_role_name` (`name`) USING BTREE,
  KEY `sys_role_update_time` (`update_time`) USING BTREE,
  KEY `sys_role_type` (`type`) USING BTREE,
  KEY `sys_role_father_id` (`father_id`) USING BTREE,
  KEY `sys_role_create_id` (`create_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='管理员角色'

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_role_permission_id_uindex` (`id`) USING BTREE,
  KEY `sys_role_permission_permission_id` (`permission_id`) USING BTREE,
  KEY `sys_role_permission_role_id` (`role_id`) USING BTREE,
  KEY `sys_role_permission_create_time` (`create_time`) USING BTREE,
  KEY `sys_role_permission_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色权限'

DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建者id，上级管理员，如果是内置角色的管理员则为空',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `able_status` smallint(1) NOT NULL DEFAULT '1' COMMENT '启用状态(1已启用 0未启用)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_admin_id_uindex` (`id`) USING BTREE,
  KEY `sys_admin_able_status_index` (`able_status`) USING BTREE,
  KEY `sys_admin_create_time_index` (`create_time`) USING BTREE,
  KEY `sys_admin_name_index` (`name`) USING BTREE,
  KEY `sys_admin_phone_index` (`phone`) USING BTREE,
  KEY `sys_admin_role_id_index` (`role_id`) USING BTREE,
  KEY `sys_admin_update_time_index` (`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='管理员'





