package com.ucsmy.mc.common.entity;

import java.util.Date;

public class LoginLog {
    private Integer loloId;

    private Byte loloType;

    private String loloIp;

    private String loloExceptionCode;

    private String loloExceptionDetail;

    private String loloParams;

    private Integer loloUsroId;

    private String loloUsroName;

    private String loloRoleName;

    private Date loloLoginDate;

    private Date loloLogoutDate;

    private Byte loloOwn;

    public Integer getLoloId() {
        return loloId;
    }

    public void setLoloId(Integer loloId) {
        this.loloId = loloId;
    }

    public Byte getLoloType() {
        return loloType;
    }

    public void setLoloType(Byte loloType) {
        this.loloType = loloType;
    }

    public String getLoloIp() {
        return loloIp;
    }

    public void setLoloIp(String loloIp) {
        this.loloIp = loloIp;
    }

    public String getLoloExceptionCode() {
        return loloExceptionCode;
    }

    public void setLoloExceptionCode(String loloExceptionCode) {
        this.loloExceptionCode = loloExceptionCode;
    }

    public String getLoloExceptionDetail() {
        return loloExceptionDetail;
    }

    public void setLoloExceptionDetail(String loloExceptionDetail) {
        this.loloExceptionDetail = loloExceptionDetail;
    }

    public String getLoloParams() {
        return loloParams;
    }

    public void setLoloParams(String loloParams) {
        this.loloParams = loloParams;
    }

    public Integer getLoloUsroId() {
        return loloUsroId;
    }

    public void setLoloUsroId(Integer loloUsroId) {
        this.loloUsroId = loloUsroId;
    }

    public String getLoloUsroName() {
        return loloUsroName;
    }

    public void setLoloUsroName(String loloUsroName) {
        this.loloUsroName = loloUsroName;
    }

    public String getLoloRoleName() {
        return loloRoleName;
    }

    public void setLoloRoleName(String loloRoleName) {
        this.loloRoleName = loloRoleName;
    }

    public Date getLoloLoginDate() {
        return loloLoginDate;
    }

    public void setLoloLoginDate(Date loloLoginDate) {
        this.loloLoginDate = loloLoginDate;
    }

    public Date getLoloLogoutDate() {
        return loloLogoutDate;
    }

    public void setLoloLogoutDate(Date loloLogoutDate) {
        this.loloLogoutDate = loloLogoutDate;
    }

    public Byte getLoloOwn() {
        return loloOwn;
    }

    public void setLoloOwn(Byte loloOwn) {
        this.loloOwn = loloOwn;
    }
}