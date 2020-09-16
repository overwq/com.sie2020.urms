package com.urms.mapper;

import com.urms.entity.Menu;
import com.urms.entity.MenuQueryCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 吴权鸿
 */
@Repository
public interface MenuMapper {

    /**
     * 根据菜单id查找菜单信息
     * @param menuId
     * @return
     */
    Menu selectByMenuId(Integer menuId);

    /**
     * 根据菜单名查找菜单信息
     * @param menuName
     * @return
     */
    Menu selectByMenuName(String menuName);

    /**
     * 条件查询获取菜单信息
     * @param menuQueryCondition
     * @return
     */
    List<Menu> selectMenuByCondition(MenuQueryCondition menuQueryCondition);

    /**
     * 添加菜单信息
     * @param menu
     * @return
     */
    Integer insertMenu(Menu menu);

    /**
     * 根据菜单id删除菜单信息 逻辑删除
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

    List<Object> getOptionParentMenuName();

    List<Object> getMenuType();

    List<Menu> getTreeMenu();

    List<Menu> getTreeMenuByRoleId(int roleId);

    Integer getCount(MenuQueryCondition condition);

}
