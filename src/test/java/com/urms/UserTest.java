package com.urms;

import com.alibaba.fastjson.JSON;
import com.urms.entity.User;
import com.urms.entity.UserQueryCondition;
import com.urms.service.UserService;
import com.urms.service.impl.UserServiceImpl;
import com.urms.tools.Tool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
//@MapperScan("com.urms.mapper")
public class UserTest {
    @Autowired
    UserService service;
    UserServiceImpl userService = new UserServiceImpl();
    @Test
    public void userLoginTest(){
        User user = new User();
        user.setUserLoginName("red");
        user.setUserPassword("red");
        System.out.println(service.userLogin(user));
    }

    @Test
    @Transactional
    public void updateUserPasswordToMd5Test(){
        UserQueryCondition condition = new UserQueryCondition();
        List<User> users = service.selectAll(condition);
        System.out.println(JSON.toJSONString(users));
//        for (User user : users){
//            user.setUserPassword(Tool.md5(user.getUserPassword()));
//            service.updateUserById(user);
//        }
        System.out.println(service.selectAll(condition));
    }

    @Test
    public void selectAllTest(){
        UserQueryCondition userQueryCondition = new UserQueryCondition();
        userQueryCondition.setPageSize(3);
        userQueryCondition.setPageNum(2);
        System.out.println(JSON.toJSONString(service.selectAll(userQueryCondition)));
        userQueryCondition.setPageNum(1);
        System.out.println(JSON.toJSONString(service.selectAll(userQueryCondition)));
    }

    @Test
    public void selectByUserIdTest(){
        System.out.println(service.selectByUserId(1));
    }

    @Test
    public void selectUserByLoginNameTest(){
        System.out.println(service.selectUserByLoginName("teriri"));
    }

    @Test
    @Transactional
    public void insertUserTest(){
        User user = new User();
        user.setUserLoginName("bbb");
        user.setUserName("bbb");
        user.setUserPassword("bbb");
        user.setUserPhone(12345789101L);
        user.setUserEmail("1111111111");
        System.out.println(service.insertUser(user));
    }

    @Test
    @Transactional
    public void deleteUserByIdTest(){
        System.out.println(service.deleteUserById(1));
    }

    @Test
    @Transactional
    public void updateUserByIdTest(){
        User user = new User();
        user.setUserId(1);
        user.setUserHidden(1);
        user.setUserSex(2);
        System.out.println(service.updateUserStatusById(user));
    }

    @Test
    @Transactional
    public void updateUserStatusByIdTest(){
        User user = new User();
        user.setUserId(1);
        user.setUserHidden(1);
        System.out.println(service.updateUserStatusById(user));
    }

    @Test
    public void setCreateTimeTest(){
        User user = new User();
        userService.setCreateTime(user);
    }

    @Test
    public void getCountTest(){

        String condition = "{\"hidden\":1,\"loginName\":\"\",\"pageNum\":1,\"pageSize\":20,\"status\":\"2\",\"userName\":\"\"}";
        UserQueryCondition userQueryCondition = JSON.parseObject(condition,UserQueryCondition.class);
        System.out.println(JSON.toJSONString(condition));
        System.out.println(userService.getCount(userQueryCondition));
    }

}
