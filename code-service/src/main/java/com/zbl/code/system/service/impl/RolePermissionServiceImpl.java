package com.zbl.code.system.service.impl;

import com.zbl.code.common.base.BaseException;
import com.zbl.code.common.base.BaseService;
import com.zbl.code.common.enums.DisplayStatusEnum;
import com.zbl.code.common.enums.ErrorCodeEnum;
import com.zbl.code.system.dao.RolePermissionMapper;
import com.zbl.code.system.pojo.po.Permission;
import com.zbl.code.system.pojo.po.Role;
import com.zbl.code.system.pojo.po.RolePermission;
import com.zbl.code.system.pojo.vo.ModuleVO;
import com.zbl.code.system.pojo.vo.OperateVO;
import com.zbl.code.system.pojo.vo.SubmoduleVO;
import com.zbl.code.system.service.PermissionService;
import com.zbl.code.system.service.RolePermissionService;
import com.zbl.code.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Created by zbl on 2019-08-22
 * <p>
 * 操作权限 服务实现类
 */
@Slf4j
@Service
public class RolePermissionServiceImpl extends BaseService implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    @Override
    public void check(Long roleId, String ctrl, String method) throws BaseException {
        Permission permission = permissionService.getByCtrlAndMethod(ctrl, method);
        if (permission == null) {
            throw new BaseException(ErrorCodeEnum.GL_NO_PERMISSION);
        }

        if (roleId == 0 || DisplayStatusEnum.HIDE.getCode().equals(permission.getDisplayStatus())) {
            return;
        }

        if (rolePermissionMapper.countByRoleAndCtrl(roleId, ctrl, method) > 0) {
            return;
        }

        throw new BaseException(ErrorCodeEnum.GL_NO_PERMISSION);
    }

    @Override
    public List<String> listOperate(Long roleId, String ctrl, String method) {
        Permission permission = permissionService.getByCtrlAndMethod(ctrl, method);
        if (roleId == 0L) {
            return permissionService.listOperate(permission.getModule(), permission.getSubmodule());
        }
        return rolePermissionMapper.listOperate(roleId, permission.getModule(), permission.getSubmodule());
    }

    @Override
    public List<ModuleVO> manage(Long createId, Long createRoleId, Long roleId) {
        List<ModuleVO> modules = new ArrayList<>();
        List<SubmoduleVO> submodules = null;
        List<OperateVO> operates = null;
        ModuleVO module;
        SubmoduleVO submodule;
        OperateVO operate;

        // 如果不是自己的子组
        if (!roleService.owner(createId, roleId)) {
            throw new BaseException(ErrorCodeEnum.GL_NO_PERMISSION);
        }

        // 先查出自己的权限和子组的权限，然后遍历
        List<Permission> selfPermissions = permissionService.listByGroup(createRoleId);
        List<Permission> subPermissions = permissionService.listByGroup(roleId);
        for (Permission permission : selfPermissions) {
            if (hasNotAddModule(modules, permission.getModule())) {
                // 一级菜单
                submodules = new ArrayList<>();
                module = new ModuleVO(permission.getModule(), submodules);
                modules.add(module);
            }

            assert submodules != null;
            if (hasNotAddSubModule(submodules, permission.getSubmodule())) {
                // 二级菜单
                operates = new ArrayList<>();
                submodule = new SubmoduleVO();
                submodule.setCtrlName(permission.getCtrlName());
                submodule.setName(permission.getSubmodule());
                submodule.setOperate(operates);
                submodules.add(submodule);
            }

            // 操作
            operate = new OperateVO();
            operate.setId(permission.getId());
            operate.setName(permission.getOperate());
            operate.setStatus(subPermissions.contains(permission) ? 1 : 0);
            assert operates != null;
            operates.add(operate);
        }

        return modules;
    }

    @Override
    public void updatePermission(Long createId, Long roleId, List<Long> permissionIds) {
        // 如果不是自己创建的角色
        if (!roleService.owner(createId, roleId)) {
            throw new BaseException(ErrorCodeEnum.GL_NO_PERMISSION);
        }

        // 先全部删除，然后添加
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermissionMapper.delete(rolePermission);
        for (Long id : permissionIds) {
            rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(id);
            rolePermissionMapper.insertSelective(rolePermission);
        }

        // 处理子角色
        List<Role> roles = roleService.listSons(roleId);
        for (Role role : roles) {
            updateSunRolePermission(role.getId(), permissionIds);
        }
    }

    @Override
    public List<ModuleVO> listModules(Long roleId) {
        List<ModuleVO> modules = new ArrayList<>();
        List<SubmoduleVO> submodules = null;
        ModuleVO module;
        SubmoduleVO submodule;

        List<Permission> selfPermissions = permissionService.listByGroup(roleId);
        for (Permission permission : selfPermissions) {
            if (hasNotAddModule(modules, permission.getModule())) {
                // 一级菜单
                submodules = new ArrayList<>();
                module = new ModuleVO(permission.getModule(), submodules);
                modules.add(module);
            }
            assert submodules != null;
            if (hasNotAddSubModule(submodules, permission.getSubmodule())) {
                // 二级菜单
                submodule = new SubmoduleVO();
                submodule.setCtrlName(permission.getCtrlName());
                submodule.setName(permission.getSubmodule());
                submodules.add(submodule);
            }
        }

        return modules;
    }

    private boolean hasNotAddModule(List<ModuleVO> modules, String name) {
        for (ModuleVO module : modules) {
            if (module.getModule().equals(name)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasNotAddSubModule(List<SubmoduleVO> modules, String name) {
        for (SubmoduleVO module : modules) {
            if (module.getName().equals(name)) {
                return false;
            }
        }

        return true;
    }

    private void updateSunRolePermission(Long roleId, List<Long> permissionIds) {
        List<Role> roles = roleService.listSons(roleId);
        for (Role role : roles) {
            updateSunRolePermission(role.getId(), permissionIds);
        }
        RolePermission search = new RolePermission();
        search.setRoleId(roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.select(search);
        for (RolePermission rolePermission : rolePermissions) {
            // 如果不包含
            if (!permissionIds.contains(rolePermission.getPermissionId())) {
                rolePermissionMapper.deleteByPrimaryKey(rolePermission.getId());
            }
        }
    }
}