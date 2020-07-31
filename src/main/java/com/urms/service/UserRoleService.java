package com.urms.service;

import com.urms.entity.UserRole;
import com.urms.entity.UserRoleQueryCondition;

import java.util.List;

/**
 * @author 吴权鸿
 */
public interface UserRoleService {

    /**
     * 条件查询所有用户所属角色信息
     * @param userRoleQueryCondition
     * @return
     */
    List<UserRole> selectAll(UserRoleQueryCondition userRoleQueryCondition);

    /**
     * 根据用户所属角色ID查询用户所属角色信息
     * @param userRoleId
     * @return
     */
    UserRole selectByUserRoleId(Integer userRoleId);

    /**
     * 通过用户所属角色名查询用户所属角色信息
     * @param userRoleName
     * @return
     */
    List<UserRole> selectUserRoleByUserLoginName(String userRoleName);

    /**
     * 添加用户所属角色数据
     * @param userRole
     * @return
     */
    Integer insertUserRole(UserRole userRole);

    /**
     * 根据id删除用户所属角色信息 逻辑删除
     * @param userRoleId
     * @return
     */
    Integer deleteUserRoleById(Integer userRoleId);

    /**
     * 根据id修改用户所属角色信息
     * @param userRole
     * @return
     */
    Integer updateUserRoleById(UserRole userRole);

}
