/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ucsmy.mc.common.pojo.DepartmentTree;

/**
 * Description:部门信息转换成JSON对象工具类
 * Time:2016年1月5日上午8:28:07
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class DepartmentJsonUtil {
	
	
	/**
	  * @Description: 将部门树转换成以{ "Text": "", "Value": "" }格式
	  * @param departmentTree 需要转换部门树对象
	  * @return
	  */
	public static Object getDepartmentJson(DepartmentTree departmentTree){
		List<Map<String,Object>> list =new ArrayList<Map<String, Object>>();
		getChild(departmentTree,list);
		return JSON.toJSON(list);
	}
	/**
	  * @Description: 递归遍历所有子树
	  * @param departmentTree
	  * @param list
	  */
	private static void getChild(DepartmentTree departmentTree,List<Map<String,Object>> list){
		Map<String,Object> map =new HashMap<String,Object>();
		StringBuilder prefix=new StringBuilder();
		for (int i = 0; i < departmentTree.getGradGradeNo(); i++) {
			prefix.append("&nbsp;&nbsp;&nbsp;");//为了显示树结构，所以采用留白形式体现
		}
		//中间的空格，为了前台的方便字符串截取
		map.put("Text", prefix.toString()+" "+departmentTree.getDepaName());
		map.put("Value", departmentTree.getDepaId());
		List<DepartmentTree> childList=departmentTree.getChildList();
		list.add(map);
		if(childList!=null&&childList.size()>0){
			for (DepartmentTree childDepartmentTree : childList) {
				getChild(childDepartmentTree,list);
			}
		}
	}
}
