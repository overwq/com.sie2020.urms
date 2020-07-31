package com.urms.mapper;

import com.urms.entity.Role;
import com.urms.entity.RoleQueryCondition;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper {

    /**
     * 根据id查找角色信息
     * @param roleId
     * @return
     */
    Role selectByRoleId(Integer roleId);

    /**
     * 根据角色名查询角色信息
     * @param roleName
     * @return
     */
    Role selectByRoleName(String roleName);

    /**
     * 条件查询角色信息
     * @param roleQueryCondition
     * @return
     */
    List<Role> selectRoleByCondition(RoleQueryCondition roleQueryCondition);

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
     * 根据角色id修改角色信息
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

}
