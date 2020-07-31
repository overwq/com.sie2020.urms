package com.urms.service;

import com.urms.entity.Menu;
import com.urms.entity.Role;
import com.urms.entity.User;
import com.urms.entity.UserQueryCondition;

import java.util.List;

/**
 * @author 吴权鸿
 */
public interface UserService {

    /**
     * 用户登陆
     * @param user
     * @return
     */
    Boolean userLogin(User user);

    /**
     * 分页查询所有用户信息 如果查询条件为空,则返回所有数据
     * @param userQueryCondition
     * @return
     */
    List<User> selectAll(UserQueryCondition userQueryCondition);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User selectByUserId(Integer userId);

    /**
     * 根据用户名查询用户信息
     * @param userLoginName
     * @return
     */
    User selectUserByLoginName(String userLoginName);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 根据id修改用户信息
     * @param user
     * @return
     */
    Integer updateUserById(User user);

    /**
     * 根据userId删除用户信息
     * @param userId
     * @return
     */
    Integer deleteUserById(Integer userId);

    /**
     * 根据用户id修改用户状态
     * @param user
     * @return
     */
    Integer updateUserStatusById(User user);

    /**
     * 根据用户登录名获取用户对应的菜单信息
     * @param userLoginName 用户登陆名
     * @return 所有对应菜单的集合
     */
    List<Menu> getUserMenu(String userLoginName);

    /**
     * 根据用户登陆名获取用户所属角色信息
     * @param userLoginName 用户登陆名
     * @return 用户所属角色信息集合
     */
    List<Role> getUserRole(String userLoginName);

    /**
     * 根据用户登录名返回一个角色名 没用
     * @param userLoginName
     * @return
     */
    String getRoleNameByUserLoginName(String userLoginName);

    /**
     * 分页查询计数
     * @param queryCondition
     * @return 总的数据量
     */
    Integer getCount(UserQueryCondition queryCondition);
}
