package com.ucsmy.mc.module.monitor.entity;

import com.ucsmy.mc.centerM.bean.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ucs_mawenzhong on 2017/5/16.
 */
public class MonitorPlatform {
    private Integer platformId;
    private String platformName;
    private List<Monitor> monitors;

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public List<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<Monitor> monitors) {
        this.monitors = monitors;
    }

    public void addMonitor(Monitor monitor) {
        if (this.monitors == null) {
            this.monitors =  new ArrayList<>();
        }

        this.monitors.add(monitor);
    }
}
