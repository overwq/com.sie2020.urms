package com.urms.tools;

import com.urms.entity.BasePage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InitTool<T>{

    /**
     * 初始化同名属性的分页对象
     * @param t
     * @param <T>
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static  <T> void InitCondition(T t) {
        Class tClass = t.getClass();
        Field pageSize = null;
        Field pageNum = null;
        try {
            pageSize = tClass.getDeclaredField("pageSize");
            pageNum = tClass.getDeclaredField("pageNum");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        pageSize.setAccessible(true);
        pageNum.setAccessible(true);
        try {
            if (pageSize.get(t) == null || pageSize.get(t).toString().trim().isEmpty()){
                pageSize.set(t,20);
            }
            if(pageNum.get(t) == null || pageNum.get(t).toString().trim().isEmpty()){
                pageNum.set(t,1);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

//        Method pageNum = tClass.getMethod("setPageNum",Integer.class);
//        pageNum.setAccessible(true);
//        pageNum.invoke(t,1);
    }

    public static void InitCondition(BasePage condition){
        if (condition.getPageNum() == null || condition.getPageNum().toString().trim().isEmpty()){
            condition.setPageNum(1);
        }
        if (condition.getPageSize() == null || condition.getPageSize().toString().trim().isEmpty()){
            condition.setPageSize(20);
        }
    }


}
