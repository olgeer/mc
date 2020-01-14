package com.ucsmy.mc.module.monitor.service;

import com.alibaba.fastjson.JSONArray;
import com.ucsmy.mc.centerM.bean.Itype;
import com.ucsmy.mc.centerM.bean.Monitor;

import java.util.List;
import java.util.Map;

/**
 * Created by ucs_mawenzhong on 2017/5/16.
 */
public interface MonitorService {
    List<Monitor> getMonitorLists(Map<String, Object> map);

    List<Monitor> getMonitorListsNotitleContent(Map<String, Object> map);

    JSONArray getMonitorInfo(Map<String, Object> map);

    public Map<Integer, String> getItypeMap();

    int updateMonitorIgnore(String ids);
}
