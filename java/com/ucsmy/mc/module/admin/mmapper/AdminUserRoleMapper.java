/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.mmapper;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: AdminUserRoleMapper 
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2017年3月21日 下午1:55:17
 * @version: V1.0     
 */
public interface AdminUserRoleMapper {

	/** 
	 * @Title: selectUserRoleList 
	 * @Description: TODO
	 * @param filterMap
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> selectUserRoleList(Map<String, Object> filterMap);
}
