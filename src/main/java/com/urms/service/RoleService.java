package com.urms.service;

import com.urms.entity.Menu;
import com.urms.entity.Role;
import com.urms.entity.RoleQueryCondition;

import java.util.List;

/**
 * @author 吴权鸿
 */
public interface RoleService {

    /**
     * 查询所有角色信息
     * @param roleQueryCondition
     * @return
     */
    List<Role> selectAll(RoleQueryCondition roleQueryCondition);

    /**
     * 根据角色id查询角色信息
     * @param roleId
     * @return
     */
    Role selectByRoleId(Integer roleId);

    /**
     * 根据角色名查询角色信息
     * @param roleName
     * @return
     */
    Role selectRoleByRoleName(String roleName);

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    Integer insertRole(Role role);

    /**
     * 根据id删除角色信息 逻辑删除
     * @param roleId
     * @return
     */
    Integer deleteRoleById(Integer roleId);

    /**
     * 根据id更新角色信息
     * @param role
     * @return
     */
    Integer updateRoleById(Role role);

    /**
     * 获取分页总数
     * @param condition
     * @return
     */
    Integer getCount(RoleQueryCondition condition);

    List<Menu> getRoleMenu(Integer roleId);
}
