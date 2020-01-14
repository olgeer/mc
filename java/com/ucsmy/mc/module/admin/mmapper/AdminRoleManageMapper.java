/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.mmapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.RolePermission;

/**
 * Description:角色权限配置Mapper
 * Time:2015年12月4日下午3:15:24
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface AdminRoleManageMapper {

	/**
	  * @Description: 获取所有角色List
	  * @param map
	  * @return
	  */
	List<Map<String, Object>> selectRoleList(Map<String, Object> map);
	
	
	/**
	 * 解除角色ID与模块之间的关系
	 * @param roleId
	 * @return 
	 */
	int deleteRolePermissionByRoleId(String roleId);
	
	/**
	 * 插入新的角色Id与权限关系
	 * @param map
	 */
	void insertRolePermissions(List<RolePermission> rolePermissions);
	
	/**
	 * @param roleId
	 * @return 当前角色Id绑定的ModuId
	 */
	List<String> selectPermissionsByRoleId(String roleId);
	
}
