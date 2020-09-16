package com.urms.controller.user;

import com.alibaba.fastjson.JSON;
import com.urms.entity.JsonObject;
import com.urms.entity.Menu;
import com.urms.entity.User;
import com.urms.entity.UserQueryCondition;
import com.urms.service.UserRoleService;
import com.urms.service.UserService;
import com.urms.tools.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author 吴权鸿
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    UserRoleService userRoleService;

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    /**
     * 条件查询用户信息
     * @param userQueryCondition
     * @return
     */
    @RequestMapping("/user/select/condition")
    public String selectUserByCondition(@RequestBody UserQueryCondition userQueryCondition ){
        if (userQueryCondition.getPageSize() == null){
            userQueryCondition.setPageSize(15);
        }
        JsonObject object = new JsonObject();
        List<User> result = service.selectAll(userQueryCondition);
        if (result.size() > 0){
            Tool.setSesscesMessage(object,"用户信息获取成功");
        }else {
            Tool.setErrorMessage(object,"用户信息获取失败，无对应信息");
        }
        object.setData(result);
        return JSON.toJSONString(object);
    }

    /**
     * 获取分页查询统计数据
     * @return
     */
    @RequestMapping("/user/select/count")
    public Integer getNumCount(@RequestBody UserQueryCondition userQueryCondition){

        return service.getCount(userQueryCondition);
    }
    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @RequestMapping("/user/insert")
    public String insertUser(@RequestBody User user){
        JsonObject jsonObject = new JsonObject();
        if (Tool.isEmpty(user)) {
            return JSON.toJSONString(Tool.setErrorMessage(jsonObject));
        }
        user.setUserPassword(Tool.md5(user.getUserPassword()));
        int result = service.insertUser(user);
        Tool.getResult(jsonObject,result,"用户添加成功","用户添加失败");

        return JSON.toJSONString(jsonObject);
    }

    /**
     * 根据id删除用户信息 逻辑删除
     * @param userId
     * @return
     */
    @RequestMapping("/user/delete")
    public String deleteUserById(@RequestBody Integer userId){
        JsonObject jsonObject = new JsonObject();
        if (Tool.isEmpty(userId)){
            return JSON.toJSONString(Tool.setErrorMessage(jsonObject));
        }
        Integer result = service.deleteUserById(userId);
        Tool.getResult(jsonObject,result,"用户已经删除","用户删除失败");

        return JSON.toJSONString(jsonObject);
    }


    /**
     * 根据id修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/user/update")
    public String updateUserById(@RequestBody User user){

        JsonObject object = new JsonObject();
        if (Tool.isEmpty(user)){
            return  JSON.toJSONString(Tool.setErrorMessage(object));
        }
        String userPassword = user.getUserPassword();
        if (!Tool.isEmpty(userPassword)){
            user.setUserPassword(Tool.md5(userPassword));
        }
        Integer result = service.updateUserById(user);
        Tool.getResult(object,result,"用户信息修改成功","用户信息修改失败");

//        checkUserForUpdate(user1);
        return JSON.toJSONString(object);
    }


    /**
     * 返回没有权限的用户的提示信息
     * @return
     */
    @RequestMapping("/user/no/permission")
    public String setNoPermissionMessage(){
        JsonObject object = new JsonObject();
        object.setMessage(this.getTips());
        object.setCurrentTime(new Date());
        object.setState(402);
        return JSON.toJSONString(object);
    }

    private String getTips(){
        String[] tips = new String[]{
                "你没有权限",
            "不要再试了,你真的没有权限",
            "你不要过来啊",
            "再点我就把你吃掉",
            "神经病啊，我不要面子的吗",
            "游泳健身了解一下？"
        };
        Random random = new Random();
        return tips[random.nextInt(tips.length)];
    }

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/user/select/id")
    public String selectByUserId(@RequestBody Integer userId){

        return JSON.toJSONString(service.selectByUserId(userId));
    }

    /**
     * 根据用户名查找用户信息
     * @param userLoginName
     * @return
     */
    @RequestMapping("/user/select/name")
    public String selectByLoginName(@RequestBody String userLoginName){

        return JSON.toJSONString(service.selectUserByLoginName(userLoginName));
    }

   /* *//**
     * 根据用户id修改用户状态
     * @param user
     * @return
     *//*
    @RequestMapping("/user/update/status")
    @Transactional
    public String updateUserStatusById(@RequestBody String user){
        User user1 = JSON.parseObject(user,User.class);
        if (Tool.isEmpty(user)){
            String msg = "";
            log.info(Tool.msg(msg));
            return msg;
        }
        checkStatus(user1.getUserStatus());
        return service.updateUserStatusById(user1).toString();
    }*/

    public boolean checkUser(User user){
        if (checkName(user.getUserName()) && checkLoginName(user.getUserLoginName())
            && checkPassword(user.getUserPassword()) && checkComm(user)){
            return true;
        }
        return false;
    }

    public boolean checkUserForUpdate(User user){
        if(!Tool.isEmpty(user.getUserName())){
            if (!checkName(user.getUserName())){
                return false;
            }
        }
        if(!Tool.isEmpty(user.getUserLoginName())){
            if (!checkLoginName(user.getUserLoginName())){
                return false;
            }
        }
        if(!Tool.isEmpty(user.getUserPassword())){
            if (!checkPassword(user.getUserPassword())){
                return false;
            }
        }
        if (!checkComm(user)){
            return false;
        }
        return true;
    }

    public boolean checkComm(User user){
        if(!Tool.isEmpty(user.getUserSex())){
            if (!checkSex(user.getUserSex())){
                return false;
            }
        }
        if(!Tool.isEmpty(user.getUserPhone())){
            if (!checkPhone(user.getUserPhone())){
                return false;
            }
        }
        if(!Tool.isEmpty(user.getUserEmail())){
            if (!checkEmail(user.getUserEmail())){
                return false;
            }
        }
        if(!Tool.isEmpty(user.getUserStatus())){
            if (!checkStatus(user.getUserStatus())){
                return false;
            }
        }
        if(!Tool.isEmpty(user.getUserRemarks())){
            if (!checkRemarks(user.getUserRemarks())){
                return false;
            }
        }
        return true;
    }

    public boolean checkLoginName(String loginName){
        String patten = "^[a-zA-Z\\u4e00-\\u9fa5]{6,20}$";
        if(!matches(patten,loginName)){
           return false;
        }
        if(service.selectUserByLoginName(loginName) !=null ){
           return false;
        }
        return true;
    }

    public boolean checkName(String name){
        String patten = "^[a-zA-Z\\u4e00-\\u9fa5]{2,50}$";
        if(matches(patten,name)){
           return true;
        }
        return false;
    }

    public boolean checkPassword(String password){
        String patten = "^[0-9a-zA-Z]{6,20}$";
        if(matches(patten,password)){
            return true;
        }
        return false;
    }

    public boolean checkRemarks(String remarks){
        Integer maxRemarksLength = 500;
        if(remarks.length()<= maxRemarksLength){
            return true;
        }
        return false;
    }

    public boolean checkStatus(Integer status){
        if(status == 0 || status == 1){
            return true;
        }
        return false;
    }

    public boolean checkEmail(String email){
        String patten = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,200}$";
        if(matches(patten,email)){
           return true;
        }
        return false;
    }

    public boolean checkPhone(Long phone){
        String patten = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
        if(matches(patten,phone.toString())){
           return true;
        }
        return false;
    }

    public boolean checkSex(Integer sex){
        if(sex == 0 || sex == 1){
            return true;
        }
        return false;
    }

    public Boolean matches(String patten,String str){
        if(Pattern.matches(patten,str)){
            return true;
        }
        return false;
    }

    public void setErrorMessage(JsonObject jsonObject){
        jsonObject.setMessage("error occurred");
        jsonObject.setState(400);
        jsonObject.setCurrentTime(new Date());
    }

    public void setSesscesMessage(JsonObject jsonObject,String message){
        jsonObject.setMessage(message);
        jsonObject.setState(200);
        jsonObject.setCurrentTime(new Date());
    }
}
