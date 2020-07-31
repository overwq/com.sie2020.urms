package com.urms.service.impl;

import com.alibaba.fastjson.JSON;
import com.urms.entity.Menu;
import com.urms.entity.Role;
import com.urms.entity.User;
import com.urms.entity.UserQueryCondition;
import com.urms.mapper.UserMapper;
import com.urms.service.UserService;
import com.urms.tools.InitTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
/**
 * @author 吴权鸿
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public Boolean userLogin(User user) {
        User loginUser = mapper.userLogin(user);
        if(loginUser != null && loginUser.getUserStatus() == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<User> selectAll(UserQueryCondition userQueryCondition) {
        InitTool.InitCondition(userQueryCondition);
        if ("2".equals(userQueryCondition.getStatus()) ){
//            System.out.println("111111111111111111111");
            userQueryCondition.setStatus("");
            userQueryCondition.setHidden(1);
        }
        return mapper.selectAll(userQueryCondition);
    }

    @Override
    public User selectByUserId(Integer userId) {
        return mapper.selectByUserId(userId);
    }

    @Override
    public User selectUserByLoginName(String userLoginName) {
        return mapper.selectUserByLoginName(userLoginName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertUser(User user) {
        setCreateTime(user);
        return mapper.insertUser(user);
    }

    @Override
    public Integer updateUserById(User user) {
        setUpdateTime(user);
        return mapper.updateUserById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteUserById(Integer userId) {

        return mapper.deleteUserById(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateUserStatusById(User user) {
        setUpdateTime(user);
        return mapper.updateUserStatusById(user);
    }

    @Override
    public List<Menu> getUserMenu(String userLoginName) {
        return mapper.getUserMenu(userLoginName);
    }

    @Override
    public List<Role> getUserRole(String userLoginName) {
        return mapper.getUserRole(userLoginName);
    }


    @Override
    public String getRoleNameByUserLoginName(String userLoginName) {
        return mapper.getRoleNameByUserLoginName(userLoginName);
    }

    @Override
    public Integer getCount(UserQueryCondition userQueryCondition) {

        return mapper.getCount(userQueryCondition);
    }

    public void setCreateTime(User user){
        user.setUserCreateTime(Timestamp.valueOf(LocalDateTime.now()));
    }

    public void setUpdateTime(User user) {
        user.setUserUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
    }
}
