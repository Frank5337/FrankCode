package com.zbl.code.system.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 11:10 2019/8/22
 * @Description:
 */
@Data
@ApiModel("子模块")
public class SubmoduleVO {
    @ApiModelProperty("子模块名字")
    private String name;
    @ApiModelProperty("控制器名字")
    private String ctrlName;
    @ApiModelProperty("操作列表")
    List<OperateVO> operate;
}
