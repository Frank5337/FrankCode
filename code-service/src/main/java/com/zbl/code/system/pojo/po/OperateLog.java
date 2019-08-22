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
@ApiModel("操作日志")
@Table(name = "sys_operate_log")
public class OperateLog extends BaseEntity {
    @ApiModelProperty("操作人id")
    private Long operatorId;
    @ApiModelProperty("操作ip")
    private String operateIp;
    @ApiModelProperty("对应操作权限id")
    private Long permissionId;
    @ApiModelProperty("提交地址")
    private String reqUrl;
    @ApiModelProperty("提交参数")
    private String reqParams;
    @ApiModelProperty("操作结果码")
    private Integer respCode;
    @ApiModelProperty("操作结果消息")
    private String respMessage;
}