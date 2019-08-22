package com.zbl.code.system.pojo.po;

import com.zbl.code.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * @Author: Created by hook on 2019-04-05
 * <p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("角色权限")
@Table(name = "sys_role_permission")
public class RolePermission extends BaseEntity {
    @ApiModelProperty("角色id")
	private Long roleId;
    @ApiModelProperty("权限id")
	private Long permissionId;
}