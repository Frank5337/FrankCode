package com.zbl.code.common.pojo;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 分页数据
 * @Version: $
 */
@Data
@ApiModel("分页数据")
public class PageData<T> {
    @ApiModelProperty("第几页")
    private Integer pageNum;
    @ApiModelProperty("每页条条数")
    private Integer pageSize;
    @ApiModelProperty("总共条数")
    private Integer total;
    @ApiModelProperty("当页数据")
    private List<T> pageList;

    public PageData() {

    }

    public PageData(Page<T> page) {
        this.total = Long.valueOf(page.getTotal()).intValue();
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.pageList = page.getResult();
    }
}
