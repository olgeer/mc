package com.ucsmy.mc.module.cmdb.entity;

public class CmdbNetworkIp {
    private Integer id;

    private String ip;

    private String netmask;

    private String gateway;

    private String remarks;

    private Integer belongsIpPoolId;

    private Integer belongsPlatformId;

    private Byte status;

    private Byte inUse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getBelongsIpPoolId() {
        return belongsIpPoolId;
    }

    public void setBelongsIpPoolId(Integer belongsIpPoolId) {
        this.belongsIpPoolId = belongsIpPoolId;
    }

    public Integer getBelongsPlatformId() {
        return belongsPlatformId;
    }

    public void setBelongsPlatformId(Integer belongsPlatformId) {
        this.belongsPlatformId = belongsPlatformId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getInUse() {
        return inUse;
    }

    public void setInUse(Byte inUse) {
        this.inUse = inUse;
    }
}