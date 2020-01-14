package com.ucsmy.mc.module.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ucsmy.mc.centerM.bean.Monitor;
import com.ucsmy.mc.module.monitor.entity.MonitorPlatform;
import com.ucsmy.mc.module.monitor.entity.ZbHisData;
import com.ucsmy.mc.module.monitor.mapper.ZbHisDataMapper;
import com.ucsmy.mc.module.monitor.service.MonitorService;
import com.ucsmy.mc.module.monitor.util.CmdbProxyUtil;
import com.ucsmy.mc.module.monitor.util.ZabbixUtil;
import com.ucsmy.mc.module.monitor.util.ZipUtil;
import com.ucsmy.mc.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ucs_mawenzhong on 2017/5/15.
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController {
    private static Logger logger = LoggerFactory.getLogger(MonitorController.class);

    @Autowired
    private MonitorService monitorService;

    @Autowired
    ZbHisDataMapper zbHisDataMapper;


    @RequestMapping("/platform/list")
    public String toPlatformList(Model model, HttpServletRequest request) {
        model.addAttribute("uuid", UUIDUtil.creatUUID());
        return "/monitor/platform_list";
    }

    @RequestMapping("/platform/data")
    @ResponseBody
    public Object getPlatformData(HttpServletRequest request, String monitorTime) {
        JSONArray data = getCmdbData("_tablename=view_cmdb_monitor_platform&_action=get&_pagesize=0");

        Map<String, MonitorPlatform> plaformMap = new HashMap<>();
        List<MonitorPlatform> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject row = data.getJSONObject(i);
            String platformName = row.getString("platform_name");
            Integer platformId = row.getInteger("platform_id");

            MonitorPlatform p = new MonitorPlatform();
            p.setPlatformId(platformId);
            p.setPlatformName(platformName);

            plaformMap.put(platformId.toString(), p);
            list.add(p);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("monitorTime", monitorTime);
        List<Monitor> monitorLists = monitorService.getMonitorLists(map);
        for (Monitor m: monitorLists) {
            String projectId = m.getProjectid();
            MonitorPlatform monitorPlatform = plaformMap.get(projectId);
            if (monitorPlatform != null) {
                monitorPlatform.addMonitor(m);
            }
        }


        Map<Integer, String> itypeMap = monitorService.getItypeMap();

        Map<String, Object> rs = new HashMap<>();
        rs.put("list", list);
        rs.put("itypeMap", itypeMap);

        return rs;
    }


    @RequestMapping("/ignore")
    @ResponseBody
    public Object monitorIgnore(HttpServletRequest request, String ids) {
        monitorService.updateMonitorIgnore(ids);
        return "success";
    }


    @RequestMapping("/platform_host/list")
    public String toPlatformHostList(Model model, String projectid, String monitorTime) {
        model.addAttribute("uuid", UUIDUtil.creatUUID());
        model.addAttribute("projectid", projectid);
        model.addAttribute("monitorTime", monitorTime);
        return "/monitor/platform_host_list";
    }


    @RequestMapping("/platform_host/data")
    @ResponseBody
    public Object getPlatformHostData(HttpServletRequest request, String monitorTime, String projectid) {
        Map<String, Object> map = new HashMap<>();
        map.put("monitorTime", monitorTime);
        map.put("projectid", projectid);
        List<Monitor> monitorLists = monitorService.getMonitorLists(map);

        Map<Integer, String> itypeMap = monitorService.getItypeMap();

        Map<String, Object> rs = new HashMap<>();
        rs.put("list", monitorLists);
        rs.put("itypeMap", itypeMap);

        return rs;
    }


    @RequestMapping("/platform/char_info")
    public String toPlatformCharInfo(Model model, HttpServletRequest request) {
        model.addAttribute("uuid", UUIDUtil.creatUUID());
        return "/monitor/platform_char_info";
    }


    @RequestMapping("/topology/char")
    public String toTopologyChar(Model model, HttpServletRequest request,
            String platformId, String monitorTime, String start, String end) {
        model.addAttribute("uuid", UUIDUtil.creatUUID());
        model.addAttribute("platformId", platformId);
        model.addAttribute("monitorTime", monitorTime);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "/monitor/topology_char";
    }


    @RequestMapping("/topology/char_data")
    @ResponseBody
    public Object toTopologyCharData(Model model, HttpServletRequest request, String platformId,
                                     String monitorTime, String start, String end) {
        if (StringUtils.isBlank(platformId)) {
            platformId = "-1";
        }

        JSONArray nodes = getCmdbData("_tablename=mc_node&_action=get&_pagesize=0&belongs_to_platform_id=" + platformId);
        JSONArray edges = getCmdbData("_tablename=mc_edge&_action=get&_pagesize=0&belongs_to_platform_id=" + platformId);

        Map<String, Object> rs = new HashMap<>();
        rs.put("nodes", nodes);
        rs.put("edges", edges);


        Map<String, Object> map = new HashMap<>();
        map.put("monitorTime", monitorTime);
        map.put("projectid", platformId);
        map.put("start", start);
        map.put("end", end);
        List<Monitor> monitorListsNotitleContent = monitorService.getMonitorListsNotitleContent(map);

        Map<Integer, String> itypeMap = monitorService.getItypeMap();

        rs.put("monitors", monitorListsNotitleContent);
        rs.put("itypeMap", itypeMap);
        return rs;
    }

    @RequestMapping("/topology/char_monitor_data")
    @ResponseBody
    public Object toTopologyCharMonitorData(Model model, HttpServletRequest request,
                                            String platformId, String monitorTime, String start, String end) {
        Map<String, Object> rs = new HashMap<>();

        Map<String, Object> map = new HashMap<>();
        map.put("monitorTime", monitorTime);
        map.put("projectid", platformId);
        map.put("start", start);
        map.put("end", end);
        List<Monitor> monitorListsNotitleContent = monitorService.getMonitorListsNotitleContent(map);

        rs.put("monitors", monitorListsNotitleContent);
        return rs;
    }


    @RequestMapping("/platform/char_info_data")
    @ResponseBody
    public Object getPlatformCharInfoData(HttpServletRequest request, String monitorTime, String start, String end) {
        Map<String, Object> map = new HashMap<>();
        map.put("monitorTime", monitorTime);
        map.put("start", start);
        map.put("end", end);
        return monitorService.getMonitorInfo(map);
    }


    @RequestMapping("/host/history_items")
    @ResponseBody
    public Object getHistoryHostItems(String hostName, String alarmtime, Integer id) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse(alarmtime);

        //String historyItems = ZabbixUtil.getHistoryItems(hostName, alarmtime);
        //if (historyItems != null) return historyItems;

        if (id != null) {
            ZbHisData zbHisData = zbHisDataMapper.selectByPrimaryKey(id);
            if (zbHisData != null) {
                String zbData = zbHisData.getZbData();
                try {
                    return ZipUtil.uncompress(zbData);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return ZabbixUtil.getHistoryItems(hostName, parse);
    }


    private JSONArray getCmdbData(String url) {
        JSONArray data = null;
        try {
            String platformData = CmdbProxyUtil.proxyGet(url);
            JSONObject platforms = JSON.parseObject(platformData);
            JSONObject returndata = platforms.getJSONObject("returndata");
            data = returndata.getJSONArray("data");
        } catch (Exception ex) {
            logger.error("获取cmdb数据失败");
            data = new JSONArray();
        }
        return data;
    }


    @RequestMapping("/host/view")
    public String toMonitorHostView(Model model, String platformId,
                                    String serverIp, String monitorTime,
                                    String hostName, String start, String end) {
        model.addAttribute("uuid", UUIDUtil.creatUUID());
        model.addAttribute("platformId", platformId);
        model.addAttribute("serverIp", serverIp);
        model.addAttribute("monitorTime", monitorTime);
        model.addAttribute("hostName", hostName);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "/monitor/host_view";
    }

    @RequestMapping("/host/view_data")
    @ResponseBody
    public Object toMonitorHostViewData(String platformId, String serverIp, String monitorTime, String start, String end) {
        Map<String, Object> map = new HashMap<>();
        map.put("platformId", platformId);
        map.put("serverIp", serverIp);
        map.put("monitorTime", monitorTime);
        map.put("start", start);
        map.put("end", end);
        map.put("sortCol", "alarmtime");
        map.put("sortDir", "desc");
        map.put("limit", "100");
        List<Monitor> monitorLists = monitorService.getMonitorLists(map);

        Map<String, Object> rs = new HashMap<>();
        rs.put("monitors", monitorLists);

        Map<Integer, String> itypeMap = monitorService.getItypeMap();
        rs.put("itypeMap", itypeMap);
        return rs;
    }


}
