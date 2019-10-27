package com.zbl.code.system.pojo.vo;

import com.zbl.code.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * @author zhangbl
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统配置")
@Table(name = "sys_config")
public class ConfigVO extends BaseEntity {
    @ApiModelProperty("系统配置id")
    private Long id;
    @ApiModelProperty("系统配置键名")
    private String sysKey;
    @ApiModelProperty("系统配置键值")
    private String sysValue;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}