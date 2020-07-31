package com.urms.service;

import com.urms.entity.RoleMenu;

import java.util.List;

public interface RoleMenuService {

    /**
     * 根据角色权限id查询角色权限信息
     * @param roleMenuId
     * @return
     */
    RoleMenu selectByRoleMenuId(Integer roleMenuId);

    /**
     * 添加角色权限数据
     * @param roleMenu
     * @return
     */
    Integer insertRoleMenu(RoleMenu roleMenu);

    /**
     * 根据id删除角色权限信息
     * @param roleMenuId
     * @return
     */
    Integer deleteRoleMenuById(Integer roleMenuId);

    /**
     * 根据roleId获取roleMenu集合
     * @param roleId
     * @return
     */
    List<RoleMenu> getRoleMenusByRoleId(Integer roleId);
}
