package com.ucsmy.mc.module.monitor.entity;

import com.ucsmy.mc.centerM.bean.Monitor;

import java.util.Date;

/**
 * Created by ucs_mawenzhong on 2017/6/1.
 */
public class MonitorPlatformInfo {
    private String platformId;
    private int errorCount = 0;
    private int warnCount = 0;
    private Date lastHappenDate;

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(int warnCount) {
        this.warnCount = warnCount;
    }

    public Date getLastHappenDate() {
        return lastHappenDate;
    }

    public void setLastHappenDate(Date lastHappenDate) {
        this.lastHappenDate = lastHappenDate;
    }

    public void addMonitor(Monitor m, Integer level) {
        if (level > 0) {
            errorCount ++;
        } else {
            warnCount ++;
        }

        if (lastHappenDate == null) {
            lastHappenDate = m.getAlarmtime();
        } else {
            if (lastHappenDate.compareTo(m.getAlarmtime()) < 0) {
                lastHappenDate = m.getAlarmtime();
            }
        }
    }


}
