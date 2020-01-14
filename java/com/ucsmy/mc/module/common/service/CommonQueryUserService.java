/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.common.service;

import java.util.List;
import java.util.Map;

/**
 * Description:查找用户Service
 * Time:2015年12月30日上午10:46:12
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface CommonQueryUserService {
	
	/**
	  * @Description: 根据部门、用户账号或者姓名查询用户信息
	  * @param map
	  * @return
	  */
	List<Map<String, Object>> getUserAccountInfo(Map<String,Object> map);
}
