package com.zbl.code.system.service;

import com.zbl.code.system.pojo.po.Permission;

import java.util.List;

/**
 * @Author: Created by hook on 2019-04-05
 * <p>
 * 操作权限 服务类
 */
public interface PermissionService {

    Permission getByCtrlAndMethod(String ctrl, String method);

    void save(
            String module, String submodule, String operate, String ctrlName, String ctrlMethod, Integer moduleSort,
            Integer submoduleSort, Integer displayStatus);

    void update(
            Long id, String module, String submodule, String operate, String ctrlName, String ctrlMethod,
            Integer moduleSort, Integer submoduleSort, String remark, Integer displayStatus);

    List<Permission> list(String module, String submodule);

    List<String> listOperate(String module, String submodule);

    List<Permission> listByGroup(Long roleId);
}