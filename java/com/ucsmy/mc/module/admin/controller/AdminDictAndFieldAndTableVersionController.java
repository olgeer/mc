package com.ucsmy.mc.module.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ucsmy.mc.util.UUIDUtil;
/**
 * 
 * @author ucs_xuxiling
 *
 */
@Controller
@RequestMapping("/admin/commonUse")
public class AdminDictAndFieldAndTableVersionController {
	@RequestMapping("/{pageName}/list")
	public String toList(@PathVariable String pageName, Model model, HttpServletRequest request) {
		for (String paramName: request.getParameterMap().keySet()) {
			model.addAttribute(paramName, request.getParameter(paramName));
		}
		model.addAttribute("uuid", UUIDUtil.creatUUID());
		return "/admin/commonUse/" + pageName + "/" + pageName + "_list";
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
	@RequestMapping("/{pageName}/view")
	public String toView(@PathVariable String pageName, Model model, HttpServletRequest request) {
		for (String paramName: request.getParameterMap().keySet()) {
			model.addAttribute(paramName, request.getParameter(paramName));
		}
		model.addAttribute("uuid", UUIDUtil.creatUUID());
		return "/admin/commonUse/" + pageName + "/" + pageName + "_view";
	}
}
