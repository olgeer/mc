/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.common.exmapper;

import com.ucsmy.mc.common.pojo.DepartmentTree;


/**
 * Description:用于获取整个部门实体.
 * Time:2015年12月4日下午4:24:07
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface ExDepartmentMapper {
	
	/**
	 * @return 整个部门树实体.
	 */
    DepartmentTree selectDepartmentTree();
	
	
}
