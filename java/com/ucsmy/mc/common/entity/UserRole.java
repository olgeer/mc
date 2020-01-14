package com.ucsmy.mc.common.entity;

import java.util.Date;

public class UserRole {
    private String usroId;

    private String roleId;

    private String usbaId;

    private Date usroModifyDate;

    private Date usroCreateDate;

    private Byte usroStatus;

    /** 用户基本信息. */
    private UserBasic usroUserBasic;
	/** 角色. */
    private Role usroRole;
    
    private Grade grade;
    
    /**
	 * getter method
	 * @return the usroUserBasic
	 */
	
	public UserBasic getUsroUserBasic() {
		return usroUserBasic;	
	}

	/**
	 * setter method
	 * @param usroUserBasic the usroUserBasic to set
	 */
	
	public void setUsroUserBasic(UserBasic usroUserBasic) {
		this.usroUserBasic = usroUserBasic;
	}

	/**
	 * getter method
	 * @return the usroRole
	 */
	
	public Role getUsroRole() {
		return usroRole;
	}

	/**
	 * setter method
	 * @param usroRole the usroRole to set
	 */
	
	public void setUsroRole(Role usroRole) {
		this.usroRole = usroRole;
	}
	
    
    public String getUsroId() {
        return usroId;
    }

    public void setUsroId(String usroId) {
        this.usroId = usroId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUsbaId() {
        return usbaId;
    }

    public void setUsbaId(String usbaId) {
        this.usbaId = usbaId;
    }

    public Date getUsroModifyDate() {
        return usroModifyDate;
    }

    public void setUsroModifyDate(Date usroModifyDate) {
        this.usroModifyDate = usroModifyDate;
    }

    public Date getUsroCreateDate() {
        return usroCreateDate;
    }

    public void setUsroCreateDate(Date usroCreateDate) {
        this.usroCreateDate = usroCreateDate;
    }

    public Byte getUsroStatus() {
        return usroStatus;
    }

    public void setUsroStatus(Byte usroStatus) {
        this.usroStatus = usroStatus;
    }

	/**
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
}