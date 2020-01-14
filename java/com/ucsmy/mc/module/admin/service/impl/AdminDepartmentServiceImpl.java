/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.Department;
import com.ucsmy.mc.common.exmapper.ExDepartmentMapper;
import com.ucsmy.mc.common.mapper.DepartmentMapper;
import com.ucsmy.mc.module.admin.mmapper.AdminDepartmentMapper;
import com.ucsmy.mc.module.admin.service.AdminDepartmentService;
import com.ucsmy.mc.util.DepartmentTreeUtil;
import com.ucsmy.mc.util.UUIDUtil;

/**
 * Description:部门管理
 * Time:2015年12月4日下午12:23:57
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Service
public class AdminDepartmentServiceImpl implements AdminDepartmentService{

	@Resource
	private AdminDepartmentMapper adminDepartmentMapper;
	
	@Resource
	private DepartmentMapper departmentMapper;
	
	@Resource
	private ExDepartmentMapper exDepartmentMapper;
	
	/** 部门树Mapper. */
//	@Resource
//	private CommonDepartmentTreeMapper commonDepartmentTreeMapper;
	
	public List<Map<String, Object>> getDepartmentList(
			Map<String, Object> map) {
		return adminDepartmentMapper.selectDepartmentList(map);
	}
	public List<Map<String, Object>> getDepartmentMenu() {
		return adminDepartmentMapper.selectDepartmentMenu(	);
	}
	public int addDepartment(Department department) {
		department.setDepaId(UUIDUtil.creatUUID());
		int result=departmentMapper.insertSelective(department);
		DepartmentTreeUtil.reloadDepartmentTree(exDepartmentMapper);
		return result;
	}
	public Department getDepartmentByDepaId(String depaId) {
		return departmentMapper.selectByPrimaryKey(depaId);
	}
	public int updateDepartment(Department department) {
		int result=departmentMapper.updateByPrimaryKeySelective(department);
		DepartmentTreeUtil.reloadDepartmentTree(exDepartmentMapper);
		return result;
	}
	public int batchDeleteDepartment(List<String> depaIds) {
		int result= adminDepartmentMapper.batchDeleteDepartment(depaIds);
	//	DepartmentTreeUtil.reloadDepartmentTree(commonDepartmentTreeMapper);
		return result;
	}
	public Department getTopDepartment() {
		return adminDepartmentMapper.selectTopDepartment();
	}
	
	
	
	/**
	 * 递归查询获取根节点
	 * @param depaId
	 * @return
	 */
	public Department getDepartmentRoot(String depaId) {	
		//查询该部门id的父id
		Department depa = departmentMapper.selectByPrimaryKey(depaId);
		
		if (depa != null) {
			if(depa.getDepaParentId()!=null){
				depaId = depa.getDepaParentId();
				depa = getDepartmentRoot(depaId);
			}else{
				return depa;
			}
		}
		return depa;
	}
	
}
