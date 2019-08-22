package com.zbl.code.system.dao;

import com.zbl.code.common.base.BaseMapper;
import com.zbl.code.common.pojo.SimpleVO;
import com.zbl.code.system.pojo.po.Role;
import com.zbl.code.system.pojo.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Author: Created by hook on 2019-04-05
 * <p>
 * 管理员角色
 */
@Mapper
@Component
public interface RoleMapper extends BaseMapper<Role> {

    List<RoleVO> list(@Param("createId") Long createId);

    List<SimpleVO> listSimple(@Param("createId") Long createId);
}