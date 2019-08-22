package com.zbl.code.system.dao;

import com.zbl.code.common.base.BaseMapper;
import com.zbl.code.system.pojo.po.OperateLog;
import com.zbl.code.system.pojo.vo.OperateLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * @Author: Created by zbl on 2019-08-22
 * <p>
 * 操作日志
 */
@Mapper
@Component
public interface OperateLogMapper extends BaseMapper<OperateLog> {

    List<OperateLogVO> list(
            @Param("operateName") String operateName, @Param("module") String module,
            @Param("submodule") String submodule, @Param("operate") String operate, @Param("start") Date start,
            @Param("end") Date end);
}