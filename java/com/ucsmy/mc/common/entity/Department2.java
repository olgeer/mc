package com.ucsmy.mc.common.entity;

import java.util.Date;

public class Department2 {
    private String depaId;

    private String depaParentId;

    private String depaName;

    private String depaDescription;

    private Date depaModifyDate;

    private Date depaCreateDate;

    private String depaNo;

    private Integer gradId;

    private String orglistId;

    public String getDepaId() {
        return depaId;
    }

    public void setDepaId(String depaId) {
        this.depaId = depaId;
    }

    public String getDepaParentId() {
        return depaParentId;
    }

    public void setDepaParentId(String depaParentId) {
        this.depaParentId = depaParentId;
    }

    public String getDepaName() {
        return depaName;
    }

    public void setDepaName(String depaName) {
        this.depaName = depaName;
    }

    public String getDepaDescription() {
        return depaDescription;
    }

    public void setDepaDescription(String depaDescription) {
        this.depaDescription = depaDescription;
    }

    public Date getDepaModifyDate() {
        return depaModifyDate;
    }

    public void setDepaModifyDate(Date depaModifyDate) {
        this.depaModifyDate = depaModifyDate;
    }

    public Date getDepaCreateDate() {
        return depaCreateDate;
    }

    public void setDepaCreateDate(Date depaCreateDate) {
        this.depaCreateDate = depaCreateDate;
    }

    public String getDepaNo() {
        return depaNo;
    }

    public void setDepaNo(String depaNo) {
        this.depaNo = depaNo;
    }

    public Integer getGradId() {
        return gradId;
    }

    public void setGradId(Integer gradId) {
        this.gradId = gradId;
    }

    public String getOrglistId() {
        return orglistId;
    }

    public void setOrglistId(String orglistId) {
        this.orglistId = orglistId;
    }
}