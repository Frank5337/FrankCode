package com.zbl.code.system.service.impl;

import com.zbl.code.common.base.BaseException;
import com.zbl.code.common.base.BaseService;
import com.zbl.code.common.enums.ErrorCodeEnum;
import com.zbl.code.common.pojo.SimpleVO;
import com.zbl.code.system.dao.RoleMapper;
import com.zbl.code.system.enums.RoleTypeEnum;
import com.zbl.code.system.pojo.po.Role;
import com.zbl.code.system.pojo.vo.RoleVO;
import com.zbl.code.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Created by hook on 2019-04-04
 * <p>
 * 管理员角色 服务实现类
 */
@Slf4j
@Service
public class RoleServiceImpl extends BaseService implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public void save(Long createId, Long fatherId, String name, String remark) {
        if (getByName(createId, name) != null) {
            throw new BaseException(ErrorCodeEnum.SYS_ROLE_EXIST);
        }

        Role role = new Role();
        role.setCreateId(createId);
        role.setFatherId(fatherId);
        role.setName(name);
        role.setRemark(remark);
        roleMapper.insertSelective(role);
    }

    @Override
    public void update(Long createId, Long id, String name, String remark) {
        Role role = getByName(createId, name);
        if (role != null && !role.getId().equals(id)) {
            throw new BaseException(ErrorCodeEnum.SYS_ROLE_EXIST);
        }

        role = roleMapper.selectByPrimaryKey(id);
        if (role == null || !role.getCreateId().equals(createId) || RoleTypeEnum.INNER.is(role.getType())) {
            throw new BaseException(ErrorCodeEnum.GL_BAD_PARAM);
        }

        role.setName(name);
        role.setRemark(remark);
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public List<RoleVO> list(Long createId) {
        return roleMapper.list(createId);
    }

    @Override
    public List<SimpleVO> listSimple(Long createId) {
        return roleMapper.listSimple(createId);
    }

    @Override
    public Boolean owner(Long createId, Long roleId) {
        if (createId == null || roleId == null) {
            return false;
        }
        Role role = roleMapper.selectByPrimaryKey(roleId);
        return role != null && role.getCreateId().equals(createId);
    }

    @Override
    public List<Role> listSons(Long fatherId) {
        Role search = new Role();
        search.setFatherId(fatherId);
        return roleMapper.select(search);
    }

    private Role getByName(Long createId, String name) {
        Role search = new Role();
        search.setCreateId(createId);
        search.setName(name);
        return roleMapper.selectOne(search);
    }
}