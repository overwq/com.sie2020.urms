package com.urms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

/**
 * @author 吴权鸿
 */
public class RoleQueryCondition extends BasePage{
    private String roleName;
    private Integer roleStatus;
    private String roleCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }


}
