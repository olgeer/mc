/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.service;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Role;
import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.entity.UserRole;

/**
 * Description:管理普通用户账号Service
 * Time:2015年12月9日上午10:14:36
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface AdminManageUserAccountService {

	/**
	 * 获取当前角色对应部门的所有子孙部门的用户账号列表，如果是管理员角色则可以获取全部
	 * @param map
	 * @return 用户账号列表
	 */
	List<Map<String,Object>> getUserAccountList(Map<String, Object> map);
	
	/**
	 * 通过用户Id,获得该用户的详情.
	 * @param tebaId 用户角色Id.
	 * @return
	 */
	Map<String, Object> getUserDetailsByUsbaId(String usbaId);


	/**
	 * 通过用户账号Id，重置用户账号的密码.
	 * @param tebaId 用户账号Id.
	 * @param resetPassword 重置的密码.
	 * @return 受影响的行数.
	 */
	int updateUserAccountPasswordByUsbaId(String usbaId, String resetPassword);


	/**
	 * 通过用户账号Id,更新用户账号的信息.
	 * @param tebaId 用户Id.
	 * @param tebaNo 用户账号.
	 * @param tebaName 用户名称.
	 * @param tebaAccountEnable 账号是否可用.
	 * @param tebaAccountLocked 账号是否锁定.
	 * @param depaId 部门Id.
	 * @param tebaAttribute4 是否为临时账号
	 * @return 受影响的行数.
	 */
	/** 
	 * @Title: updateUserAccountByUsbaId 
	 * @Description: TODO
	 * @return: int
	 */
	int updateUserAccountByUsbaId(String usbaId, String usbaNo, String usbaName, 
			byte usbaAccountEnable, byte usbaAccountLocked, String depaId, String tebaAttribute4);


	/**
	 * 更新用户实体
	 * @param UserBasic
	 * @return
	 */
	int updateUserBasic(UserBasic userBasic);
	
	/**
	 * 通过用户实体，添加用户实体.
	 * @param UserBasic 用户实体.
	 * @return 受影响的行数.
	 */
	int addUserBasic(UserBasic UserBasic);


	/**
	 * 通过用户账号，获得用户实体.
	 * @param usbaAccount 用户账号.
	 * @return 用户实体.
	 */
	UserBasic getUserBasicByUsbaAccount(String usbaAccount);


	/**
	 * 根据用户Id和可用状态,更新对应用户的可用状态.
	 * @param usbaIds 用户Id列表.
	 * @param accountEnable 更新状态.
	 * @return 受影响的行数.
	 */
	int updateUserBasicEnableByUsbaIds(List<String> usbaIds, byte accountEnable);


	/**
	 * 根据用户Id列表,删除对应的用户实体.
	 * @param usbaIds 用户Id类别.
	 * @return 受影响的行数.
	 */
	int removeUserBasicByUsbaId(List<String> usbaIds);


	/**
	 * 通过用户Id,获得该用户所担当的角色.
	 * @param tebaId 用户Id.
	 * @return 用户角色列表.
	 */
	List<Map<String, Object>> getUserRoleByUsbaId(String usbaId);


	/**
	 * 通过角色类型，获得角色列表.
	 * @param roleTypes 角色类型.
	 * @return 角色列表.
	 */
	List<Role> getRoleByRoleTypes(List<Byte> roleTypes);


	/**
	 * 添加用户角色.
	 * @param userRole 用户角色实体.
	 * @return 受影响的行数.
	 */
	int addUserRole(UserRole userRole);


	/**
	 * 通过用户角色Id列表，更新用户角色的状态.
	 * @param usroIds 用户角色Id列表.
	 * @param status 状态.
	 * @return 受影响的行数.
	 */
	int updateUserRoleStatusByUsroIds(List<String> usroIds, byte status);


	/**
	 * 通过用户角色Id列表，删除对应的状态.
	 * @param usroIds 用户角色Id列表.
	 * @return 受影响的行数.
	 */
	int removeUserRoleByUsroIds(List<String> usroIds);


	/**
	 * 通过用户账号Id列表和可用状态，批量更新用户账号的可用状态.
	 * @param usbaIds 用户账号Id列表.
	 * @param enableStatus 可用状态.
	 * @return 受影响的行数.
	 */
	int batchUpdateUserAccountEnable(List<Integer> usbaIds, byte enableStatus);
	
	
	/**
	 * 通过usbaId获取用户
	 * @param usbaId
	 * @return UserBasic
	 */
	public UserBasic selectByPrimaryKey(String usbaId);
	
	
	/**
	 * 更新用户信息，角色和岗位
	 * @param userBasic
	 * @param userRoleId
	 * @param userPositionId
	 * @return
	 */
	public int updateUserBasic(UserBasic userBasic, String[] userRoleId, String [] userPositionId);

	
	/**
	 * 添加用户，设置用户角色岗位
	 * @param usbaAccount
	 * @param userRoleId
	 * @param userPositionId
	 * @return
	 */
	public int addUserBasic(UserBasic UserBasic, String[] userRoleId, String [] userPositionId);

	
}
