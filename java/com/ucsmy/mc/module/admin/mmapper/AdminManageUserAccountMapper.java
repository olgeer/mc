/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.mmapper;

import java.util.List;
import java.util.Map;


/**
 * Description:
 * Time:2015年12月4日下午4:09:48
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface AdminManageUserAccountMapper {

	/**
	 * 查询用户账号信息.
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectUserAccountList(Map<String, Object> map);

	/**
	 * 批量导入用户基本信息.
	 * @param map
	 */
	int batchInsertUserBasic(Map<String, Object> map);
	
	
	/******************************* HONG begin write ********************************/
	/**
	 * 根据用户Id,查询出该用户详情.
	 * @param tebaId 用户觉得Id.
	 * @return 用户详情.
	 */
	Map<String, Object> selectUserDetailsByUsbaId(String usbaId);

	/**
	 * 根据用户Id列表和账号可用状态,更新用户的账号可用状态.
	 * @param map 用户Id列表(List<Integet> usroIds),可用状态(tebaAccountEnable);
	 * @return 受影响的行数.
	 */
	int updateUserBasicEnableByUsbaIds(Map<String, Object> map);

	/**
	 * 根据用户Id,查询出该用户的所对应的用户角色列表.
	 * @param tebaId 用户Id.
	 * @return 用户角色列表.
	 */
	List<Map<String, Object>> selectUserRoleByUsbaId(String usbaId);

	/**
	 * 根据用户角色Id列表和状态，更新对应用户的状态.
	 * @param map 用户角色Id(List<Integer> usroIds),状态(byte status)
	 * @return 受影响的行数.
	 */
	int updateUserRoleStatusByUsroIds(Map<String, Object> map);

	/**
	 * 根据用户账号Id列表和可用状态，批量更新用户账号的可用状态.
	 * @param map 用户账号Id列表(List<Integer> usroIds),可用状态(byte enableStatus)
	 * @return 受影响的行数.
	 */
	int batchUpdateUserAccountEnableByUsbaIds(Map<String, Object> map);
	
}
