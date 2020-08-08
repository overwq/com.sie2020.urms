package com.urms.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

public class Role {
    private Integer roleId;
    private String roleName;
    private String roleRemarks;
    private String roleCode;
    @JSONField(format = "yyyy-MM-dd")
    private Timestamp roleCreateTime;
    @JSONField(format = "yyyy-MM-dd")
    private Timestamp roleInvalidTime;
    private Integer roleHidden;
    private Integer roleStatus;
    private Integer userId = 0;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Timestamp getRoleInvalidTime() {
        return roleInvalidTime;
    }

    public void setRoleInvalidTime(Timestamp roleInvalidTime) {
        this.roleInvalidTime = roleInvalidTime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleRemarks() {
        return roleRemarks;
    }

    public void setRoleRemarks(String roleRemarks) {
        this.roleRemarks = roleRemarks;
    }

    public Timestamp getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(Timestamp roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public Integer getRoleHidden() {
        return roleHidden;
    }

    public void setRoleHidden(Integer roleHidden) {
        this.roleHidden = roleHidden;
    }
}
