package com.zbl.code.common.base;

import com.zbl.code.common.compoment.SnowflakeId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zbl
 * @Date: Created in 15:34 2019/8/20
 * @Description: 基类
 * @Version: $
 */
@Data
public class BaseEntity implements Serializable {
    @Id
    @KeySql(genId = SnowflakeId.class)
    private Long id;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
