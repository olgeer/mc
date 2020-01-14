/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.UserRole;
import com.ucsmy.mc.common.mapper.UserRoleMapper;
import com.ucsmy.mc.module.admin.mmapper.AdminUserRoleMapper;
import com.ucsmy.mc.module.admin.service.AdminUserRoleService;
import com.ucsmy.mc.util.UUIDUtil;

/** 
 * @ClassName: AdminUserRoleServiceImpl 
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2017年3月21日 下午1:54:23
 * @version: V1.0     
 */
@Service
public class AdminUserRoleServiceImpl implements AdminUserRoleService {
	@Resource
	private AdminUserRoleMapper adminUserRoleMapper;
	@Resource
	private UserRoleMapper userRoleMapper;
	@Override
	public List<Map<String, Object>> getUserRoleList(Map<String, Object> filterMap) {
		// TODO Auto-generated method stub
		return adminUserRoleMapper.selectUserRoleList(filterMap);
	}
	@Override
	public void saveUserRole(String roleId, String usbaIds) {
		// TODO Auto-generated method stub
		Map<String, Object> filterMap= new HashMap<String, Object>();
		List<String> usbaIdList=Arrays.asList(usbaIds.split(","));
		
		filterMap.put("role_id", roleId);
		//获取该角色已经绑定的用户
		List<Map<String, Object>> existUserRoleList=adminUserRoleMapper.selectUserRoleList(filterMap);
		for (String usbaId : usbaIdList) {
			boolean exist=false;
			for (Map<String, Object> map : existUserRoleList) {
				if(map.get("usba_id").equals(usbaId)){
					exist=true;
					break;
				}
			}
			if(!exist){
				UserRole userRole=new UserRole();
				userRole.setUsroId(UUIDUtil.creatUUID());
				userRole.setRoleId(roleId);
				userRole.setUsbaId(usbaId);
				userRole.setUsroStatus((byte)2);
				userRoleMapper.insertSelective(userRole);
			}
		}
	}
}
