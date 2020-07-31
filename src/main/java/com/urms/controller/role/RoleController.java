package com.urms.controller.role;

import com.alibaba.fastjson.JSON;
import com.urms.entity.JsonObject;
import com.urms.entity.Role;
import com.urms.entity.RoleQueryCondition;
import com.urms.service.RoleService;
import com.urms.tools.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 吴权鸿
 */
@RestController
public class RoleController {
    @Autowired
    RoleService service;
    private final static Logger log = LoggerFactory.getLogger(RoleController.class);
    /**
     * 查询所有角色信息
     * @param roleQueryCondition
     * @return
     */
    @RequestMapping("/role/select/condition")
    public String selectRoleByCondition(@RequestBody RoleQueryCondition roleQueryCondition){
        JsonObject object = new JsonObject();
        List<Role> result = service.selectAll(roleQueryCondition);
        if (result.size() > 0 ){
            Tool.setSesscesMessage(object,"角色信息获取成功");
        }else {
            Tool.setErrorMessage(object,"角色信息获取失败，无对应信息");
        }
        object.setData(result);
        return JSON.toJSONString(object);
    }

    @RequestMapping("/role/select/count")
    public Integer getTotalCount(@RequestBody RoleQueryCondition condition){

        return service.getCount(condition);
    }

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    @RequestMapping("/role/insert")
    @Transactional(rollbackFor = Exception.class)
    public String insertRole(@RequestBody Role role){
        JsonObject object = new JsonObject();
        if (Tool.isEmpty(role)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        Integer result = service.insertRole(role);
        if (result > 0 ){
            Tool.setSesscesMessage(object,"角色添加成功");
        }else {
            Tool.setErrorMessage(object,"角色删除失败");
        }

        return JSON.toJSONString(object);
    }



    /**
     * 根据id删除角色信息 逻辑删除
     * @param roleId
     * @return
     */
    @RequestMapping("/role/delete")
    public String deleteRoleById(@RequestBody Integer roleId){
        JsonObject object = new JsonObject();
        if (Tool.isEmpty(roleId)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        if (service.deleteRoleById(roleId) > 0){
            Tool.setSesscesMessage(object,"角色已删除");
        }else {
            Tool.setErrorMessage(object,"角色删除失败");
        }
        return JSON.toJSONString(object);
    }

    /**
     * 根据id更新角色信息
     * @param role
     * @return
     */
    @RequestMapping("/role/update")
    public String updateRoleById(@RequestBody Role role){
        JsonObject object = new JsonObject();
        if (Tool.isEmpty(role)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        if (service.updateRoleById(role) > 0){
            Tool.setSesscesMessage(object,"角色修改成功");
        }else {
            Tool.setErrorMessage(object,"角色修改失败");
        }
        return JSON.toJSONString(object);
    }


    /**
     * 根据角色id查询角色信息
     * @param roleId
     * @return
     */
    @RequestMapping("/role/select/id")
    public String selectByRoleId(@RequestBody Integer roleId){

        return JSON.toJSONString(service.selectByRoleId(roleId));
    }

    /**
     * 根据角色名查询角色信息
     * @param roleName
     * @return
     */
    @RequestMapping("/role/select/name")
    public String selectRoleByRoleName(@RequestBody String roleName){
        if (Tool.isEmpty(roleName)){
            String msg = "roleName for query is null";
            log.info(Tool.msg(msg));
            return msg;
        }
        return JSON.toJSONString(service.selectRoleByRoleName(roleName));
    }

    public PrintWriter getPrintWrite(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        return response.getWriter();
    }

    public boolean checkRoleName(String roleName){
        Integer maxRoleNameLength = 100;
        if(Tool.isEmpty(roleName)){
            return false;
        }
        if(roleName.length()> maxRoleNameLength){
            return false;
        }
        if (!Tool.isEmpty(service.selectRoleByRoleName(roleName))){
            return false;
        }
        return true;
    }

    public boolean checkRoleNameForUpdate(String roleName){
        Integer maxRoleNameLength = 100;
        if(Tool.isEmpty(roleName)){
            return true;
        }
        if(roleName.length()> maxRoleNameLength){
            return false;
        }
        if (!Tool.isEmpty(service.selectRoleByRoleName(roleName))){
            return false;
        }
        return true;
    }

    public boolean checkRoleCode(String roleCode){
        Integer maxRoleCodeLength = 50;
        if(Tool.isEmpty(roleCode)){
            return false;
        }
        if(roleCode.length()> maxRoleCodeLength){
            return false;
        }
        return true;
    }

    public boolean checkRoleCodeForUpdate(String roleCode){
        Integer maxRoleCodeLength = 50;
        if (Tool.isEmpty(roleCode)){
            return true;
        }
        if(roleCode.length()> maxRoleCodeLength){
            return false;
        }
        return true;
    }

}
