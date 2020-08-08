package com.urms.service.impl;

import com.urms.entity.UserRole;
import com.urms.entity.UserRoleQueryCondition;
import com.urms.mapper.UserRoleMapper;
import com.urms.service.UserRoleService;
import com.urms.tools.InitTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper mapper;
    @Override
    public List<UserRole> selectAll(UserRoleQueryCondition userRoleQueryCondition) {
        InitTool.InitCondition(userRoleQueryCondition);
        return mapper.selectUserRoleByCondition(userRoleQueryCondition);
    }

    @Override
    public UserRole selectByUserRoleId(Integer userRoleId) {
        return mapper.selectByUserRoleId(userRoleId);
    }

    @Override
    public List<UserRole> selectUserRoleByUserLoginName(String userRoleName) {
        return mapper.selectUserRoleByUserLoginName(userRoleName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertUserRole(UserRole userRole) {
        return mapper.insertUserRole(userRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteUserRoleById(Integer userRoleId) {
        return mapper.deleteUserRoleById(userRoleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateUserRoleById(UserRole userRole) {
        return mapper.updateUserRoleById(userRole);
    }
}
