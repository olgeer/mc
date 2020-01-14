package com.ucsmy.mc.common.entity;

import java.io.Serializable;
import java.util.Date;

public class SystemLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String syloId;

    private String syloMethodName;

    private String syloMethodDescription;

    private String syloMethodType;

    private Byte syloType;

    private String syloIp;

    private String syloExceptionCode;

    private String syloExceptionDetail;

    private String syloParams;

    private String syloUsroId;

    private String syloUsroName;

    private String syloRoleName;

    private Date syloCreateDate;

    private String syloModuleName;

    private String syloModuleDescription;

    private String syloSql;

    private Byte syloOwn;

    public String getSyloId() {
        return syloId;
    }

    public void setSyloId(String syloId) {
        this.syloId = syloId;
    }

    public String getSyloMethodName() {
        return syloMethodName;
    }

    public void setSyloMethodName(String syloMethodName) {
        this.syloMethodName = syloMethodName;
    }

    public String getSyloMethodDescription() {
        return syloMethodDescription;
    }

    public void setSyloMethodDescription(String syloMethodDescription) {
        this.syloMethodDescription = syloMethodDescription;
    }

    public String getSyloMethodType() {
        return syloMethodType;
    }

    public void setSyloMethodType(String syloMethodType) {
        this.syloMethodType = syloMethodType;
    }

    public Byte getSyloType() {
        return syloType;
    }

    public void setSyloType(Byte syloType) {
        this.syloType = syloType;
    }

    public String getSyloIp() {
        return syloIp;
    }

    public void setSyloIp(String syloIp) {
        this.syloIp = syloIp;
    }

    public String getSyloExceptionCode() {
        return syloExceptionCode;
    }

    public void setSyloExceptionCode(String syloExceptionCode) {
        this.syloExceptionCode = syloExceptionCode;
    }

    public String getSyloExceptionDetail() {
        return syloExceptionDetail;
    }

    public void setSyloExceptionDetail(String syloExceptionDetail) {
        this.syloExceptionDetail = syloExceptionDetail;
    }

    public String getSyloParams() {
        return syloParams;
    }

    public void setSyloParams(String syloParams) {
        this.syloParams = syloParams;
    }

    public String getSyloUsroId() {
        return syloUsroId;
    }

    public void setSyloUsroId(String syloUsroId) {
        this.syloUsroId = syloUsroId;
    }

    public String getSyloUsroName() {
        return syloUsroName;
    }

    public void setSyloUsroName(String syloUsroName) {
        this.syloUsroName = syloUsroName;
    }

    public String getSyloRoleName() {
        return syloRoleName;
    }

    public void setSyloRoleName(String syloRoleName) {
        this.syloRoleName = syloRoleName;
    }

    public Date getSyloCreateDate() {
        return syloCreateDate;
    }

    public void setSyloCreateDate(Date syloCreateDate) {
        this.syloCreateDate = syloCreateDate;
    }

    public String getSyloModuleName() {
        return syloModuleName;
    }

    public void setSyloModuleName(String syloModuleName) {
        this.syloModuleName = syloModuleName;
    }

    public String getSyloModuleDescription() {
        return syloModuleDescription;
    }

    public void setSyloModuleDescription(String syloModuleDescription) {
        this.syloModuleDescription = syloModuleDescription;
    }

    public String getSyloSql() {
        return syloSql;
    }

    public void setSyloSql(String syloSql) {
        this.syloSql = syloSql;
    }

    public Byte getSyloOwn() {
        return syloOwn;
    }

    public void setSyloOwn(Byte syloOwn) {
        this.syloOwn = syloOwn;
    }
}