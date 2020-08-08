package com.urms;

import com.alibaba.fastjson.JSON;
import com.urms.entity.RoleMenu;
import com.urms.service.RoleMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RoleMenuTest {
    @Autowired
    RoleMenuService service;

    @Test
    public void selectByRoleMenuIdTest(){
        System.out.println(JSON.toJSONString(service.selectByRoleMenuId(1)));
    }

    @Test
    @Transactional
    public void insertRoleMenuTest(){
        RoleMenu rm = new RoleMenu();
        rm.setMenuId(10);
        rm.setRoleId(10);
        System.out.println(service.insertRoleMenu(rm));
    }

    @Test
    @Transactional
    public void deleteRoleMenuByIdTest(){
        System.out.println(service.deleteRoleMenuById(1));
    }





}
