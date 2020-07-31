package com.urms.controller.login;

import com.alibaba.fastjson.JSON;
import com.urms.entity.*;
import com.urms.service.UserService;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 吴权鸿
 */
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    UserService service;

    private final static Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * 根据用户名和密码返回登陆信息
     * @param user user对象 userLoginName和userPassword
     * @param session 存储 登陆对象user 用户所属角色集合role 用户对应的角色的菜单集合menu
     * @return 登陆对象user 用户所属角色集合role 用户对应的角色的菜单集合menu
     */
    @RequestMapping("/login")
    public String doLogin(@RequestBody User user,HttpSession session) throws JoseException {
        JsonObject  object = new JsonObject();
        if (service.userLogin(user)){
            String userLoginName = user.getUserLoginName();
            List<Role> roles = service.getUserRole(userLoginName);
            List<Menu> menus = service.getUserMenu(userLoginName);
//            session.setAttribute("user",user);
//            session.setAttribute("role",roles);
//            session.setAttribute("menu",menus);
            User realUser = service.selectUserByLoginName(userLoginName);
            Map<String,Object> box = new HashMap<>(8);
            box.put("role",roles);
            box.put("user",realUser);
            box.put("menu",menus);
//            box.put("token",menus);
            object.setBox(box);
            object.setState(200);
            object.setMessage("用户："+userLoginName+"登陆成功");
            object.setCurrentTime(new Date());
            return JSON.toJSONString(object);
        }else {
            object = new JsonObject();
            object.setState(400);
            object.setMessage("登陆失败！  用户名："+user.getUserLoginName()+" 密码：******");
            object.setCurrentTime(new Date());
            return JSON.toJSONString(object);
        }

    }


    @RequestMapping("/logout")
    public void logout(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("role");
        session.removeAttribute("menu");
    }


    /**
     * springboot 自带的md5加密工具
     * @param str
     * @return
     */
    public String md5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }



    /*return "redirect:tologin";*/
}
