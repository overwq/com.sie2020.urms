package com.urms.service.impl;

import com.urms.entity.Menu;
import com.urms.entity.MenuQueryCondition;
import com.urms.mapper.MenuMapper;
import com.urms.service.MenuService;
import com.urms.tools.InitTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper mapper;
    @Override
    public List<Menu> selectAll(MenuQueryCondition menuQueryCondition) {
        InitTool.InitCondition(menuQueryCondition);
        return mapper.selectMenuByCondition(menuQueryCondition);
    }

    @Override
    public Menu selectByMenuId(Integer menuId) {
        return mapper.selectByMenuId(menuId);
    }

    @Override
    public Menu selectMenuByMenuName(String menuName) {
        return mapper.selectByMenuName(menuName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertMenu(Menu menu) {
        return mapper.insertMenu(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteMenuById(Integer menuId) {
        return mapper.deleteMenuById(menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateMenuById(Menu menu) {
        menu.setMenuParentId(mapper.selectByMenuName(menu.getMenuParentName()).getMenuId());
        return mapper.updateMenuById(menu);
    }

    @Override
    public Map<String,Object> getAddMenuInfo() {
        Map<String,Object> map = new HashMap<>(8);
        map.put("menuTypes",mapper.getMenuType());
        map.put("parentName",mapper.getOptionParentMenuName());
        return map;
    }

    @Override
    public List<Menu> getTreeMenu() {
        return mapper.getTreeMenu();
    }

    @Override
    public List<Menu> getTreeMenuByRoleId(int roleId) {
        return mapper.getTreeMenuByRoleId(roleId);
    }

    @Override
    public Integer getCount(MenuQueryCondition condition) {
        return mapper.getCount(condition);
    }
}
