package com.zbl.code.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Created by hook on 2018/7/28.
 * <p>
 */
@Data
@ApiModel("简单视图")
public class SimpleVO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("名字")
    private String name;

}
