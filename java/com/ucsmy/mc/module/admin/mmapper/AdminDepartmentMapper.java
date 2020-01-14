/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.mmapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Department;


/**
 * Description:部门Mapper
 * Time:2015年12月4日下午3:14:13
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface AdminDepartmentMapper {
	
	/**
	 * @param map
	 * @return 满足查询条件的部门.
	 */
	List<Map<String, Object>> selectDepartmentList(Map<String, Object> map);
	
	/**
	 * @return  该年份的、除了最低级的所有部门.
	 */
	List<Map<String, Object>> selectDepartmentMenu();
	
	/**
	 * @param depaIds
	 * @return 删除成功影响行数.
	 */
	int batchDeleteDepartment(List<String> depaIds);
	
	/**
	 * @return 顶级部门.
	 */
	Department selectTopDepartment();
	
	/**
	 * 批量插入部门.
	 * @param map
	 * @return
	 */
	int batchInsertDepartment(Map<String, Object> map);
	
}
