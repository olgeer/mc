package com.ucsmy.mc.module.cmdb.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucsmy.mc.module.cmdb.service.CmdbDataService;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
public class CmdbProxyController {
	private Logger log = LoggerFactory.getLogger(CmdbProxyController.class);
	public static final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
	

	@javax.annotation.Resource(name="cmdbDataServiceImpl")
	private CmdbDataService cmdbDataService;

	// @Value("${cmdb.host}")
	private String HOST;
	private String TOKEN;
	
	{
		Resource resource = new ClassPathResource("/application.properties");
		try {
			Properties appProp = PropertiesLoaderUtils.loadProperties(resource);
			HOST = appProp.getProperty("cmdb.host");
			TOKEN = appProp.getProperty("cmdb.token");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/itil", method = RequestMethod.GET)
	@ResponseBody
	public String proxyGet(HttpServletRequest req) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(HOST + "/itil?" + req.getQueryString())
				.build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	@ResponseBody
	public String proxyGetReport(HttpServletRequest req) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(HOST + "/report?" + req.getQueryString())
				.build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	@RequestMapping(value = "/itil", method = RequestMethod.POST)
	@ResponseBody
	public String proxyPost(HttpServletRequest req, @org.springframework.web.bind.annotation.RequestBody String params)
			throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(mediaType, params);
		Request request = new Request.Builder()
				.url(HOST + "/itil?")
				.post(body)
				.build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	
	@RequestMapping(value = "/getCmdbData")
	@ResponseBody
	public Object getCmdbData(HttpServletRequest req, String hostname, String field_list)
			throws IOException {
		String token = req.getParameter("token");
		if (TOKEN != null && !TOKEN.equals(token)) {
			log.error("token 不正确");
			return null;
		}
		Set<String> fieldList = new HashSet<>(Arrays.asList(field_list.split(",")));
		return cmdbDataService.getCmdbData(hostname, fieldList);
	}
	
}
