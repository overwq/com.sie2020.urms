package com.urms;

import com.alibaba.fastjson.JSON;
import com.urms.entity.UserRole;
import com.urms.entity.UserRoleQueryCondition;
import com.urms.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserRoleTest {
    @Autowired
    UserRoleService service;
    @Test
    public void selectUserRoleByConditionTest(){
        UserRoleQueryCondition condition = new UserRoleQueryCondition();
        condition.setPageNum(1);
        condition.setPageSize(20);
        condition.setUserLoginName("r");
        for (UserRole ur : service.selectAll(condition)){
            System.out.println(JSON.toJSONString(ur));
        }
    }

    @Test
    public void selectByUserRoleIdTest(){
        System.out.println(JSON.toJSONString(service.selectByUserRoleId(1)));
    }

    @Test
    public void selectUserRoleByUserRoleNameTest(){
        System.out.println(JSON.toJSONString(service.selectUserRoleByUserLoginName("teriri")));
    }

    @Test
    @Transactional
    public void insertUserRoleTest(){
        UserRole ur = new UserRole();
        ur.setUserLoginName("fff");
        ur.setUserId(8);
        ur.setRoleId(8);
        System.out.println(JSON.toJSONString(service.insertUserRole(ur)));
    }

    @Test
    @Transactional
    public void deleteUserRoleByIdTest(){
        System.out.println(service.deleteUserRoleById(4));
    }

    @Test
    @Transactional
    public void updateUserRoleByIdTest(){
        UserRole ur = new UserRole();
        ur.setUserLoginName("fff");
        ur.setUserId(8);
        ur.setRoleId(8);
        ur.setUserRoleId(4);
        System.out.println(JSON.toJSONString(service.updateUserRoleById(ur)));
    }

}
