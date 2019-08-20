package com.zbl.code.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description:
 * @Version: $
 */
@Data
@ApiModel("上传错误消息")
public class UploadErrorMessage {
    @ApiModelProperty("哪条上传失败")
    private String failedIndex;
    @ApiModelProperty("错误消息")
    private String errorMessage;

    public UploadErrorMessage(String failedIndex, String errorMessage) {
        this.failedIndex = failedIndex;
        this.errorMessage = errorMessage;
    }
}
