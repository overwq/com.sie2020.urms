package com.urms;

import com.alibaba.fastjson.JSON;
import com.urms.entity.Menu;
import com.urms.entity.MenuQueryCondition;
import com.urms.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MenuTest {
    @Autowired
    MenuService service;

    @Test
    public void selectByMenuIdTest(){
        System.out.println(JSON.toJSONString(service.selectByMenuId(1)));
    }

    @Test
    public void selectByMenuNameTest(){
        System.out.println(JSON.toJSONString(service.selectMenuByMenuName("teri")));
    }

    @Test
    public void selectMenuByConditionTest(){
        MenuQueryCondition condition = new MenuQueryCondition();
        condition.setPageSize(20);
        condition.setPageNum(1);
        condition.setMenuName("t");
        System.out.println(JSON.toJSONString(service.selectAll(condition)));
    }

    @Test
    @Transactional
    public void insertMenuTest(){
        Menu menu = new Menu();
        menu.setMenuType("6");
        menu.setMenuName("qwer");
        menu.setMenuUrl("6");
        System.out.println(JSON.toJSONString(service.insertMenu(menu)));
    }

    @Test
    @Transactional
    public void deleteMenuByIdTest(){
        System.out.println(JSON.toJSONString(service.deleteMenuById(1)));
    }

    @Test
    @Transactional
    public void updateMenuByIdTest(){
        Menu menu = new Menu();
        menu.setMenuId(1);
        menu.setMenuName("xxxxxxxxx");
        System.out.println(JSON.toJSONString(service.updateMenuById(menu)));
    }

}
