package com.ucsmy.mc.common.entity;

public class OrglistTemp {
    private String dpid;

    private String dpname;

    private String parentdpid;

    private Byte version;

    public String getDpid() {
        return dpid;
    }

    public void setDpid(String dpid) {
        this.dpid = dpid;
    }

    public String getDpname() {
        return dpname;
    }

    public void setDpname(String dpname) {
        this.dpname = dpname;
    }

    public String getParentdpid() {
        return parentdpid;
    }

    public void setParentdpid(String parentdpid) {
        this.parentdpid = parentdpid;
    }

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}