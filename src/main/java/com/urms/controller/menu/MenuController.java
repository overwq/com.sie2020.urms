package com.urms.controller.menu;

import com.alibaba.fastjson.JSON;
import com.urms.entity.JsonObject;
import com.urms.entity.Menu;
import com.urms.entity.MenuQueryCondition;
import com.urms.service.MenuService;
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
public class MenuController {
    @Autowired
    MenuService service;
    private static final Logger log = LoggerFactory.getLogger(MenuController.class);
    /**
     * 查询所有菜单信息
     * @param menuQueryCondition
     * @return
     */
    @RequestMapping("/menu/select/condition")
    public String selectMenuByCondition(@RequestBody MenuQueryCondition menuQueryCondition){
        JsonObject object = new JsonObject();
        List<Menu> result = service.selectAll(menuQueryCondition);
        if (result.size() > 0 ){
            Tool.setSesscesMessage(object,"菜单信息获取成功");
        }else {
            Tool.setErrorMessage(object,"菜单信息获取成功，无对应信息");
        }
        object.setData(result);
        return JSON.toJSONString(object);
    }

    @RequestMapping("/menu/select/tree")
    public String getTreeMenu(){

        return JSON.toJSONString(service.getTreeMenu());
    }


    /**
     * 添加菜单数据
     * @param menu
     * @return
     */
    @RequestMapping("/menu/insert")
    public String insertMenu(@RequestBody Menu menu){
        JsonObject object = new JsonObject();
        if (Tool.isEmpty(menu)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        Integer result = service.insertMenu(menu);
        if (result > 0){
            Tool.setSesscesMessage(object,"菜单已添加");
        }else {
            Tool.setErrorMessage(object,"菜单添加失败");
        }
//        checkMenuName(menu.getMenuName());
        return JSON.toJSONString(object);
    }

    /**
     * 根据id删除删除信息
//     * @param menuIds
     * @return
     */
    @RequestMapping("/menu/delete")
    public String deleteMenuById(@RequestBody Integer menuId){

        JsonObject object = new JsonObject();
        if (Tool.isEmpty(menuId)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        Integer result = service.deleteMenuById(menuId);
        if (result > 0){
            Tool.setSesscesMessage(object,"菜单已删除");
        }else {
            Tool.setErrorMessage(object,"菜单删除失败");
        }
        return JSON.toJSONString(object);
    }

    /**
     * 根据菜单id修改菜单信息
     * @param menu
     * @return
     */
    @RequestMapping("/menu/update")
    public String updateMenuById(@RequestBody Menu menu){

        JsonObject object = new JsonObject();
        if (Tool.isEmpty(menu)){
            return JSON.toJSONString(Tool.setErrorMessage(object));
        }
        Integer result = service.updateMenuById(menu);
        if (result > 0){
            Tool.setSesscesMessage(object,"菜单修改成功");
        }else {
            Tool.setErrorMessage(object,"菜单修改失败");
        }
        return JSON.toJSONString(object);
    }

    /**
     * 根据菜单id查询菜单信息
     * @param menuId
     * @return
     */
    @RequestMapping("/menu/select/id")
    public String selectByMenuId(@RequestBody Integer menuId){
        if (Tool.isEmpty(menuId)){
            String msg = "menuId for query is null";
            log.info(Tool.msg(msg));
            return msg;
        }
        return JSON.toJSONString(service.selectByMenuId(menuId));
    }

    @RequestMapping("/menu/select/count")
    public Integer  getTotalCount(@RequestBody MenuQueryCondition condition){
//        System.out.println(service.getCount(condition));
        return service.getCount(condition);
    }

    /**
     * 根据菜单名查询菜单信息
     * @param menuName
     * @return
     */
    @RequestMapping("/menu/select/name")
    public String selectMenuByMenuName(@RequestBody String menuName){
        if (Tool.isEmpty(menuName)){
            String msg = "menuName for query is null";
            log.info(msg);
            return msg;
        }
        return JSON.toJSONString(service.selectMenuByMenuName(menuName));
    }

    @RequestMapping("/menu/select/options")
    public String getOptionValues(){
        return JSON.toJSONString(service.getAddMenuInfo());
    }

    public void checkMenuName(String menuName){
        int maxMenuNameLength = 20;
        if(Tool.isEmpty(menuName)){
            throw new RuntimeException("menuName is null");
        }
        if(menuName.length() > maxMenuNameLength){
            throw new RuntimeException("menuName has over maxMenuNameLength 20 !");
        }
    }



}
