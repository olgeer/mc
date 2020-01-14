/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.common.service;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.RolePermission;

/**
 * Description:角色和模块结合的Service，主要用于登录时角色对应模块的缓存.
 * Time:2015年12月9日上午9:02:20
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface CommonRolePermissionService {
	
	/**
	  * @Description: 获取角色ID对应的权限(模块).
	  * @param map
	  * @return
	  */
	public List<RolePermission> getRolePermissionByRoleId(Map<String, Object> map);
	
}
