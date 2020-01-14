/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.common.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.Permission;
import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.entity.UserRole;
import com.ucsmy.mc.common.exmapper.ExPermissionMapper;
import com.ucsmy.mc.common.exmapper.ExUserRoleMapper;
import com.ucsmy.mc.common.mapper.UserBasicMapper;
import com.ucsmy.mc.common.mapper.UserRoleMapper;
import com.ucsmy.mc.module.common.service.CommonUserLoginService;

/**
 * Description:
 * Time:2015年12月9日上午9:21:29
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Service
public class CommonUserLoginServiceImpl implements CommonUserLoginService{
	
	@Resource(name="userRoleMapper")
	private UserRoleMapper userRoleMapper;
	
	@Resource(name="exUserRoleMapper")
	private ExUserRoleMapper exUserRoleMapper;
	
	@Resource(name="userBasicMapper")
	private UserBasicMapper userBasicMapper;
	
	@Resource
	private ExPermissionMapper exPermissionMapper;
	
	public List<UserRole> getUserRoleByUsbaId(String usbaId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usbaId", usbaId);
		return exUserRoleMapper.selectUserRoleByUsbaId(map);
	}

	public UserRole getUserRoleByUsroId(String usroId) {
		return userRoleMapper.selectByPrimaryKey(usroId);
	}
	
	/**
	 * 获取用户信息
	 * @param usbaId
	 * @return
	 */
	public UserBasic getUserBasicByUsbaId(String usbaId){
		return userBasicMapper.selectByPrimaryKey(usbaId);
	}

	/* (non Javadoc) 
	 * @Title: getPermissionListByRoleId
	 * @Description: TODO
	 * @param roleId
	 * @return 
	 * @see com.ucsmy.mc.module.common.service.CommonUserLoginService#getPermissionListByRoleId(java.lang.String)
	 */
	@Override
	public List<Permission> getPermissionListByRoleId(String roleId) {
		List<Permission> allList=new ArrayList<Permission>();	
		List<Permission> roots = exPermissionMapper.selectRootPermissionsByRoleId(roleId);
		for(Permission root:roots){
			root = getPermissionChildren(roleId,root,true);
			allList.add(root);
		}
		return allList;
	}

	/**
	 * 递归
	 * @param roleId
	 * @param m
	 * @param recursive
	 * @return
	 */
	public Permission getPermissionChildren(String roleId, Permission m, boolean recursive) {
		//获取指定节点的所有子节点集合
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("pId", m.getPermId());
		List<Permission> mList=exPermissionMapper.selectChildrenPermissionsByRoleId(map);
		if (mList != null && mList.size() > 0) {						
			if (recursive) {
				//递归查询子节点
				List<Permission> children = new ArrayList<Permission>();
				for (Permission d : mList) {
					Permission t = getPermissionChildren(roleId, d, true);
					children.add(t);
				}
				m.setChildren(children);
			}
		}
		return m;
	}
	
	
	@Override
	public List<Permission> getPermissionListByRoleId(List<UserRole> userRoles) {
		if (userRoles.size() <= 0) {
			return Collections.emptyList();
		}
			
		List<String> roleIds = new ArrayList<>();
		for (UserRole ur: userRoles) {
			roleIds.add(ur.getRoleId());
		}
		
		List<Permission> permissions = exPermissionMapper.selectPermissionsByRoleIds(roleIds);
		Map<String, Permission> map = new HashMap<>(permissions.size());
		
		List<Permission> rootPermissions = new ArrayList<>(); 
		for (Permission p: permissions) {
			map.put(p.getPermId(), p);
			
			if (StringUtils.isBlank(p.getPermParentId())) {
				rootPermissions.add(p);
			}
		}
		
		for (Permission p: permissions) {
			String pId = p.getPermParentId();
			Permission pPermission = map.get(pId);
			if (pPermission != null) {
				List<Permission> childPermissionList = pPermission.getChildren();
				if (childPermissionList == null) {
					childPermissionList = new ArrayList<>();
					pPermission.setChildren(childPermissionList);
				}
				
				childPermissionList.add(p);
			}
			
		}
		
		
		return rootPermissions;
	}
}
