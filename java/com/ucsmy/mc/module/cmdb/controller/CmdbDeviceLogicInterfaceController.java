package com.ucsmy.mc.module.cmdb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucsmy.mc.module.cmdb.entity.CmdbDeviceLogicInterface;
import com.ucsmy.mc.module.cmdb.mapper.CmdbDeviceLogicInterfaceMapper;
import com.ucsmy.mc.module.cmdb.service.CmdbDeviceLogicInterfaceService;
import com.ucsmy.mc.util.UUIDUtil;

@Controller
@RequestMapping("/cmdb")
public class CmdbDeviceLogicInterfaceController {
	
	@Resource(name="cmdbDeviceLogicInterfaceServiceImpl")
	private CmdbDeviceLogicInterfaceService cmdbDeviceLogicInterfaceService;
	@Autowired
	private CmdbDeviceLogicInterfaceMapper cmdbDeviceLogicInterfaceMapper;
	
	
	@RequestMapping("/toCmdbDeviceLogicInterfaceAddOREdit")
	public String toCmdbDeviceLogicInterfaceAddOREdit(Model model, HttpServletRequest request, Integer ipId) {
		for (String paramName: request.getParameterMap().keySet()) {
			model.addAttribute(paramName, request.getParameter(paramName));
		}
		
		model.addAttribute("uuid", UUIDUtil.creatUUID());
		CmdbDeviceLogicInterface deviceInterface = cmdbDeviceLogicInterfaceMapper.selectByIpId(ipId);
		
		if (deviceInterface != null) {
			model.addAttribute("id", deviceInterface.getId());
			model.addAttribute("belongsDeviceId", deviceInterface.getBelongsDeviceId());
		}
		
		return "/cmdb/device_logic_interface/add_and_edit";
	}
	
	
	@RequestMapping("/insertOrUpdateCmdbDeviceLogicInterface")
	@ResponseBody
	public Object insertOrUpdateCmdbDeviceLogicInterface(HttpServletRequest request, CmdbDeviceLogicInterface record){
		
		int num = cmdbDeviceLogicInterfaceService.insertDeviceLogicInterface(record);
		
		Map<String, Object> rs = new HashMap<>();
		rs.put("code", 200);
		return rs;
	}
	

}
