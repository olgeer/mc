package com.ucsmy.mc.centerM.bean;

import java.util.Date;

/**
 * Created by jack on 2017/5/15.
 */
public class Monitor {
    Integer id;
    String projectid;
    String project;
    String serverip;
    String computername;
    Integer itype;
    Integer alarmlevel;
    String alarmtitle;
    String alarmcontent;
    Date alarmtime;
    Date createtime;
    Integer processingstate;
    String systemId;
    String traceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public String getComputername() {
        return computername;
    }

    public void setComputername(String computername) {
        this.computername = computername;
    }

    public Integer getItype() {
        return itype;
    }

    public void setItype(Integer itype) {
        this.itype = itype;
    }

    public Integer getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(Integer alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public String getAlarmtitle() {
        return alarmtitle;
    }

    public void setAlarmtitle(String alarmtitle) {
        this.alarmtitle = alarmtitle;
    }

    public String getAlarmcontent() {
        return alarmcontent;
    }

    public void setAlarmcontent(String alarmcontent) {
        this.alarmcontent = alarmcontent;
    }

    public Date getAlarmtime() {
        return alarmtime;
    }

    public void setAlarmtime(Date alarmtime) {
        this.alarmtime = alarmtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getProcessingstate() {
        return processingstate;
    }

    public void setProcessingstate(Integer processingstate) {
        this.processingstate = processingstate;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
