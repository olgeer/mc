package com.ucsmy.mc.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Permission {
    private String permId;

    private String permParentId;

    private String permName;

    private String permDescribe;

    private String permUrl;

    private String permIcon;

    private String permResource;

    private Date permModifyDate;

    private Date permCreateDate;

    private Byte permSort;

    private Byte permUseStatus;

    private Byte permType;
    
    private List<Permission> childPermissionList;
    
    private Permission parentPermission;

    /**
	 * @return the childPermissionList
	 */
	public List<Permission> getChildPermissionList() {
		return childPermissionList;
	}

	/**
	 * @param childPermissionList the childPermissionList to set
	 */
	public void setChildPermissionList(List<Permission> childPermissionList) {
		this.childPermissionList = childPermissionList;
	}

	/**
	 * @return the parentPermission
	 */
	public Permission getParentPermission() {
		return parentPermission;
	}

	/**
	 * @param parentPermission the parentPermission to set
	 */
	public void setParentPermission(Permission parentPermission) {
		this.parentPermission = parentPermission;
	}

	public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getPermParentId() {
        return permParentId;
    }

    public void setPermParentId(String permParentId) {
        this.permParentId = permParentId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermDescribe() {
        return permDescribe;
    }

    public void setPermDescribe(String permDescribe) {
        this.permDescribe = permDescribe;
    }

    public String getPermUrl() {
        return permUrl;
    }

    public void setPermUrl(String permUrl) {
        this.permUrl = permUrl;
    }

    public String getPermIcon() {
        return permIcon;
    }

    public void setPermIcon(String permIcon) {
        this.permIcon = permIcon;
    }

    public String getPermResource() {
        return permResource;
    }

    public void setPermResource(String permResource) {
        this.permResource = permResource;
    }

    public Date getPermModifyDate() {
        return permModifyDate;
    }

    public void setPermModifyDate(Date permModifyDate) {
        this.permModifyDate = permModifyDate;
    }

    public Date getPermCreateDate() {
        return permCreateDate;
    }

    public void setPermCreateDate(Date permCreateDate) {
        this.permCreateDate = permCreateDate;
    }

    public Byte getPermSort() {
        return permSort;
    }

    public void setPermSort(Byte permSort) {
        this.permSort = permSort;
    }

    public Byte getPermUseStatus() {
        return permUseStatus;
    }

    public void setPermUseStatus(Byte permUseStatus) {
        this.permUseStatus = permUseStatus;
    }

    public Byte getPermType() {
        return permType;
    }

    public void setPermType(Byte permType) {
        this.permType = permType;
    }
    
    
    private List<Permission> children = new ArrayList<Permission>();

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}
}