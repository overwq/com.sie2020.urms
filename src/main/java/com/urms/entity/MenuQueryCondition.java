package com.urms.entity;

import java.io.Serializable;

/**
 * @author 吴权鸿
 */
public class MenuQueryCondition extends BasePage implements Serializable {
    private String menuName;
    private String menuStatus;
    private String menuType;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }


}
