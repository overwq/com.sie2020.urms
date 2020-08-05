package com.urms.tools;

import com.alibaba.fastjson.JSON;
import com.urms.entity.JsonObject;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴权鸿
 */
public class Tool {

//    private static Map<String,Object> map = new HashMap<>();

    public static boolean isEmpty(String input){
        if (input == null || input.trim().isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Object obj){
        if ( obj == null || "{}".equals(JSON.toJSONString(obj)) || "[]".equals(obj.toString())){
            return true;
        }
        return false;
    }

    public static JsonObject getResult(JsonObject jsonObject,Integer result,String successMessage,String errorMessage){
        if (result > 0 ){
            setSesscesMessage(jsonObject,successMessage);
        }else {
            setErrorMessage(jsonObject,errorMessage);
        }
        return jsonObject;
    }

    public static JsonObject setEmptyMessage(Object object,JsonObject jsonObject){
        if (isEmpty(object)){
            setErrorMessage(jsonObject);
        }
        return jsonObject;
    }

    public static JsonObject setErrorMessage(JsonObject jsonObject){
        jsonObject.setMessage("query condition is null");
        jsonObject.setState(400);
        jsonObject.setCurrentTime(new Date());
        return jsonObject;
    }

    public static JsonObject setErrorMessage(JsonObject jsonObject,String errorMessage){
        jsonObject.setMessage(errorMessage);
        jsonObject.setState(400);
        jsonObject.setCurrentTime(new Date());
        return jsonObject;
    }

    public static JsonObject setSesscesMessage(JsonObject jsonObject,String message){
        jsonObject.setMessage(message);
        jsonObject.setState(200);
        jsonObject.setCurrentTime(new Date());
        return jsonObject;
    }


    /**
     * springboot 自带的md5加密工具
     * @param str
     * @return
     */
    public static String md5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static String msg(String msg){
        return "********"+msg+"********";
    }

}
