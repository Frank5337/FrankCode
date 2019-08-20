package com.zbl.code.common.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: zbl
 * @Date: Created in 14:17 2019/8/20
 * @Description: 基础 Mapper
 * @Version: $
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
