/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ucsmy.mc.module.admin.service.AdminManageUserAccountService;
import com.ucsmy.mc.module.admin.service.AdminRoleManageService;
import com.ucsmy.mc.module.admin.service.AdminUserRoleService;
import com.ucsmy.mc.util.DataTableUtil;
import com.ucsmy.mc.util.PageUtil;
import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.paging.PageContext;

/** 
 * 用户角色管理
 * @ClassName: AdminUserRoleController 
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2017年3月21日 下午1:49:16
 * @version: V1.0     
 */
@Controller
@RequestMapping("/admin/userRole")
public class AdminUserRoleController {
	
	@Resource(name="adminUserRoleServiceImpl")
	private AdminUserRoleService adminUserRoleService;
	
	@Resource(name="adminRoleManageServiceImpl")
	private AdminRoleManageService adminRoleManageService;
	@Resource(name="adminManageUserAccountServiceImpl")
	private AdminManageUserAccountService adminManageUserAccountService;
	
	@RequestMapping("/toUserRoleList")
	public String toUserAccountList(Model model){
		return "admin/user_role/user_role_list";
	}
	
	/** 
	 * @Title: getUserRoleDatatable 
	 * @Description: TODO
	 * @return
	 * @return: Map<String,Object>
	 */
	@RequestMapping("/getUserRoleDatatable")
	@ResponseBody
	public Map<String, Object> getUserRoleDatatable() {
		
        //存储DataTable数据Map
		Map<String, Object> dataTableMap = new HashMap<String, Object>();
		//存储Request数据Map
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		//查询过滤添加Map
		Map<String, Object> filterMap = new HashMap<String, Object>();
		parameterMap.putAll(DataTableUtil.getParameterMap());
		// DataTable操作类型
		String sAction=(String)parameterMap.get(DTConstants.ACTION);
	    if(DTConstants.FILTER_ACTION.equals(sAction)){
	    	
			filterMap.putAll(parameterMap);
		}else if(DTConstants.GROUP_ACTION.equals(sAction)){
			List<String> usroIds = DataTableUtil.getDataTableListIds(parameterMap);
			
			if (usroIds != null) {
				String sGroupActionName=(String)parameterMap.get(DTConstants.GROUP_ACTION_NAME);
				if(StringUtils.equals("open", sGroupActionName)){
					adminManageUserAccountService.updateUserRoleStatusByUsroIds(usroIds, (byte)2);
				}else if(StringUtils.equals("close", sGroupActionName)){
					adminManageUserAccountService.updateUserRoleStatusByUsroIds(usroIds, (byte)1);
				}else if (sGroupActionName.equals("delete")) {
					adminManageUserAccountService.removeUserRoleByUsroIds(usroIds);
				}
			}
		}
		PageContext pageContext = PageUtil.setPageArgs(parameterMap);// 分页
		List<Map<String, Object>> list = adminUserRoleService.getUserRoleList(filterMap); 
		DataTableUtil.setPageArgs(pageContext, dataTableMap, parameterMap);
		dataTableMap.put(DTConstants.DATA, list);
		return dataTableMap;
	}
	
	/**
	  * @Description: 获取角色JSON格式数据
	  * @return
	  */
	@RequestMapping("/getRoleJSON")
	@ResponseBody
	public Object getRoleJSON(){
		Map<String,Object> filterMap=new HashMap<String,Object>();
		filterMap.put("role_use_status", 1);
		return JSON.toJSON(adminRoleManageService.getRoleList(filterMap));
	}
	
	@RequestMapping("/saveUserRole")
	@ResponseBody
	public String saveUserRole(HttpServletRequest request){
		String roleId=request.getParameter("roleId");
		String usbaIds=request.getParameter("usbaIds");
		if(StringUtils.isNotBlank(roleId)&&StringUtils.isNotBlank(usbaIds)){
			adminUserRoleService.saveUserRole(roleId,usbaIds);
		}else{
			return "false";
		}
		return "success";
	}
}
