package com.urms.controller.userrole;

import com.alibaba.fastjson.JSON;
import com.urms.entity.JsonObject;
import com.urms.entity.UserRole;
import com.urms.entity.UserRoleQueryCondition;
import com.urms.service.UserRoleService;
import com.urms.tools.Tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


/**
 * @author 吴权鸿
 */
@RestController
public class UserRoleController {
    @Autowired
    UserRoleService service;
    private static final Logger log = LoggerFactory.getLogger(UserRoleController.class);

    /**
     * 根据用户所属角色名查找用户所属角色信息
     * @param userLoginName
     * @return
     */
    @RequestMapping("/userrole/select/userloginname")
    public String selectByUserRoleName(@RequestBody String userLoginName){
        JsonObject object = new JsonObject();
        List<UserRole> result = service.selectUserRoleByUserLoginName(userLoginName);
        if (result.size() > 0 ){
            Tool.setSesscesMessage(object,"用户角色信息获取成功");
        }else {
            Tool.setErrorMessage(object,"用户角色信息获取失败，无对应信息");
        }
        object.setData(result);
        return JSON.toJSONString(object);
    }

    /**
     * 条件查询
     * @param userRoleQueryCondition
     * @return
     */
    @RequestMapping("/userrole/select/condition")
    public String selectUserRoleByCondition(@RequestBody UserRoleQueryCondition userRoleQueryCondition){
        JsonObject object = new JsonObject();
        List<UserRole> result = service.selectAll(userRoleQueryCondition);
        if (result.size() > 0 ){
            Tool.setSesscesMessage(object,"用户角色信息获取成功");
        }else {
            Tool.setErrorMessage(object,"用户角色信息获取失败，无对应信息");
        }
        object.setData(result);
        return JSON.toJSONString(object);
    }



    /**
     * 添加用户所属角色数据
     * @param userRoles
     * @return
     */
    @RequestMapping("/userrole/insert")
    public String insertUserRole(@RequestBody UserRole[] userRoles){
        System.out.println("UserRole 64: "+new Date()+" "+JSON.toJSONString(userRoles));
        JsonObject object = new JsonObject();
//        UserRoleQueryCondition condition = new UserRoleQueryCondition();
//        condition.setUserId(userRole.getUserId());
//        condition.setRoleId(userRole.getRoleId());
//        if (service.selectAll(condition).size() > 0){
//            Tool.setErrorMessage(object,"该用户已用户此角色");
//            return JSON.toJSONString(object);
//        }
        int result = 0;
        if (Tool.isEmpty(userRoles)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        for(UserRole userRole : userRoles){
            result = service.insertUserRole(userRole);
        }
        if (result > 0){
            Tool.setSesscesMessage(object,"用户角色信息已经添加成功");
        }else {
            Tool.setErrorMessage(object,"用户角色信息添加失败");
        }
        return JSON.toJSONString(object);
    }

    /**
     * 根据用户所属角色id删除用户所属角色信息
     * @param userRoleIds
     * @return
     */
    @RequestMapping("/userrole/delete")
    public String deleteUserRoleById(@RequestBody Integer[] userRoleIds){
        JsonObject object = new JsonObject();
        int result = 0;
        if (Tool.isEmpty(userRoleIds)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        for(int id : userRoleIds){
            result =  service.deleteUserRoleById(id);
        }
        if (result > 0){
            Tool.setSesscesMessage(object,"用户所属角色已删除");
        }else {
            Tool.setErrorMessage(object,"用户所属角色信息删除失败");
        }
        return JSON.toJSONString(object);
    }

    /**
     * 根据用户所属角色id更新用户所属角色信息
     * @param userRole
     * @return
     */
    @RequestMapping("/userrole/update")
    public String updateUserRoleById(@RequestBody UserRole userRole){
        JsonObject object = new JsonObject();
        if (Tool.isEmpty(userRole)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        if (service.updateUserRoleById(userRole) > 0){
            Tool.setSesscesMessage(object,"用户所属角色信息修改成功");
        }else {
            Tool.setErrorMessage(object,"用户所属角色信息修改失败");
        }
        return JSON.toJSONString(object);
    }


    /**
     * 根据用户所属角色id查找用户所属角色信息
     * @param userRoleId
     * @return
     */
    @RequestMapping("/userrole/select/id")
    public String selectByUserRoleId(@RequestBody Integer userRoleId){
        if (Tool.isEmpty(userRoleId)){
            String msg = "userRoleId for qeury is null";
            log.info(Tool.msg(msg));
            return msg;
        }

        return JSON.toJSONString(service.selectByUserRoleId(userRoleId));
    }




}
