/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.service;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Role;

/** 
 * @ClassName: AdminRoleManageService 
 * @Description: TODO 角色模块管理Service
 * @author: ucs_chenchengteng
 * @date: 2016年11月28日 下午2:59:35
 * @version: V1.0     
 */
public interface AdminRoleManageService {
	
	/**
	 * @param map
	 * @return 角色List
	 */
	List<Map<String, Object>> getRoleList(Map<String, Object> map);
	
	/**
	 * @param roleIds
	 * @return 
	 */
	int deleteRoleIds(List<String> roleIds);
	
	/**
	 * 更新Role和RolePermission
	 * @param role
	 * @param permIdsStr
	 * @return
	 */
	int updateRoleAndRolePermission(Role role,String permIdsStr);
	
	/**
	 * 插入Role和RolePermission
	 * @param role
	 * @param permIdsStr
	 * @return
	 */
	int insertRoleAndRolePermission(Role role,String permIdsStr);
}
