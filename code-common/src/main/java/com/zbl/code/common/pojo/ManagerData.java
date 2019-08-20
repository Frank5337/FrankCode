package com.zbl.code.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description:
 * @Version: $
 */
@Data
@ApiModel("后台管理数据")
public class ManagerData<V> {
    @ApiModelProperty("额外数据")
    private V extra;
    @ApiModelProperty("操作列表")
    private List<String> operates;
}
