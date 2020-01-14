package com.ucsmy.mc.centerM.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ucsmy.mc.centerM.bean.Itype;
import com.ucsmy.mc.centerM.bean.McModel;
import com.ucsmy.mc.centerM.bean.Monitor;
import com.ucsmy.mc.centerM.mmapper.McenterMapper;
import com.ucsmy.mc.centerM.utils.CmdbUtils;
import com.ucsmy.mc.module.monitor.entity.ZbHisMessage;
import com.ucsmy.mc.module.monitor.util.CmdbProxyUtil;
import com.ucsmy.mc.module.rabbitmq.RabbitmqProducer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/rev")
public class kc_RevController {
    private Logger log = LoggerFactory.getLogger(kc_RevController.class);

    @Resource
    private McenterMapper mcenterMapper;

    @Autowired
    RabbitmqProducer rabbitmqProducer;


    //@RequestMapping(value="/message")
    @RequestMapping(value="/message", method = RequestMethod.POST)
    @ResponseBody
    public String rev_message(McModel mcModel) {
        //http://127.0.0.1/rev/message?host=10.10.45.129&ctime=20150101111111&metric=dev.cpu&description=cpu%E5%8D%A0%E7%94%A8%E7%8E%87
        //log.debug(JSON.toJSONString(mcModel));
        //System.out.println(JSON.toJSONString(mcModel));
        if (StringUtils.isBlank(mcModel.getHost())){
            return "主机名不能为空！";
        }
        if (StringUtils.isBlank(mcModel.getMetric())){
            return "指标不能为空！";
        }

        if (StringUtils.isBlank(mcModel.getCtime())){
            return "时间不能为空！";
        }

        if (StringUtils.isBlank(mcModel.getDescription())){
            return "描述不能为空！";
        }
        //notity= 1 /告警  2取消


        Monitor mt = new Monitor();
        //找到指标相关信息
        List<Map<String, Object>> itypes = mcenterMapper.findByMetric(mcModel.getMetric());
        if (null !=  itypes &&itypes.size()>0)
        {
            String id = itypes.get(0).get("id").toString();
            String iname = itypes.get(0).get("iname").toString();
//            String imark = itypes.get(0).get("imark").toString();
//            String status = itypes.get(0).get("status").toString();
            String ilevel = itypes.get(0).get("ilevel").toString();
            mt.setAlarmtitle(iname);
            mt.setItype(Integer.parseInt(id));  //设置ID
            mt.setAlarmlevel(Integer.parseInt(ilevel));//设置警告等级
        }

        //去cmdb获取主机项目名称和 项目ID

        //判断zabbix传过来的host字段是主机名还是IP
        CmdbUtils.IpAdd ipAdd = new CmdbUtils.IpAdd();
        String jsonBody ="";
        boolean ip = ipAdd.isIP(mcModel.getHost());

        try {
            if (!ip){
                //按主机名查
                jsonBody = CmdbProxyUtil.proxyGet("_tablename=view_monitor_host_info&_action=get&_pagesize=0&host_name="+mcModel.getHost());
            }
            else
            {
                //查IP
                jsonBody = CmdbProxyUtil.proxyGet("_tablename=view_monitor_host_info&_action=get&_pagesize=0&ip="+mcModel.getHost()+",");
            }


            JSONObject jsonObject = JSON.parseObject(jsonBody);
            if ( ((JSONObject) jsonObject.get("returndata"))!=null)
            {
                JSONArray objects = (JSONArray) ((JSONObject) jsonObject.get("returndata")).get("data");
                if (objects!=null){
                    Integer  project_id =  ((Map<String, Integer>) objects.get(0)).get("project_id");
                    String projectName = (((Map<String, String>) objects.get(0)).get("project_name")+"").replace("null","");
                    String platform_name = (((Map<String, String>) objects.get(0)).get("platform_name")+"").replace("null","");
                    String computername = (((Map<String, String>) objects.get(0)).get("host_name")+"").replace("null","");
                    if (null!=project_id)
                    {
                        mt.setProjectid(project_id+"");
                    }

                    if (StringUtils.isNotBlank(projectName))
                    {
                        mt.setProject(projectName);
                    }

                    if (StringUtils.isNotBlank(computername))
                    {
                        mt.setComputername(computername);
                    }
                }

            }

        }catch (Exception e )
        {
            return "CMDB系统中未查询到 "+mcModel.getHost() +"对应的主机！" ;
        }



        mt.setServerip(mcModel.getHost());
        mt.setAlarmcontent(mcModel.getDescription());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            mt.setAlarmtime(formatter.parse(mcModel.getCtime()));
        } catch (ParseException e) {
            mt.setAlarmtime(new Date());
        }
        mt.setProcessingstate(0); //处理状态
        mt.setAlarmtitle(mt.getAlarmtitle() +" : 告警！ ");
        mt.setCreatetime(new Date());
        mt.setSystemId(mcModel.getSystemId());
        mt.setTraceId(mcModel.getTraceId());
        mcenterMapper.insertMonitorBean(mt);


        if (StringUtils.isNotBlank(mt.getComputername())) {
            ZbHisMessage zbHisMessage = new ZbHisMessage();
            zbHisMessage.setHostName(mt.getComputername());
            zbHisMessage.setId(mt.getId());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            zbHisMessage.setAlarmtime(format.format(mt.getAlarmtime()));

            String msg = JSON.toJSONString(zbHisMessage);
            rabbitmqProducer.sendQueue("mc_zb_his_exchange", "mc_zb_his_queue", msg);
        }

        return "success!";
    }

}
