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

import com.alibaba.fastjson.JSON;
import com.ucsmy.mc.common.entity.Grade;
import com.ucsmy.mc.common.entity.Role;
import com.ucsmy.mc.common.exmapper.ExRolePermissionMapper;
import com.ucsmy.mc.common.mapper.RoleMapper;
import com.ucsmy.mc.common.pojo.ComboTree;
import com.ucsmy.mc.module.admin.mmapper.AdminRoleManageMapper;
import com.ucsmy.mc.module.admin.service.AdminGradeService;
import com.ucsmy.mc.module.admin.service.AdminPermissionService;
import com.ucsmy.mc.module.admin.service.AdminRoleManageService;
import com.ucsmy.mc.util.DataTableUtil;
import com.ucsmy.mc.util.PageUtil;
import com.ucsmy.mc.util.UUIDUtil;
import com.ucsmy.mc.util.constants.Constants;
import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.paging.PageContext;

/**
 * Description:角色管理
 * Time:2015年12月9日上午10:33:17
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Controller
@RequestMapping("/admin/roleManage")
public class AdminRoleManageController {
	
	@Resource(name="adminRoleManageServiceImpl")
	private AdminRoleManageService adminRoleManageService;
	
	@Resource
	private AdminRoleManageMapper adminRoleManageMapper;
	
	@Resource
	private ExRolePermissionMapper exRolePermissionMapper;
	
	@Resource(name="adminGradeServiceImpl")
	private AdminGradeService adminGradeService;
	
	@Resource(name="adminPermissionServiceImpl")
	private AdminPermissionService adminPermissionService;
	
	
	@Resource(name="roleMapper")
	private RoleMapper roleMapper;
	
	@RequestMapping("toRoleManageList")
	public String toRoleManageList() {
		return "admin/rolemanage/role_manage_list";
	}
	
	@RequestMapping("toRoleManageAddOREdit")
	public String toRoleManageAddOREdit(Model model,HttpServletRequest request){
		//编辑角色ID
		String editRoleId=request.getParameter("roleId");
		Map<String, Object> map=new HashMap<String, Object>();
		List<Grade> gradeList =adminGradeService.getGradeList(map,(byte)1);
		if(StringUtils.isNotBlank(editRoleId)){//获取编辑信息
			Role role=roleMapper.selectByPrimaryKey(editRoleId);
			List<String> permIds=adminRoleManageMapper.selectPermissionsByRoleId(editRoleId);
			model.addAttribute("permIds", JSON.toJSONString(permIds));
			model.addAttribute("editRoleId", editRoleId);
			model.addAttribute("role", role);
		}
		model.addAttribute("uuid",UUIDUtil.creatUUID());
		model.addAttribute("gradeList",gradeList);
		return "admin/rolemanage/role_manage_add_edit";
	}
	
	/**
	 * 保存新建角色权限
	 * @param request
	 * @return
	 */
	@RequestMapping("saveRoleMange")
	public String saveRoleMange(HttpServletRequest request){
		String roleName=request.getParameter("roleName");
		String gradId=request.getParameter("gradId");
		String roleUseStatus=request.getParameter("roleUseStatus");
		String roleType=request.getParameter("roleType");
		String roleDescribe=request.getParameter("roleDescribe");
		String permIdsStr=request.getParameter("permIdsStr");
		Role role=new Role();
		
		int result = Constants.FAILURE;;
		if(StringUtils.isNotBlank(roleName)) {
			role.setRoleName(roleName);
		}
		if(StringUtils.isNotBlank(gradId)) {
			role.setGradId(gradId);
		}
		if(StringUtils.isNotBlank(roleDescribe)) {
			role.setRoleDescribe(roleDescribe);
		}
		if(StringUtils.isNotBlank(roleUseStatus)) {
			role.setRoleUseStatus(Byte.parseByte(roleUseStatus));
		}
		if(StringUtils.isNotBlank(roleType)) {
			role.setRoleType(Byte.parseByte(roleType));
		}
		//插入
		result=adminRoleManageService.insertRoleAndRolePermission(role, permIdsStr);
		
		if(result == Constants.FAILURE){
			return "redirect:/common/error/operateError";
		}else{
			return "admin/rolemanage/role_manage_list";
		}
	}
	
	/**
	 * 编辑角色权限
	 * @param request
	 * @return
	 */
	@RequestMapping("updateRoleMange")
	public String updateRoleMange(HttpServletRequest request){
		String roleId=request.getParameter("roleId");
		String gradId=request.getParameter("gradId");
		String roleName=request.getParameter("roleName");
		String roleUseStatus=request.getParameter("roleUseStatus");
		String roleType=request.getParameter("roleType");
		String roleDescribe=request.getParameter("roleDescribe");
		String permIdsStr=request.getParameter("permIdsStr");
		Role role=new Role();
		int result = Constants.FAILURE;
		if(StringUtils.isNotBlank(roleName)) {
			role.setRoleName(roleName);
		}
		if(StringUtils.isNotBlank(gradId)) {
			role.setGradId(gradId);
		}
		if(StringUtils.isNotBlank(roleDescribe)) {
			role.setRoleDescribe(roleDescribe);
		}
		if(StringUtils.isNotBlank(roleUseStatus)) {
			role.setRoleUseStatus(Byte.parseByte(roleUseStatus));
		}
		if(StringUtils.isNotBlank(roleType)) {
			role.setRoleType(Byte.parseByte(roleType));
		}
		//更新
		if(StringUtils.isNotBlank(roleId)) {
			role.setRoleId(roleId);
		}
		result=adminRoleManageService.updateRoleAndRolePermission(role, permIdsStr);
		
		if(result == Constants.FAILURE){
			return "redirect:/common/error/operateError";
		}else{
			return "admin/rolemanage/role_manage_list";
		}
		
	}
	
	@RequestMapping("/getRoleManageList")
	@ResponseBody
	public Map<String, Object> getRoleManageDatatable() {
		

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
			List<String> roleIds = DataTableUtil.getDataTableListIds(parameterMap);
			
			if (roleIds != null) {
				//操作影响行数
				int result=0;
				//
				String sGroupActionName=(String)parameterMap.get(DTConstants.GROUP_ACTION_NAME);
				
				if (sGroupActionName.equals("delete")) {
					 result=adminRoleManageService.deleteRoleIds(roleIds);
					if(Constants.SUCCESS==result) {
						dataTableMap.put("sStatus", "OK");
						dataTableMap.put("sMessage", roleIds.size() + "条记录已被删除");
					}
				}
			}
		}
		PageContext pageContext = PageUtil.setPageArgs(parameterMap);// 分页
		List<Map<String, Object>> list = adminRoleManageService.getRoleList(filterMap);
		DataTableUtil.setPageArgs(pageContext, dataTableMap, parameterMap);
		dataTableMap.put(DTConstants.DATA, list);
		return dataTableMap;
	}
	
	
	/**
	 * 获取树结构
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/getTreeJson")
	@ResponseBody
	public List<ComboTree> getTreeJson(String roleId){		
		List<ComboTree> tree = adminPermissionService.comTree(roleId);		
		return tree;
	}
}
