/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.mmapper;

import java.util.List;

import com.ucsmy.mc.common.entity.Dictionary;


/**
 * Description:部门Mapper
 * Time:2015年12月4日下午3:14:13
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface AdminDictionaryMapper {
	
	List<Dictionary> selectDictionaryList();
	
}
