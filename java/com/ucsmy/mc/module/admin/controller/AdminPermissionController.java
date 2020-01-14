/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
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

import com.ucsmy.mc.common.entity.Permission;
import com.ucsmy.mc.common.pojo.ComboTree;
import com.ucsmy.mc.module.admin.service.AdminPermissionService;
import com.ucsmy.mc.util.DataTableUtil;
import com.ucsmy.mc.util.PageUtil;
import com.ucsmy.mc.util.constants.Constants;
import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.constants.StatusConstants;
import com.ucsmy.mc.util.paging.PageContext;
import com.ucsmy.mc.util.security.CustomInvocationSecurityMetadataSource;

@Controller
@RequestMapping("/admin/permission")
public class AdminPermissionController {
	
	@Resource(name="adminPermissionServiceImpl")
	private AdminPermissionService adminPermissionService;
	
	@Resource(name="customInvocationSecurityMetadataSource")
	CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource;
	
	
	@RequestMapping("toPermissionList")
	public String toPermissionManageList() {
		return "admin/permission/permission_list";
	}
	
	@RequestMapping("/toPermissionAddOREdit")
	public String toPermissionAddOREdit(Model model,HttpServletRequest request){
		String permId=request.getParameter("permId");
		if(StringUtils.isNotBlank(permId)){
			Permission permission = adminPermissionService.getPermission(permId);
			model.addAttribute("permission", permission);
		}
		List<ComboTree> tree = adminPermissionService.comTree(null);
		model.addAttribute("trees", tree);
		return "admin/permission/permission_add_edit";
	}
	
	/**
	 * 新增模块
	 * @param request
	 * @param permission
	 * @return
	 */
	@RequestMapping("savePermission")
	@ResponseBody
	public String savePermission(HttpServletRequest request,Permission permission){
		//新建
		String result=adminPermissionService.savePermission(permission);
		
		if(result!=null){
			return "success";
		}else {
			return "操作失败";
		}
	}
	
	/**
	 * 修改模块
	 * @param request
	 * @param permission
	 * @return
	 */
	@RequestMapping("updatePermission")
	@ResponseBody
	public String updatePermission(HttpServletRequest request,Permission permission){
		int result = Constants.FAILURE;
		if(null!=permission.getPermId()){//更新
			result=adminPermissionService.updatePermission(permission);
		}
		
		if(result==Constants.SUCCESS){
			return "success";
		}else {
			return "操作失败";
		}
	}
	
	/**
	 * 模块列表
	 * @param request
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param iTotalRows
	 * @param sAction
	 * @param sGroupActionName
	 * @return
	 */
	@RequestMapping("/getPermissionDatatable")
	@ResponseBody
	public Map<String, Object> getPermissionDatatable() {
		
		
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
			List<String> moduIds = DataTableUtil.getDataTableListIds(parameterMap);
			
			if (moduIds != null) {
				//操作影响行数
				int result=0;
				//
				String sGroupActionName=(String)parameterMap.get(DTConstants.GROUP_ACTION_NAME);
				
				if (sGroupActionName.equals("delete")) {
					result=adminPermissionService.deletePermissionByIds(moduIds);
					if(Constants.SUCCESS==result) {
						dataTableMap.put("sStatus", "OK");
						dataTableMap.put("sMessage", moduIds.size() + "条记录已被删除");
					}
				}else if(sGroupActionName.equals("open")){//启用
					result=adminPermissionService.updatePermissionByIds(moduIds, StatusConstants.OPEN);
					if(Constants.SUCCESS==result) {
						dataTableMap.put("sStatus", "OK");
						dataTableMap.put("sMessage", moduIds.size() + "条记录已被启用");
					}
				}else {//关闭
					result=adminPermissionService.updatePermissionByIds(moduIds, StatusConstants.CLOSE);
					if(Constants.SUCCESS==result) {
						dataTableMap.put("sStatus", "OK");
						dataTableMap.put("sMessage", moduIds.size() + "条记录已被关闭");
					}
					
				}
			}
		}
		PageContext pageContext = PageUtil.setPageArgs(parameterMap);// 分页
		List<Map<String, Object>> list = adminPermissionService.getPermissionList(filterMap);
		DataTableUtil.setPageArgs(pageContext, dataTableMap, parameterMap);
		dataTableMap.put(DTConstants.DATA, list);
		return dataTableMap;
	}
	
	/** 
	 * 重新加载权限
	 * @Title: reloadResource 
	 * @Description: TODO
	 * @param req
	 * @return
	 * @return: Object
	 */
	@RequestMapping("/reloadResource")
	@ResponseBody
	public Object reloadResource(HttpServletRequest req) {
		customInvocationSecurityMetadataSource.init();
		return "success";
	}
	

}
