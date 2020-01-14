package com.ucsmy.mc.common.entity;

import java.util.Date;

public class RolePermission {
    private String ropeId;

    private String permId;

    private String roleId;

    private Date ropeModifyDate;

    private Date ropeCreateDate;
    /** 扩展属性 */
    private String exPermResource;
    /**
	 * @return the exPermResource
	 */
	public String getExPermResource() {
		return exPermResource;
	}

	/**
	 * @param exPermResource the exPermResource to set
	 */
	public void setExPermResource(String exPermResource) {
		this.exPermResource = exPermResource;
	}

	public String getRopeId() {
        return ropeId;
    }

    public void setRopeId(String ropeId) {
        this.ropeId = ropeId;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getRopeModifyDate() {
        return ropeModifyDate;
    }

    public void setRopeModifyDate(Date ropeModifyDate) {
        this.ropeModifyDate = ropeModifyDate;
    }

    public Date getRopeCreateDate() {
        return ropeCreateDate;
    }

    public void setRopeCreateDate(Date ropeCreateDate) {
        this.ropeCreateDate = ropeCreateDate;
    }
}