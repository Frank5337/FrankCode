package com.zbl.code.common.pojo;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description:
 * @Version: $
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("额外分页数据")
public class ExtraPageData<T, V> extends PageData<T> {
    @ApiModelProperty("额外数据")
    private V extra;

    public ExtraPageData(Page<T> page, V extra) {
        super(page);
        this.extra = extra;
    }

    public ExtraPageData(PageData<T> data, V extra) {
        setTotal(data.getTotal());
        setPageNum(data.getPageNum());
        setPageSize(data.getPageSize());
        setPageList(data.getPageList());
        this.extra = extra;
    }
}
