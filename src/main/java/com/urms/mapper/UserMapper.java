package com.urms.mapper;

import com.urms.entity.Menu;
import com.urms.entity.Role;
import com.urms.entity.User;
import com.urms.entity.UserQueryCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 吴权鸿
 */
@Repository
public interface UserMapper {
    /**
     * 用户登陆
     * @param user
     * @return
     */
    User userLogin(User user);

    /**
     * 查询所有用户信息
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
     * 添加用户数据
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 根据id删除用户信息 逻辑删除
     * @param userId
     * @return
     */
    Integer deleteUserById(Integer userId);

    /**
     * 根据id修改用户信息
     * @param user
     * @return
     */
    Integer updateUserById(User user);

    /**
     * 根据用户id修改用户状态
     * @param user
     * @return
     */
    Integer updateUserStatusById(User user);

    List<Menu> getUserMenu(String uName);

    List<Role> getUserRole(String userLoginName);

    String getRoleNameByUserLoginName(String userLoginName);

    Integer getCount(UserQueryCondition userQueryCondition);
}
