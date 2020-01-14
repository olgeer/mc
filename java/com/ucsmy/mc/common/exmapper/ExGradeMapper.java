/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.common.exmapper;

import java.util.List;

import com.ucsmy.mc.common.entity.Grade;

/** 
 * @ClassName: ExGradeMapper 
 * @Description: TODO 级别扩展
 * @author: ucs_chenchengteng
 * @date: 2017年2月10日 上午9:22:43
 * @version: V1.0     
 */
public interface ExGradeMapper {
	
	 /** 
	 * @Title: getAllGrade 
	 * @Description: TODO 获取所有级别信息
	 * @return
	 * @return: List<Grade>
	 */
	public List<Grade> getAllGrade();
}
