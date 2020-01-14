/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.common.mmaper;

import java.util.List;
import java.util.Map;

/**
 * Description:查找用户基本信息
 * Time:2015年12月30日上午10:53:33
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface CommonQueryUserMapper {
	/**
	  * @Description: 
	  * @param map
	  * @return
	  */
	List<Map<String, Object>> selectUserAccountInfo(Map<String,Object> map);
}