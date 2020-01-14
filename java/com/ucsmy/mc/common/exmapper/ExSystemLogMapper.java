/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.common.exmapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.SystemLog;
import com.ucsmy.mc.common.entity.SystemLogExtend;

/** 
 * @ClassName: ExGradeMapper 
 * @Description: TODO 级别扩展
 * @author: ucs_chenchengteng
 * @date: 2017年2月10日 上午9:22:43
 * @version: V1.0     
 */
public interface ExSystemLogMapper {
	
	 /** 
	 * @Title: getAllGrade 
	 * @Description: TODO 获取所有级别信息
	 * @return
	 * @return: List<Grade>
	 */
	public List<SystemLogExtend> getAllSystemLogExtend();

	public SystemLogExtend selectSystemLogExtendBYClassAndMethod(Map<String, Object> maps);

	public void bathInsertSystemLogExtend(List<SystemLogExtend> systemLogExtendList);

	public void bathInsertSystemLog(List<SystemLog> systemLoglist);
}
