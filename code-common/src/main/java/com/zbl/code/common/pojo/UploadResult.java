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
@ApiModel("上传结果")
public class UploadResult {
    @ApiModelProperty("读取条数")
    private Integer uploadCount;
    @ApiModelProperty("成功导入条数")
    private Integer successCount;
    @ApiModelProperty("成功更新条数")
    private Integer updateCount;
    @ApiModelProperty("哪些失败")
    private List<UploadErrorMessage> uploadErrorMessages;
}
