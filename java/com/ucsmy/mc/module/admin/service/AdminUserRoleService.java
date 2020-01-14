/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.service;

import java.util.List;
import java.util.Map;

/** 
 * 用户角色管理
 * @ClassName: AdminUserRoleService 
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2017年3月21日 下午1:52:12
 * @version: V1.0     
 */
public interface AdminUserRoleService {

	/** 
	 * 获取用户角色列表
	 * @Title: getUserRoleList 
	 * @Description: TODO
	 * @param filterMap
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> getUserRoleList(Map<String, Object> filterMap);

	/** 绑定角色和用户之间的关联
	 * @Title: saveUserRole 
	 * @Description: TODO
	 * @param roleId
	 * @param usbaIds
	 * @return: void
	 */
	void saveUserRole(String roleId, String usbaIds);
	
}
