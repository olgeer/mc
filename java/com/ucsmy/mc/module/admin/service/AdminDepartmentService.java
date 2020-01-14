/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.service;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Department;

/** 
 * @ClassName: AdminDepartmentService 
 * @Description: TODO 部门管理Service
 * @author: ucs_chenchengteng
 * @date: 2016年11月28日 下午2:53:00
 * @version: V1.0     
 */
public interface AdminDepartmentService {
	
	/** 
	 * @Title: getDepartmentList 
	 * @Description: TODO 查询条件的部门.
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> getDepartmentList(Map<String, Object> map);
	
	/**
	  * @Description: 除了最低级的所有部门.
	  * @return
	  */
	List<Map<String, Object>> getDepartmentMenu();
	
	/**
	  * @Description: 插入部门
	  * @param department
	  * @return 影响行数
	  */
	int addDepartment(Department department);
	
	/**
	 * @Description:更新部门.
	 * @param department
	 * @return 影响行数.
	 */
	int updateDepartment(Department department);
	
	/**
	 * @param depaId
	 * @return 获取部门实体.
	 */
	Department getDepartmentByDepaId(String depaId);
	
	/**
	 * 批量删除部门.
	 * @param depaIds
	 * @return 删除成功影响行数.
	 */
	int batchDeleteDepartment(List<String> depaIds);
	
	/**
	 * @return 顶级部门.
	 */
	Department getTopDepartment();
	
	/**
	 * 递归查询获取根节点
	 * @param depaId
	 * @return
	 */
	public Department getDepartmentRoot(String depaId);
	
	
}
