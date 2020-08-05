package com.urms.service.impl;

import com.urms.entity.Menu;
import com.urms.entity.Role;
import com.urms.entity.RoleQueryCondition;
import com.urms.mapper.RoleMapper;
import com.urms.mapper.RoleMenuMapper;
import com.urms.service.RoleService;
import com.urms.tools.InitTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper mapper;
    @Override
    public List<Role> selectAll(RoleQueryCondition roleQueryCondition) {
        InitTool.InitCondition(roleQueryCondition);
        return mapper.selectRoleByCondition(roleQueryCondition);
    }

    @Override
    public Role selectByRoleId(Integer roleId) {
        return mapper.selectByRoleId(roleId);
    }

    @Override
    public Role selectRoleByRoleName(String roleName) {
        return mapper.selectByRoleName(roleName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertRole(Role role) {
        return mapper.insertRole(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteRoleById(Integer roleId) {

        return mapper.deleteRoleById(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateRoleById(Role role) {

        return mapper.updateRoleById(role);
    }

    @Override
    public Integer getCount(RoleQueryCondition condition) {
        return mapper.getCount(condition);
    }

    @Override
    public List<Menu> getRoleMenu(Integer roleId) {
        return mapper.getRoleMenu(roleId);
    }


}
