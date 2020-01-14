/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ucsmy.mc.common.entity.Grade;
import com.ucsmy.mc.common.exmapper.ExGradeMapper;

/** 
 * @ClassName: GradeUtil 
 * @Description: TODO 级别信息工具类
 * @author: ucs_chenchengteng
 * @date: 2017年2月10日 上午9:15:23
 * @version: V1.0     
 */
@Component
public class GradeUtil {
	
	
	/**
	 * @fieldName: gradeMap
	 * @fieldType: Map<String,Object>
	 * @Description: TODO key :gradeId,value:gradeNo
	 */
	private static Map<String, Object> gradeMap=new HashMap<String, Object>();
	
	@Resource
	ExGradeMapper exGradeMapper;
	/**
	 * 初始化级别Map信息.
	 */
	@PostConstruct
	public void init() {
		reloadGradeMap(exGradeMapper);
	}
	/** 
	 * @Title: reloadGradeMap 
	 * @Description: TODO
	 * @return: void
	 */
	public static void reloadGradeMap(ExGradeMapper exGradeMapper) {
		// TODO Auto-generated method stub
		List<Grade> grades=exGradeMapper.getAllGrade();
		for (Grade grade : grades) {
			gradeMap.put(grade.getGradId(), grade.getGradGradeNo());
		}
	}
	/**
	 * @return the gradeMap
	 */
	public static Map<String, Object> getGradeMap() {
		return gradeMap;
	}
	/**
	 * @param gradeMap the gradeMap to set
	 */
	public static void setGradeMap(Map<String, Object> gradeMap) {
		GradeUtil.gradeMap = gradeMap;
	}
	
}
