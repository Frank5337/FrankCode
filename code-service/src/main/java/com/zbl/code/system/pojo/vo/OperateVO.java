package com.zbl.code.system.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zbl
 * @Date: Created in 11:11 2019/8/22
 * @Description:
 */
@Data
@ApiModel("操作")
public class OperateVO {
    @ApiModelProperty("操作id")
    private Long id;
    @ApiModelProperty("操作名字")
    private String name;
    @ApiModelProperty("操作状态 0未分配 1已分配")
    private Integer status;
}
