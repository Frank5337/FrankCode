package com.zbl.code.system.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 11:09 2019/8/22
 * @Description:
 */
@Data
@ApiModel("主模块")
public class ModuleVO {
    @ApiModelProperty("主模块名字")
    private String module;
    @ApiModelProperty("子模块列表")
    private List<SubmoduleVO> submodules;

    public ModuleVO(String module, List<SubmoduleVO> submodules) {
        this.module = module;
        this.submodules = submodules;
    }
}
