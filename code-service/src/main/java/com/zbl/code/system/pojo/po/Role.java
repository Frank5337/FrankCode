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
@ApiModel("管理员角色")
@Table(name = "sys_role")
public class Role extends BaseEntity {
    @ApiModelProperty("创建者id")
    private Long createId;
    @ApiModelProperty("父id，如果是超管创建的则没有父id")
    private Long fatherId;
    @ApiModelProperty("角色类型(1内置角色（无法编辑）2普通)")
    private Integer type;
    @ApiModelProperty("角色名称(角色名称不可重复)")
    private String name;
    @ApiModelProperty("备注")
    private String remark;
}