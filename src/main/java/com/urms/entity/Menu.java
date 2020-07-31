package com.urms.entity;

import java.util.List;

/**
 * 状态 1有 0 无
 */
public class Menu {
    private Integer menuId;
    private String menuType;
    private String menuName;
    private Integer menuParentId;
    private Integer menuStatus;
    private Integer menuQueueNumber;
    private String menuUrl;
    private String menuOpcode;
    private String menuRemarks;
    private Integer menuHidden;
    private String menuParentName;
    private List<Menu> children;
    public String getMenuParentName() {
        return menuParentName;
    }

    public void setMenuParentName(String menuParentName) {
        this.menuParentName = menuParentName;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public Integer getMenuQueueNumber() {
        return menuQueueNumber;
    }

    public void setMenuQueueNumber(Integer menuQueueNumber) {
        this.menuQueueNumber = menuQueueNumber;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuOpcode() {
        return menuOpcode;
    }

    public void setMenuOpcode(String menuOpcode) {
        this.menuOpcode = menuOpcode;
    }

    public String getMenuRemarks() {
        return menuRemarks;
    }

    public void setMenuRemarks(String menuRemarks) {
        this.menuRemarks = menuRemarks;
    }

    public Integer getMenuHidden() {
        return menuHidden;
    }

    public void setMenuHidden(Integer menuHidden) {
        this.menuHidden = menuHidden;
    }


    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
