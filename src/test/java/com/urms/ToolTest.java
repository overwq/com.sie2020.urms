package com.urms;

import com.urms.entity.MenuQueryCondition;
import com.urms.entity.RoleQueryCondition;
import com.urms.tools.InitTool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

@SpringBootTest
public class ToolTest {

    @Test
    public void InitConditionTest() throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
        RoleQueryCondition rq = new RoleQueryCondition();
        InitTool.InitCondition(rq);
        System.out.println(rq.getPageNum());
        System.out.println(rq.getPageSize());
    }

    @Test
    public void InitConTest(){
        MenuQueryCondition mq =  new MenuQueryCondition();
        mq.setMenuName("test");
        mq.setMenuType("test");
        InitTool.InitCondition(mq);
        System.out.println(mq.getMenuName());
        System.out.println(mq.getPageNum());
    }
}
