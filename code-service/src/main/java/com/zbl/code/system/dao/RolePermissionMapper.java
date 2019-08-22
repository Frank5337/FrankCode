package com.zbl.code.system.dao;

import com.zbl.code.common.base.BaseMapper;
import com.zbl.code.system.pojo.po.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 11:29 2019/8/22
 * @Description: 角色权限
 * @Version: $
 */
@Mapper
@Component
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    Integer countByRoleAndCtrl(
            @Param("roleId") Long roleId, @Param("ctrl") String ctrl, @Param("method") String method);

    List<String> listOperate(
            @Param("roleId") Long roleId, @Param("module") String module, @Param("submodule") String submodule);
}
