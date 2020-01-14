/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucsmy.mc.module.common.service.CommonQueryUserService;
import com.ucsmy.mc.util.DepartmentJsonUtil;
import com.ucsmy.mc.util.DepartmentTreeUtil;

/**
 * Description:查找用户信息，主要用于主送、抄送、收件人等Model框查找
 * Time:2015年12月30日上午10:45:01
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Controller(value = "commonQueryUserController")
@RequestMapping("/common/queryUser")
public class CommonQueryUserController {

	
	private static Logger log = LoggerFactory.getLogger(CommonQueryUserController.class);
	
	
	@Resource(name = "commonQueryUserServiceImp")
	private CommonQueryUserService commonQueryUserService;

	/**
	  * @Description: 获取符合条件的用户信息集合
	  * @param request
	  * @return
	  */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public List<Map<String, Object>> getUserInfo(HttpServletRequest request){
		String nameOrAccount = request.getParameter("nameOrAccount");
		String[] departmentIds = request.getParameterValues("departmentId[]");
		Map<String, Object> map=new HashMap<String, Object>();
		HashSet<String> departmentIdSet = new HashSet<String>();//HashSet没有重复的元素
		if(departmentIds!=null && departmentIds.length>0){
			for (int i = 0; i < departmentIds.length; i++) {
				//获取当前部门所有子部门
				List<String> childDepaIds=DepartmentTreeUtil.getAllChildDepartmentDepaIds(DepartmentTreeUtil.getDepartmentTreeByDepaId(departmentIds[i]));
				departmentIdSet.addAll(childDepaIds);
				departmentIdSet.add(departmentIds[i]);
			}
		}
		List<String> allDepaIds=new ArrayList<String>();
		allDepaIds.addAll(departmentIdSet);
		map.put("nameOrAccount", nameOrAccount);
		map.put("departmentIds", allDepaIds);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list=commonQueryUserService.getUserAccountInfo(map);
		return list;
	}
	
	/**
	  * @Description: 获取部门JSON格式数据
	  * @return
	  */
	@RequestMapping("/getDepartmentJSON")
	@ResponseBody
	public Object getDepartmentJSON(){
		return DepartmentJsonUtil.getDepartmentJson(DepartmentTreeUtil.getDepartmentTree());
	}
}
