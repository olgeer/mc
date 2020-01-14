/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.Role;
import com.ucsmy.mc.common.entity.RolePermission;
import com.ucsmy.mc.common.mapper.RoleMapper;
import com.ucsmy.mc.module.admin.mmapper.AdminRoleManageMapper;
import com.ucsmy.mc.module.admin.service.AdminPermissionService;
import com.ucsmy.mc.module.admin.service.AdminRoleManageService;
import com.ucsmy.mc.util.UUIDUtil;
import com.ucsmy.mc.util.constants.Constants;

import net.sf.ehcache.CacheManager;


/**
 * Description:角色管理
 * Time:2015年12月4日下午12:20:56
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Service
public class AdminRoleManageServiceImpl implements AdminRoleManageService{

	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private AdminRoleManageMapper adminRoleManageMapper;
	
	@Resource(name="adminPermissionServiceImpl")
	private AdminPermissionService adminPermissionService;
	
	
	public List<Map<String, Object>> getRoleList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return adminRoleManageMapper.selectRoleList(map);
	}

	public int deleteRoleIds(List<String> roleIds) {
	    CacheManager manager = CacheManager.create();     //通过manager可以生成指定名称的Cache对象
	    manager.getCache("roleModelCache").removeAll();
	    manager.getCache("parentModuleCache").removeAll();
		for (String roleId : roleIds) {
			roleMapper.deleteByPrimaryKey(roleId);
		}
		return Constants.SUCCESS;
	}

	/**
	 * 更新Role和RoleModule
	 * @param role
	 * @param moduIds
	 * @return
	 */
	@Override
	public int updateRoleAndRolePermission(Role role, String permIdsStr) {
//	    CacheManager manager = CacheManager.create();     //通过manager可以生成指定名称的Cache对象
//	    manager.getCache("roleModelCache").removeAll();
//	    manager.getCache("parentModuleCache").removeAll();
		roleMapper.updateByPrimaryKeySelective(role);
		adminRoleManageMapper.deleteRolePermissionByRoleId(role.getRoleId());
		List<RolePermission> rolePermissions=new ArrayList<RolePermission>();
		List<String> permIds=new ArrayList<String>();
		//是否有配置模块
		if (StringUtils.isNotBlank(permIdsStr)) {
			//查询获取所有的节点及其父节点id
			for (String permissionId : permIdsStr.split(",")) {
				String permId = permissionId;
				permIds.add(permissionId);
				permIds = adminPermissionService.getSelectedPermId(permIds,permId);
			}
		}
		
		
		for (String permId : permIds) {
			RolePermission rolePermission =new RolePermission();
			rolePermission.setRoleId(role.getRoleId());
			rolePermission.setRopeCreateDate(new Date());
			rolePermission.setPermId(permId);
			rolePermission.setRopeId(UUIDUtil.creatUUID());
			rolePermissions.add(rolePermission);
		}
		if(rolePermissions.size()>0){
			adminRoleManageMapper.insertRolePermissions(rolePermissions);
		}
		return Constants.SUCCESS;
	}

	
	/**
	 * 插入Role和RoleModule
	 * @param role
	 * @param moduIds
	 * @return
	 */
	@Override
	public int insertRoleAndRolePermission(Role role, String permIdsStr) {
//	    CacheManager manager = CacheManager.create();     //通过manager可以生成指定名称的Cache对象
//	    manager.getCache("roleModelCache").removeAll();
//	    manager.getCache("parentModuleCache").removeAll();
		
		role.setRoleCreateDate(new Date());
		role.setRoleId(UUIDUtil.creatUUID());
		roleMapper.insertSelective(role);
		List<RolePermission> rolePermissions=new ArrayList<RolePermission>();
		List<String> permIds=new ArrayList<String>();
		//是否有配置模块
		if (StringUtils.isNotBlank(permIdsStr)) {
			//查询获取所有的节点及其父节点id
			for (String permissionId : permIdsStr.split(",")) {
				String permId = permissionId;
				permIds.add(permissionId);
				permIds = adminPermissionService.getSelectedPermId(permIds,permId);
			}
		}
		for (String permId : permIds) {
			RolePermission rolePermission =new RolePermission();
			rolePermission.setRoleId(role.getRoleId());
			rolePermission.setRopeCreateDate(new Date());
			rolePermission.setPermId(permId);
			rolePermission.setRopeId(UUIDUtil.creatUUID());
			rolePermissions.add(rolePermission);
		}
		if(rolePermissions.size()>0){
			adminRoleManageMapper.insertRolePermissions(rolePermissions);
		}
		return Constants.SUCCESS;
	}


}
