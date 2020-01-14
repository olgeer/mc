package com.ucsmy.mc.module.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ucsmy.mc.module.cmdb.service.CmdbDataService;
import com.ucsmy.mc.module.monitor.util.MonitorTimeConstant;
import com.ucsmy.mc.util.OkHttpUtil;
import com.ucsmy.mc.util.SpringContextUtil;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class LogProxyController {
	private Logger log = LoggerFactory.getLogger(LogProxyController.class);
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public static final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");


	private static OkHttpClient client = OkHttpUtil.getOkHttpClient().newBuilder().build();
	private static String URL;

	static {
		Resource resource = new ClassPathResource("/application.properties");
		try {
			Properties appProp = PropertiesLoaderUtils.loadProperties(resource);
			URL = appProp.getProperty("log.api.url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String post(String url, String params) throws IOException {
		RequestBody body = RequestBody.create(mediaType, params);
		Request request = new Request.Builder()
				.url(URL + url)
				.post(body)
				.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}


	@RequestMapping(value = "/log/api", method = RequestMethod.POST)
	@ResponseBody
	public String proxyPost(HttpServletRequest req, @org.springframework.web.bind.annotation.RequestBody String params)
			throws IOException, ParseException {

		String alarmtime = req.getParameter("alarmtime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = sdf.parse(alarmtime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		calendar.add(Calendar.MINUTE, -10);
		String startTime = sdf.format(calendar.getTime());
		calendar.add(Calendar.MINUTE, 20);
		String endTime = sdf.format(calendar.getTime());
		params = params + "&startTime=" + startTime + "&endTime=" + endTime;

		return post("/uspapi/logapi/getearlywarnloglist", params);
	}
}
