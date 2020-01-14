/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.common.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.ucsmy.mc.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucsmy.mc.util.UUIDUtil;
import com.ucsmy.mc.util.freemarker.Dict;
import com.ucsmy.mc.util.freemarker.DictContext;
import com.ucsmy.mc.util.session.SessionPropertyUtil;


@Controller
public class CommonDictionaryController {
	private Logger log = LoggerFactory.getLogger(CommonDictionaryController.class);
	
	
	@RequestMapping("/getDictionary")
	@ResponseBody
	public Object getDictionary(HttpServletRequest req) {
		String keystr = req.getParameter("keys");
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(keystr)) {
			String[] keys = keystr.split(",");
			for (String key: keys) {
				List<Dict> dicts = DictContext.getInstance().getDict(key);
				if (dicts != null) {
					Map<String, String> kvs = new HashMap<>();
					for (Dict dict: dicts) {
						Byte code = dict.getCode();
						if (code == null) {
							kvs.put(dict.getValue(), dict.getValue());
						} else {
							kvs.put(dict.getCode().toString(), dict.getValue());
						}
					}
					map.put(key, kvs);
				}
			}
		}
		
		return map;
	}
	
	
	@RequestMapping("/getCalcVals")
	@ResponseBody
	public Object getCalcVals(HttpServletRequest req) {
		String keystr = req.getParameter("keys");
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(keystr)) {
			String[] keys = keystr.split(",");
			Set<String> keySet = new HashSet<>();
			for (String key: keys) {
				keySet.add(key);
			}
			
			if (keySet.contains("uuid")) {
				map.put("uuid", UUIDUtil.creatUUID());
			}
			
			if (keySet.contains("cur_date")) {
				//map.put("cur_date", System.currentTimeMillis());
				map.put("cur_date", DateUtil.currentTime());
			}
			
			if (keySet.contains("usba_id")) {
				map.put("usba_id", SessionPropertyUtil.getUsbaId());
			}
			
			if (keySet.contains("usba_name")) {
				map.put("usba_name", SessionPropertyUtil.getUserName());
			}
			
		}
		
		return map;
	}
	
	@RequestMapping("/reloadDictionary")
	@ResponseBody
	public Object reloadDictionary(HttpServletRequest req) {
		DictContext.reloadDictionary();
		return "success";
	}
	
}
