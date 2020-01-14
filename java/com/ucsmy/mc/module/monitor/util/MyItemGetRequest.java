package com.ucsmy.mc.module.monitor.util;

import com.zabbix4j.ZabbixApiRequest;
import com.zabbix4j.item.ItemGetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ucs_mawenzhong on 2017/7/26.
 */
public class MyItemGetRequest extends ZabbixApiRequest {

    public MyItemGetRequest() {
        setMethod("item.get");
    }

    private Map<String, Object> params = new HashMap<>();

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
