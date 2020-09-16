package com.urms.entity;

import java.io.Serializable;
import java.util.List;

public class LoginUser implements Serializable {
    private String loginName;
    private List<Menu> menus;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
