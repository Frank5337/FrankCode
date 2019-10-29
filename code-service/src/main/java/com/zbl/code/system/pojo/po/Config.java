package com.zbl.code.system.pojo.po;

import com.zbl.code.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * <p>
 *
 * @author zhangbl
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统配置")
@Table(name = "sys_config")
public class Config extends BaseEntity {
    @ApiModelProperty("系统配置键名")
    private String sysKey;
    @ApiModelProperty("系统配置键值")
    private String sysValue;
    @ApiModelProperty("备注")
    private String remark;
}