package com.ucsmy.mc.module.cmdb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ucsmy.mc.module.cmdb.service.CmdbDataService;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
public class CmdbDNSController {
	private Logger log = LoggerFactory.getLogger(CmdbDNSController.class);
	public static final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
	

	@javax.annotation.Resource(name="cmdbDataServiceImpl")
	private CmdbDataService cmdbDataService;
	
	
	private String URL;	
	{
		Resource resource = new ClassPathResource("/application.properties");
		try {
			Properties appProp = PropertiesLoaderUtils.loadProperties(resource);
			URL = appProp.getProperty("dns.sync.url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/syncdns")
	@ResponseBody
	public Object syncDNS(HttpServletRequest req, String id) throws IOException {
		Map<String, Object> dnsRecordData = cmdbDataService.getDnsRecordData(id);
		
		Map<String, Object> map = new HashMap<>();
		
		
		map.put("record_id", "");
		map.put("dns_pf", "DNSPOD");
		map.put("act", "Record.Create");
		
		
		map.put("format", "json");
		map.put("login_token", dnsRecordData.get("service_token"));
		map.put("domain", dnsRecordData.get("domain_name"));
		map.put("record_line", dnsRecordData.get("record_line"));
		map.put("record_type", dnsRecordData.get("record_type"));
		map.put("sub_domain", dnsRecordData.get("sub_domain"));
		map.put("value", dnsRecordData.get("value"));
		map.put("ttl", dnsRecordData.get("ttl"));
		
		String params = JSON.toJSONString(map);
		OkHttpClient client = new OkHttpClient();
		
		System.out.println(params);
		
		RequestBody body = RequestBody.create(mediaType, params);
		Request request = new Request.Builder()
				.url(URL)
				.post(body)
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
		
	}
	
	
}
