package com.ucsmy.mc.module.cmdb.entity;

public class CmdbDeviceLogicInterface {
    private Integer id;

    private String interfaceName;

    private Integer interfaceType;

    private Byte interfaceDeviceType;

    private Integer belongsDeviceId;

    private Integer ipId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Integer getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Integer interfaceType) {
        this.interfaceType = interfaceType;
    }

    public Byte getInterfaceDeviceType() {
        return interfaceDeviceType;
    }

    public void setInterfaceDeviceType(Byte interfaceDeviceType) {
        this.interfaceDeviceType = interfaceDeviceType;
    }

    public Integer getBelongsDeviceId() {
        return belongsDeviceId;
    }

    public void setBelongsDeviceId(Integer belongsDeviceId) {
        this.belongsDeviceId = belongsDeviceId;
    }

    public Integer getIpId() {
        return ipId;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;
    }
}