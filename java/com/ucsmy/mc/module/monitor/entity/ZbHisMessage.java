package com.ucsmy.mc.module.monitor.entity;

/**
 * Created by ucs_mawenzhong on 2017/7/26.
 */
public class ZbHisMessage {

    private Integer id;
    private String hostName;
    private String alarmtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getAlarmtime() {
        return alarmtime;
    }

    public void setAlarmtime(String alarmtime) {
        this.alarmtime = alarmtime;
    }
}
