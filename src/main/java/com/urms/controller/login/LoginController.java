package com.urms.controller.login;

import com.alibaba.fastjson.JSON;
import com.urms.entity.*;
import com.urms.service.UserService;
import com.urms.tools.Tool;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author 吴权鸿
 */
@RestController
//@CrossOrigin
public class LoginController {
    @Autowired
    UserService service;

    private final static Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * 根据用户名和密码返回登陆信息
     * @param user user对象 userLoginName和userPassword
     * @return 登陆对象user 用户所属角色集合role 用户对应的角色的菜单集合menu
     */
    @RequestMapping("/login")
    public String doLogin(@RequestBody User user) throws JoseException {
        JsonObject  object = new JsonObject();
        user.setUserPassword(Tool.md5(user.getUserPassword()));
        if (service.userLogin(user)){
            Map<String,Object> box = new HashMap<>(8);
            putUserAndRoleAndMenuToBox(object,user,box);
            object.setBox(box);
            object.setState(200);
            object.setMessage("用户："+user.getUserLoginName()+"登陆成功");
        }else {
            object.setState(400);
            object.setMessage("登陆失败！  用户名："+user.getUserLoginName()+" 密码：******");
        }
        object.setCurrentTime(new Date());
        return JSON.toJSONString(object);
    }

    /**
     * 修改用户、角色、菜单信息后，更新localStory里的信息 未使用
     * @param userId
     * @return
     */
    @RequestMapping("/user/relogin")
    public String reLogin(@RequestBody Integer userId){
        JsonObject  object = new JsonObject();
        User user = service.selectByUserId(userId);
        Map<String,Object> box = new HashMap<>(8);
        putUserAndRoleAndMenuToBox(object,user,box);
        object.setBox(box);
        object.setState(200);
        object.setCurrentTime(new Date());
        return JSON.toJSONString(object);
    }

    public void putUserAndRoleAndMenuToBox(JsonObject object,User user,Map<String,Object> box){
        String userLoginName = user.getUserLoginName();
        List<Role> roles = service.getUserRole(userLoginName);
        List<Menu> menus = service.getUserMenu(userLoginName);
        User realUser = service.selectUserByLoginName(userLoginName);
        realUser.setUserPassword(Tool.md5(realUser.getUserPassword()));
        /*            box.put("token",menus);*/
        box.put("role",roles);
        box.put("user",realUser);
        box.put("menu",menus);
    }


/*
    @RequestMapping("/logout")
    public void logout(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("role");
        session.removeAttribute("menu");
    }*/





    /*return "redirect:tologin";*/
}
