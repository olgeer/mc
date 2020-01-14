/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.common.service;

import java.util.List;

import com.ucsmy.mc.common.entity.Permission;
import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.entity.UserRole;

/**
 * Description:用户登录
 * Time:2015年12月7日下午4:03:13
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface CommonUserLoginService {
    /**
     * 通过用户ID查找用户角色实体列表.
     * @param usbaId 用户ID
     * @return 用户角色实体
     */
	public List<UserRole> getUserRoleByUsbaId(String usbaId);
	
	/**
	 * 根据用户角色表的ID查找用户角色实体.
	 * @param usroId 用户角色实体的ID
	 * @return 返回相应的用户角色实体
	 */
	public UserRole getUserRoleByUsroId(String usroId);
	
	/**
	 * 获取用户信息
	 * @param usbaId
	 * @return
	 */
	public UserBasic getUserBasicByUsbaId(String usbaId);
	
	/**
	 * 获取指定角色的所有模块
	 * @param roleId
	 * @return
	 */
	public List<Permission> getPermissionListByRoleId(String roleId);

	
	/**
	 * 获取用户模块权限
	 * @param userRoles
	 * @return
	 */
	List<Permission> getPermissionListByRoleId(List<UserRole> userRoles);
}
