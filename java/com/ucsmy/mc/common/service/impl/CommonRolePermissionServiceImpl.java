/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.RolePermission;
import com.ucsmy.mc.common.exmapper.ExRolePermissionMapper;
import com.ucsmy.mc.common.service.CommonRolePermissionService;

/**
 * Description:
 * Time:2015年12月9日上午9:05:25
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Service
public class CommonRolePermissionServiceImpl implements CommonRolePermissionService{

	@Resource
	private ExRolePermissionMapper exRolePermissionMapper;
	
	
	/**
	  * @Description: 获取角色ID对应的权限(模块).
	  * @param map
	  * @return
	  */
	public List<RolePermission> getRolePermissionByRoleId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return exRolePermissionMapper.selectRolePermissionByRoleId(map);
	}

}
