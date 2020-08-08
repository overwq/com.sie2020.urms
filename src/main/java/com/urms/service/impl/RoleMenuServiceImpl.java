package com.urms.service.impl;

import com.urms.entity.RoleMenu;
import com.urms.mapper.RoleMenuMapper;
import com.urms.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    RoleMenuMapper mapper;
    @Override
    public RoleMenu selectByRoleMenuId(Integer roleMenuId) {
        return mapper.selectByRoleMenuId(roleMenuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertRoleMenu(RoleMenu roleMenu) {
        return mapper.insertRoleMenu(roleMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteRoleMenuById(Integer roleMenuId) {
        return mapper.deleteRoleMenuById(roleMenuId);
    }

    @Override
    public List<RoleMenu> getRoleMenusByRoleId(Integer roleId) {
        return mapper.getRoleMenusByRoleId(roleId);
    }
}
