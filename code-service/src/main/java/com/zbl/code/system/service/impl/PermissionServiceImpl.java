package com.zbl.code.system.service.impl;

import com.zbl.code.common.base.BaseException;
import com.zbl.code.common.base.BaseService;
import com.zbl.code.common.enums.ErrorCodeEnum;
import com.zbl.code.system.dao.PermissionMapper;
import com.zbl.code.system.pojo.po.Permission;
import com.zbl.code.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Created by hook on 2019-04-05
 * <p>
 * 操作权限 服务实现类
 */
@Slf4j
@Service
public class PermissionServiceImpl extends BaseService implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public Permission getByCtrlAndMethod(String ctrl, String method) {
        Permission search = new Permission();
        search.setCtrlName(ctrl);
        search.setCtrlMethod(method);
        return permissionMapper.selectOne(search);
    }

    @Override
    public void save(
            String module, String submodule, String operate, String ctrlName, String ctrlMethod, Integer moduleSort,
            Integer submoduleSort, Integer displayStatus) {
        Permission permission = getByCtrlAndMethod(ctrlName, ctrlMethod);
        if (permission != null) {
            throw new BaseException(ErrorCodeEnum.SYS_PERMISSION_EXIST);
        }

        permission = getByModule(module, submodule, operate);
        if (permission != null) {
            throw new BaseException(ErrorCodeEnum.SYS_PERMISSION_EXIST);
        }

        permission = new Permission();
        permission.setModule(module);
        permission.setSubmodule(submodule);
        permission.setOperate(operate);
        permission.setCtrlName(ctrlName);
        permission.setCtrlMethod(ctrlMethod);
        permission.setModuleSort(moduleSort);
        permission.setSubmoduleSort(submoduleSort);
        permission.setDisplayStatus(displayStatus);
        permissionMapper.insertSelective(permission);
    }

    @Override
    public void update(
            Long id, String module, String submodule, String operate, String ctrlName, String ctrlMethod,
            Integer moduleSort, Integer submoduleSort, String remark, Integer displayStatus) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        permission.setModule(module);
        permission.setSubmodule(submodule);
        permission.setOperate(operate);
        permission.setCtrlName(ctrlName);
        permission.setCtrlMethod(ctrlMethod);
        permission.setModuleSort(moduleSort);
        permission.setSubmoduleSort(submoduleSort);
        permission.setDisplayStatus(displayStatus);
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public List<Permission> list(String module, String submodule) {
        return permissionMapper.list(module, submodule);
    }

    @Override
    public List<String> listOperate(String module, String submodule) {
        return permissionMapper.listOperate(module, submodule);
    }

    @Override
    public List<Permission> listByGroup(Long roleId) {
        if (roleId == 0) {
            return permissionMapper.listShow();
        }

        return permissionMapper.listByGroup(roleId);
    }

    private Permission getByModule(String module, String submodule, String operate) {
        Permission search = new Permission();
        search.setModule(module);
        search.setSubmodule(submodule);
        search.setOperate(operate);
        return permissionMapper.selectOne(search);
    }
}