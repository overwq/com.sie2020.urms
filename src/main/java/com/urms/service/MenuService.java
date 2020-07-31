package com.urms.service;

import com.urms.entity.Menu;
import com.urms.entity.MenuQueryCondition;

import java.util.List;
import java.util.Map;

/**
 * @author 吴权鸿
 */
public interface MenuService {

    /**
     * 查询所有菜单信息
     * @param menuQueryCondition
     * @return
     */
    List<Menu> selectAll(MenuQueryCondition menuQueryCondition);

    /**
     * 根据菜单id查询菜单信息
     * @param menuId
     * @return
     */
    Menu selectByMenuId(Integer menuId);

    /**
     * 根据菜单名查询菜单信息
     * @param menuName
     * @return
     */
    Menu selectMenuByMenuName(String menuName);

    /**
     * 添加菜单数据
     * @param menu
     * @return
     */
    Integer insertMenu(Menu menu);

    /**
     * 根据id删除删除信息 逻辑删除
     * @param menuId
     * @return
     */
    Integer deleteMenuById(Integer menuId);

    /**
     * 根据菜单id修改菜单信息
     * @param menu
     * @return
     */
    Integer updateMenuById(Menu menu);

    Map<String,Object> getAddMenuInfo();

    List<Menu> getTreeMenu();

    Integer getCount(MenuQueryCondition condition);

}
