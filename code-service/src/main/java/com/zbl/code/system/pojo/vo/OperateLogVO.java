package com.zbl.code.system.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zbl
 * @Date: Created in 11:12 2019/8/22
 * @Description:
 */
@Data
@ApiModel("操作日志")
public class OperateLogVO {
    @ApiModelProperty("操作id")
    private Long id;
    @ApiModelProperty("操作人姓名")
    private String name;
    @ApiModelProperty("主模块名称")
    private String module;
    @ApiModelProperty("子模块名称")
    private String submodule;
    @ApiModelProperty("操作名称")
    private String operate;
    @ApiModelProperty("操作ip")
    private String operateIp;
    @ApiModelProperty("操作时间")
    private Date createTime;
    @ApiModelProperty("操作结果消息")
    private String respMessage;
}
