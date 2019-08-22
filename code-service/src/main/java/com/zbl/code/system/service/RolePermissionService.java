package com.zbl.code.system.service;

import com.zbl.code.common.base.BaseException;
import com.zbl.code.system.pojo.vo.ModuleVO;

import java.util.List;

/**
 * @Author: Created by zbl on 2019-08-22
 * <p>
 * 角色权限 服务类
 */
public interface RolePermissionService {

    void check(Long roleId, String ctrl, String method) throws BaseException;

    List<String> listOperate(Long roleId, String ctrl, String method);

    List<ModuleVO> manage(Long createId, Long createRoleId, Long roleId);

    void updatePermission(Long createId, Long roleId, List<Long> permissionIds);

    List<ModuleVO> listModules(Long roleId);
}