package com.ucsmy.mc.centerM.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ucsmy.mc.module.monitor.util.CmdbProxyUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CmdbUtils {


    /**
     * IP判断工具 类
     */
    public static   class IpAdd
    {
        public boolean isIP(String addr)
        {
            if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
            {
                return false;
            }
            /**
             * 判断IP格式和范围
             */
            String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

            Pattern pat = Pattern.compile(rexp);

            Matcher mat = pat.matcher(addr);

            boolean ipAddress = mat.find();

            return ipAddress;
        }
    }

    public static void main(String[] args) throws Exception {

        String json = "{\n" +
                "\t\"returncode\" : \"200\",\n" +
                "\t\"token\" : \"null\",\n" +
                "\t\"responsetime\" : \"2017-05-17 17:37:19\",\n" +
                "\t\"returndata\" : {\n" +
                "\t\t\"data\" : [{\n" +
                "\t\t\t\t\"host_id\" : 2936,\n" +
                "\t\t\t\t\"host_name\" : \"PA0WS00701\",\n" +
                "\t\t\t\t\"company\" : \"网金\",\n" +
                "\t\t\t\t\"project_name\" : \"运维\",\n" +
                "\t\t\t\t\"platform_name\" : \"容灾POC\",\n" +
                "\t\t\t\t\"host_type\" : \"0\",\n" +
                "\t\t\t\t\"logic_area\" : \"WEB\",\n" +
                "\t\t\t\t\"ip\" : \"10.2.110.66,10.10.45.129,10.1.20.12,\",\n" +
                "\t\t\t\t\"public_ip\" : null,\n" +
                "\t\t\t\t\"load_ip\" : null,\n" +
                "\t\t\t\t\"vip_ip\" : null,\n" +
                "\t\t\t\t\"border_ip\" : \"NULL\",\n" +
                "\t\t\t\t\"heartbeat_ip\" : \"NULL\",\n" +
                "\t\t\t\t\"app_type\" : \"6\",\n" +
                "\t\t\t\t\"os_name\" : \"Windows Server 2012 R2\",\n" +
                "\t\t\t\t\"services\" : null,\n" +
                "\t\t\t\t\"update_time\" : \"2017-05-17 18:38:03.0\",\n" +
                "\t\t\t\t\"project_id\" : 39\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"resultsetparameter\" : {\n" +
                "\t\t\t\"commonExample\" : {\n" +
                "\t\t\t\t\"distinct\" : false,\n" +
                "\t\t\t\t\"innoValues\" : [],\n" +
                "\t\t\t\t\"oredCriteria\" : [{\n" +
                "\t\t\t\t\t\t\"allCriteria\" : [{\n" +
                "\t\t\t\t\t\t\t\t\"betweenValue\" : false,\n" +
                "\t\t\t\t\t\t\t\t\"condition\" : \"ip like\",\n" +
                "\t\t\t\t\t\t\t\t\"listValue\" : false,\n" +
                "\t\t\t\t\t\t\t\t\"noValue\" : false,\n" +
                "\t\t\t\t\t\t\t\t\"singleValue\" : true,\n" +
                "\t\t\t\t\t\t\t\t\"value\" : \"10.10.45.129\"\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t],\n" +
                "\t\t\t\t\t\t\"criteria\" : [{\n" +
                "\t\t\t\t\t\t\t\t\"$ref\" : \"$.commonExample.oredCriteria[0].allCriteria[0]\"\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t],\n" +
                "\t\t\t\t\t\t\"valid\" : true\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"pageIndex\" : 1,\n" +
                "\t\t\t\t\"pageSize\" : 0,\n" +
                "\t\t\t\t\"values\" : []\n" +
                "\t\t\t},\n" +
                "\t\t\t\"tableInfo\" : {\n" +
                "\t\t\t\t\"columnList\" : \"host_id,host_name,company,project_name,platform_name,host_type,logic_area,ip,public_ip,load_ip,vip_ip,border_ip,heartbeat_ip,app_type,os_name,services,update_time,project_id\",\n" +
                "\t\t\t\t\"columns\" : [{\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"host_id\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : true,\n" +
                "\t\t\t\t\t\t\"maynull\" : false,\n" +
                "\t\t\t\t\t\t\"name\" : \"host_id\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"host_id\",\n" +
                "\t\t\t\t\t\t\"size\" : 11,\n" +
                "\t\t\t\t\t\t\"type\" : \"int\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"host_name\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"host_name\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"host_name\",\n" +
                "\t\t\t\t\t\t\"size\" : 50,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"company\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"company\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"company\",\n" +
                "\t\t\t\t\t\t\"size\" : 50,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"project_name\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"项目名称\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 0,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"project_name\",\n" +
                "\t\t\t\t\t\t\"size\" : 50,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"platform_name\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"platform_name\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"platform_name\",\n" +
                "\t\t\t\t\t\t\"size\" : 50,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"host_type\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"主机类型\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"host_type\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"host_type\",\n" +
                "\t\t\t\t\t\t\"size\" : 20,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"logic_area\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"logic_area\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"logic_area\",\n" +
                "\t\t\t\t\t\t\"size\" : 50,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"ip\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"ip\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"ip\",\n" +
                "\t\t\t\t\t\t\"size\" : 255,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"public_ip\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"public_ip\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"public_ip\",\n" +
                "\t\t\t\t\t\t\"size\" : 255,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"load_ip\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"负载vip\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 0,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"load_ip\",\n" +
                "\t\t\t\t\t\t\"size\" : 255,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"vip_ip\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"集群vip\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"vip_ip\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"vip_ip\",\n" +
                "\t\t\t\t\t\t\"size\" : 255,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"border_ip\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"边界ip\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 0,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"border_ip\",\n" +
                "\t\t\t\t\t\t\"size\" : 255,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"heartbeat_ip\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"心跳ip\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 0,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"heartbeat_ip\",\n" +
                "\t\t\t\t\t\t\"size\" : 255,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"app_type\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"app_type\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"app_type\",\n" +
                "\t\t\t\t\t\t\"size\" : 50,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"os_name\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"操作系统名称\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 0,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"os_name\",\n" +
                "\t\t\t\t\t\t\"size\" : 255,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"services\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"services\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"services\",\n" +
                "\t\t\t\t\t\t\"size\" : 4096,\n" +
                "\t\t\t\t\t\t\"type\" : \"varchar\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"update_time\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"\",\n" +
                "\t\t\t\t\t\t\"describes\" : \"\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 1,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"update_time\",\n" +
                "\t\t\t\t\t\t\"showname\" : \"update_time\",\n" +
                "\t\t\t\t\t\t\"size\" : 0,\n" +
                "\t\t\t\t\t\t\"type\" : \"timestamp\"\n" +
                "\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\"alias\" : \"\",\n" +
                "\t\t\t\t\t\t\"aliasName\" : \"project_id\",\n" +
                "\t\t\t\t\t\t\"autofill\" : false,\n" +
                "\t\t\t\t\t\t\"comment\" : \"项目ID\",\n" +
                "\t\t\t\t\t\t\"in_use\" : 0,\n" +
                "\t\t\t\t\t\t\"key\" : false,\n" +
                "\t\t\t\t\t\t\"maynull\" : true,\n" +
                "\t\t\t\t\t\t\"name\" : \"project_id\",\n" +
                "\t\t\t\t\t\t\"size\" : 11,\n" +
                "\t\t\t\t\t\t\"type\" : \"int\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"key\" : \"host_id\",\n" +
                "\t\t\t\t\"name\" : \"cmdb_host_information\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"total\" : 1\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";


        JSONObject jsonObject = JSON.parseObject(json);
        if ( ((JSONObject) jsonObject.get("returndata"))!=null)
        {
            JSONArray objects = (JSONArray) ((JSONObject) jsonObject.get("returndata")).get("data");
            if (objects!=null){
                String projectID = ((Map<String, String>) objects.get(11)).get("project_id").toString();
                String projectName = ((Map<String, String>) objects.get(11)).get("project_name").toString();
            }

        }





        String platformData = CmdbProxyUtil.proxyGet("_tablename=cmdb_host_information&_action=get&_pagesize=0&ip=127.0.0.1");
//        System.out.println(platformData);

//        Map<String, String> m = new HashMap<>();
//        m.put("__VIEWSTATE","lO/7gRnTQzPz0SdFP+AEN0ssu09jb4AJTgl8CEaiPT8FWNaPMQKcZZfJ/yaz+pgs5Q01GjHyp7GGEm2C04pv94oO1RuZWlsU5PdoyZEMOYLA1bKdIaqBOCiPY1stir3jYo6XURAOodeYmdTJQmWFSkWLMYArvYf3G3rZcEpqLo+VVhriRFIItR1l2HvEWptTxT0oEQ==");
//        m.put("__VIEWSTATEGENERATOR","C2EE9ABB");
//        m.put("__EVENTVALIDATION","qAJVS+Ds2r3FWVqZAYA6tryJxmU0bNae2N8bEGeccGst8NivpNrJBNNMq/KHBVGGOFHQ/tj7/CLcuFhq7Sup+rhaGSSfcbV/POjE/3FYKJJ0q+u4WPs/ZxSrT9ybtaay7Y5i6LdfpAtIngeslMBTS99DTHrr1gNlcujyOcPj3bn4xdPAv1EOQ9Dep89NObIrmAkj8xEboYKbeEumi3ZfWZvXxm+k0qw+w3uXAqP37YG8+dETiTk01mK7YWw3eXRBy5f1f97HfiFdpz9ocbrjBS9ynuk=");
//        m.put("hfsuccess","false");
//        m.put("hdsafe","0");
//        m.put("Myusername","ucs_shuangjian");
//        m.put("txtpwd","");
//        m.put("hidpwd","ZWUxOGUzNGY3Yzg0NDdiM2RiNTBiYWMxMjg1ZGZmZTk=");
//        m.put("btnLogin","登录");
//        String s = doPost("http://www.ucsmy.net/login.aspx", m,"utf-8");
//        System.out.println(s);

    }

    //    http://blog.csdn.net/rongyongfeikai2/article/details/41659353/
    public static String doPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
