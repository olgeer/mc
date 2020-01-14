package com.ucsmy.mc.centerM.bean;

/**
 * Created by jack on 2017/5/15.
 */
public class McModel {

    String host;
    String ctime;
    String metric;
    String description;
    String notity;
    String systemId;
    String traceId;

    public String getNotity() {
        return notity;
    }

    public void setNotity(String notity) {
        this.notity = notity;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
