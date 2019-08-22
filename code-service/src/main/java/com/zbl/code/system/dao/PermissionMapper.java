package com.zbl.code.system.dao;

import com.zbl.code.common.base.BaseMapper;
import com.zbl.code.system.pojo.po.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Author: Created by zbl on 2019-08-22
 * <p>
 * 操作权限
 */
@Mapper
@Component
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> listByGroup(@Param("roleId") Long roleId);

    List<Permission> list(@Param("module") String module, @Param("submodule") String submodule);

    List<String> listOperate(@Param("module") String module, @Param("submodule") String submodule);

    List<Permission> listShow();
}