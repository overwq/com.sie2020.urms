package com.urms;

import com.alibaba.fastjson.JSON;
import com.urms.entity.Role;
import com.urms.entity.RoleQueryCondition;
import com.urms.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RoleTest {
    @Autowired
    RoleService service;

    @Test
    public void selectByRoleIdTest(){
        System.out.println(JSON.toJSONString(service.selectByRoleId(3)));
    }

    @Test
    public void selectByRoleNameTest(){
        System.out.println(JSON.toJSONString(service.selectRoleByRoleName("teriri")));
    }

    @Test
    public void selectAllTest(){
        RoleQueryCondition condition = new RoleQueryCondition();
        condition.setPageNum(2);
        condition.setPageSize(2);
        System.out.println(JSON.toJSONString(service.selectAll(condition)));
        condition.setRoleName("t");
        condition.setPageNum(1);
        condition.setPageSize(20);
        System.out.println(JSON.toJSONString(service.selectAll(condition)));
    }

    @Test
    @Transactional
    public void insertRoleTest(){
        Role role = new Role();
        role.setRoleName("green");
        System.out.println(service.insertRole(role));
    }

    @Test
    @Transactional
    public void deleteRoleById(){
        System.out.println(service.deleteRoleById(3));
    }

    @Test
    @Transactional
    public void updateRoleById(){
        Role role = new Role();
        role.setRoleId(3);
        role.setRoleName("mery");
        System.out.println(service.updateRoleById(role));
    }

}
