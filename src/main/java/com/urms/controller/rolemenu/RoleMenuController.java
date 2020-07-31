package com.urms.controller.rolemenu;

import com.alibaba.fastjson.JSON;
import com.urms.entity.JsonObject;
import com.urms.entity.RoleMenu;
import com.urms.service.RoleMenuService;
import com.urms.tools.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 吴权鸿
 */
@RestController
public class RoleMenuController {
    @Autowired
    RoleMenuService service;
    private final static Logger log = LoggerFactory.getLogger(RoleMenuController.class);
    /**
     * 根据角色权限id查询角色权限信息
     * @param roleMenuId
     * @return
     */
    @RequestMapping("/rolemenu/select/id")
    public String selectByRoleMenuId(@RequestBody Integer roleMenuId){
        if (Tool.isEmpty(roleMenuId)){
            String msg = "roleMenuId for query is null";
            log.info(Tool.msg(msg));
            return msg;
        }
        return JSON.toJSONString(service.selectByRoleMenuId(roleMenuId));
    }

    /**
     * 通过角色id获取角色菜单信息集合
     * @param roleId
     * @return
     */
    @RequestMapping("/rolemenu/select/roleid")
    public String getRoleMenusByRoleId(@RequestBody Integer roleId) {
        JsonObject object = new JsonObject();
        if (Tool.isEmpty(roleId)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        List<RoleMenu> result = service.getRoleMenusByRoleId(roleId);
        if (result.size() > 0){
            Tool.setSesscesMessage(object,"角色菜单获取成功");
        }else {
            Tool.setErrorMessage(object,"角色菜单获取失败，无相应记录");
        }
        object.setData(result);
        return JSON.toJSONString(object);
    }

    /**
     * 添加角色权限数据
     * @param roleMenus
     * @return
     */
    @RequestMapping("/rolemenu/insert")
    public String insertRoleMenu(@RequestBody RoleMenu[] roleMenus){
        JsonObject object = new JsonObject();
        int result = 0;
        if (Tool.isEmpty(roleMenus)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        for(RoleMenu roleMenu : roleMenus){
            result = service.insertRoleMenu(roleMenu);
        }
        if (result > 0){
            Tool.setSesscesMessage(object,"角色菜单已添加");
        }else {
            Tool.setErrorMessage(object,"角色菜单添加失败");
        }
        return JSON.toJSONString(object);
    }

    /**
     * 根据id删除角色权限信息
     * @param roleMenuIds
     * @return
     */
    @RequestMapping("/rolemenu/delete")
    public String deleteRoleMenuById(@RequestBody Integer[] roleMenuIds){
        JsonObject object = new JsonObject();
        Integer result = 0;
        if (Tool.isEmpty(roleMenuIds)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        for(int id : roleMenuIds){
            result = service.deleteRoleMenuById(id);
        }
        if (result > 0){
            Tool.setSesscesMessage(object,"角色菜单已删除");
        }else {
            Tool.setErrorMessage(object,"角色菜单删除失败");
        }
        return JSON.toJSONString(object);
    }


}
