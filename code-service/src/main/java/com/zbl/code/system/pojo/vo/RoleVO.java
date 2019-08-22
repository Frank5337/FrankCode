package com.zbl.code.system.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Created by hook on 2018/7/28.
 */
@Data
@ApiModel("角色视图")
public class RoleVO {
    @ApiModelProperty("角色id")
    private Long id;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("角色类型(1内置角色（无法编辑）2普通)")
    private Integer type;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
