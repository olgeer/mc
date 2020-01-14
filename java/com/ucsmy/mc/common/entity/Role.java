package com.ucsmy.mc.common.entity;

import java.util.Date;

public class Role {
    private String roleId;

    private String roleName;

    private Byte roleType;

    private String roleDescribe;

    private Date roleModifyDate;

    private Date roleCreateDate;

    private Byte roleSort;

    private Byte roleUseStatus;

    private Byte roleEditStatus;

    private String gradId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Byte getRoleType() {
        return roleType;
    }

    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public Date getRoleModifyDate() {
        return roleModifyDate;
    }

    public void setRoleModifyDate(Date roleModifyDate) {
        this.roleModifyDate = roleModifyDate;
    }

    public Date getRoleCreateDate() {
        return roleCreateDate;
    }

    public void setRoleCreateDate(Date roleCreateDate) {
        this.roleCreateDate = roleCreateDate;
    }

    public Byte getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Byte roleSort) {
        this.roleSort = roleSort;
    }

    public Byte getRoleUseStatus() {
        return roleUseStatus;
    }

    public void setRoleUseStatus(Byte roleUseStatus) {
        this.roleUseStatus = roleUseStatus;
    }

    public Byte getRoleEditStatus() {
        return roleEditStatus;
    }

    public void setRoleEditStatus(Byte roleEditStatus) {
        this.roleEditStatus = roleEditStatus;
    }

    public String getGradId() {
        return gradId;
    }

    public void setGradId(String gradId) {
        this.gradId = gradId;
    }
}