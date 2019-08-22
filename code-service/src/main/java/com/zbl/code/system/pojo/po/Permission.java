package com.zbl.code.system.pojo.po;

import com.zbl.code.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;


/**
 * @Author: Created by zbl on 2019-08-22
 * <p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("操作权限")
@Table(name = "sys_permission")
public class Permission extends BaseEntity {
    @ApiModelProperty("模块名称")
    private String module;
    @ApiModelProperty("子模块名称")
    private String submodule;
    @ApiModelProperty("操作名称")
    private String operate;
    @ApiModelProperty("控制器名字")
    private String ctrlName;
    @ApiModelProperty("控制器类方法名字")
    private String ctrlMethod;
    @ApiModelProperty("模块排序")
    private Integer moduleSort;
    @ApiModelProperty("子模块排序")
    private Integer submoduleSort;
    @ApiModelProperty("显示状态(1显示0隐藏)")
    private Integer displayStatus;
}