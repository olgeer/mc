package com.ucsmy.mc.module.cmdb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ucsmy.mc.module.cmdb.service.CmdbDataService;
import com.ucsmy.mc.util.UUIDUtil;

@Controller
@RequestMapping("/configuration")
public class CmdbConfigurationController {
	
	@Resource(name="cmdbDataServiceImpl")
	private CmdbDataService cmdbDataService;
	
	@RequestMapping
	public String index() {
		return "/cmdb/configuration/index";
	}
	
	@RequestMapping("/{pageName}/list")
	public String toList(@PathVariable String pageName, Model model, HttpServletRequest request) {
		for (String paramName: request.getParameterMap().keySet()) {
			model.addAttribute(paramName, request.getParameter(paramName));
		}
		model.addAttribute("uuid", UUIDUtil.creatUUID());
		return "/cmdb/configuration/" + pageName + "/" + pageName + "_list";
	}
	
	@RequestMapping("/{pageName}/view")
	public String toView(@PathVariable String pageName, Model model, HttpServletRequest request) {
		for (String paramName: request.getParameterMap().keySet()) {
			model.addAttribute(paramName, request.getParameter(paramName));
		}
		model.addAttribute("uuid", UUIDUtil.creatUUID());
		return "/cmdb/configuration/" + pageName + "/" + pageName + "_view";
	}
	
	@RequestMapping("/{pageName}/{id}")
	public String toEdit(@PathVariable String pageName, @PathVariable String id,
			Model model, HttpServletRequest request) {
		Map<String, String> extParams = new HashMap<>();
		for (String paramName: request.getParameterMap().keySet()) {
			model.addAttribute(paramName, request.getParameter(paramName));
			extParams.put(paramName, request.getParameter(paramName));
		}
		model.addAttribute("tableName", pageName);
		model.addAttribute("id", id);
		model.addAttribute("uuid", UUIDUtil.creatUUID());
		model.addAttribute("extParams", JSON.toJSONString(extParams));
		return "/cmdb/configuration/edit";
	}
	
	@RequestMapping("/globCapacity/{pageName}/{id}")
	public String toEditCapacity(@PathVariable String pageName, @PathVariable String id,
			Model model, HttpServletRequest request) {
		for (String paramName: request.getParameterMap().keySet()) {
			model.addAttribute(paramName, request.getParameter(paramName));
		}
		model.addAttribute("tableName", pageName);
		model.addAttribute("id", id);
		model.addAttribute("uuid", UUIDUtil.creatUUID());
		return "/cmdb/configuration/globCapacity/globCapacity_edit";
	}
	
	
	
	@RequestMapping("/ippool/generate")
	@ResponseBody
	public Object ippoolGenerate(String poolId) {
		return cmdbDataService.insertIpByIpPoolId(poolId);
	}
	
	
	@RequestMapping("/vlanpool/generate")
	@ResponseBody
	public Object vlanpoolGenerate(String poolId) {
		return cmdbDataService.insertVlanByVlanPoolId(poolId);
	}

}
