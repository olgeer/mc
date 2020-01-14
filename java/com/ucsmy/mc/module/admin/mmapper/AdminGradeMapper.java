/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.mmapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Grade;

/**
 * Description:级别Mapper
 * Time:2015年12月4日下午3:14:29
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface AdminGradeMapper {
	
	/**根据级别类型查询级别列表
	 * @param map
	 * @return .
	 */
	List<Grade> selectGradeList(Map<String, Object> map);
	/**
	 * @param gradIds
	 * @return 删除成功影响行数.
	 */
	int batchDeleteGrade(List<String> gradIds);
	
}
