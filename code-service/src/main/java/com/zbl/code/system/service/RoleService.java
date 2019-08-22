package com.zbl.code.system.service;


import com.zbl.code.common.pojo.SimpleVO;
import com.zbl.code.system.pojo.po.Role;
import com.zbl.code.system.pojo.vo.RoleVO;

import java.util.List;

/**
 * @Author: Created by hook on 2019-04-04
 * <p>
 * 管理员角色 服务类
 */
public interface RoleService {
    
    void save(Long createId, Long fatherId, String name, String remark);

    void update(Long createId, Long id, String name, String remark);

    List<RoleVO> list(Long createId);

    Boolean owner(Long createId, Long roleId);

    List<Role> listSons(Long fatherId);

    List<SimpleVO> listSimple(Long createId);
}