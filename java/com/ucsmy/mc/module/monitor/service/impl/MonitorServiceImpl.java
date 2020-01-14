package com.ucsmy.mc.module.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ucsmy.mc.centerM.bean.Itype;
import com.ucsmy.mc.centerM.bean.Monitor;
import com.ucsmy.mc.centerM.mmapper.McenterMapper;
import com.ucsmy.mc.module.monitor.controller.MonitorController;
import com.ucsmy.mc.module.monitor.entity.MonitorPlatformInfo;
import com.ucsmy.mc.module.monitor.service.MonitorService;
import com.ucsmy.mc.module.monitor.util.CmdbProxyUtil;
import com.ucsmy.mc.module.monitor.util.MonitorTimeConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ucs_mawenzhong on 2017/5/16.
 */
@Service
public class MonitorServiceImpl implements MonitorService {
    private static Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Autowired
    private McenterMapper mcenterMapper;


    private void heandleSearchParams(Map<String, Object> map) {
        String monitorTime = (String)map.get("monitorTime");
        if (StringUtils.isNotBlank(monitorTime)) {
            Date monitordate = getDateByMonitorTime(monitorTime);
            map.put("alarmtime", monitordate);
        } else if (map.get("start") == null && map.get("end") == null) {
            map.put("alarmtime", new Date());
        }
    }


    @Override
    public List<Monitor> getMonitorLists(Map<String, Object> map) {
        heandleSearchParams(map);
        return mcenterMapper.selectMonitorList(map);
    }


    @Override
    public List<Monitor> getMonitorListsNotitleContent(Map<String, Object> map) {
        heandleSearchParams(map);
        return mcenterMapper.selectMonitorListNotitleContent(map);
    }

    @Override
    public JSONArray getMonitorInfo(Map<String, Object> map) {
        JSONArray cmdbPlatform = getCmdbPlatform();

        heandleSearchParams(map);
        List<Monitor> monitors = mcenterMapper.selectMonitorListNotitleContent(map);

        List<Itype> list = mcenterMapper.selectItypeList(map);
        Map<Integer, Integer> typeMap = new HashMap<>();
        for (Itype type: list) {
            typeMap.put(type.getId(), type.getIlevel());
        }

        Map<String, MonitorPlatformInfo> platformMap = new HashMap<>();
        for (int i = 0; i < monitors.size(); i++) {
            Monitor monitor = monitors.get(i);
            String projectId = monitor.getProjectid();
            MonitorPlatformInfo pinfo = platformMap.get(projectId);
            if (pinfo == null) {
                pinfo = new MonitorPlatformInfo();
                pinfo.setPlatformId(projectId);
                platformMap.put(projectId, pinfo);
            }

            Integer itype = monitor.getItype();
            Integer level = typeMap.get(itype);
            pinfo.addMonitor(monitor, level);
        }

        for (int i = 0; i < cmdbPlatform.size(); i++) {
            JSONObject row = cmdbPlatform.getJSONObject(i);
            Integer platformId = row.getInteger("id");

            MonitorPlatformInfo monitorPlatformInfo = platformMap.get(platformId.toString());
            if (monitorPlatformInfo != null) {
                row.put("errorCount", monitorPlatformInfo.getErrorCount());
                row.put("warnCount", monitorPlatformInfo.getWarnCount());
                row.put("lastHappenDate", monitorPlatformInfo.getLastHappenDate());
            }

        }

        return cmdbPlatform;

    }


    private JSONArray getCmdbPlatform() {
        JSONArray data = null;
        try {
            String platformData = CmdbProxyUtil.proxyGet("_tablename=cmdb_platform&_action=get&_pagesize=0");
            JSONObject platforms = JSON.parseObject(platformData);
            JSONObject returndata = platforms.getJSONObject("returndata");
            data = returndata.getJSONArray("data");
        } catch (Exception ex) {
            logger.error("获取cmdb数据失败");
            data = new JSONArray();
        }
        return data;
    }


    private Date getDateByMonitorTime(String monitorTime) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (MonitorTimeConstant.T30M.equals(monitorTime)) {
            calendar.add(Calendar.MINUTE, -30);
        } else if (MonitorTimeConstant.T1H.equals(monitorTime)) {
            calendar.add(Calendar.HOUR_OF_DAY, -1);
        } else if (MonitorTimeConstant.T3H.equals(monitorTime)) {
            calendar.add(Calendar.HOUR_OF_DAY, -3);
        } else if (MonitorTimeConstant.T6H.equals(monitorTime)) {
            calendar.add(Calendar.HOUR_OF_DAY, -6);
        } else if (MonitorTimeConstant.T1D.equals(monitorTime)) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        } else if (MonitorTimeConstant.T3D.equals(monitorTime)) {
            calendar.add(Calendar.DAY_OF_MONTH, -3);
        }

        return calendar.getTime();
    }


    @Override
    public Map<Integer, String> getItypeMap() {
        Map<String, ?> map = Collections.emptyMap();
        List<Itype> list = mcenterMapper.selectItypeList(map);

        Map<Integer, String> typeMap = new HashMap<>();
        for (Itype itype: list) {
            typeMap.put(itype.getId(), itype.getIname());
        }

        return typeMap;
    }


    @Override
    public int updateMonitorIgnore(String ids) {
        return mcenterMapper.updateMonitorState(ids);
    }

}
