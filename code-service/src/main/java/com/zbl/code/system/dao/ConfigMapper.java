package com.zbl.code.system.dao;

import com.zbl.code.common.base.BaseMapper;
import com.zbl.code.system.pojo.po.Config;
import com.zbl.code.system.pojo.vo.ConfigVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by hook on 2019-04-05
 * <p>
 * 系统配置
 */
@Mapper
@Component
public interface ConfigMapper extends BaseMapper<Config> {

    List<ConfigVO> list();
}