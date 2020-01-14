/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.common.exmapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.UserRole;


/**
 * Description:用户角色扩展Mapper
 * Time:2015年12月4日下午3:09:07
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface ExUserRoleMapper {
	
    /**
     * 通过用户ID查找用户角色实体（用户基本信息实体，部门实体，角色实体）列表.
     * @param map 封装用户ID(int tebaId)
     * @return 用户角色实体列表
     */
    public List<UserRole> selectUserRoleByUsbaId(Map<String, Object> map);
    
    /**
     * 通过用户角色ID列表查找用户角色实体（用户基本信息实体，部门实体，角色实体）列表.
     * @param usroIds 封装用户角色ID列表
     * @return 用户角色实体列表
     */
    public List<UserRole> selectUserRoleListByUsroIds(List<String> usroIds);
    
    /**
     * 能过主键查询用户角色实体，包括（用户基本信息实体，部门实体，角色实体）
     * @param usroId 用户角色实体的主键
     * @return 返回用户角色实体
     */
    public UserRole selectUserRoleByUsroId(int usroId);
    
	/**
	 * 批量删除用户角色.
	 * @param ids 删除的用户角色Id
	 * @return 返回删除操作影响的行数
	 */
	public int batchDeleteUserRole(List<String> ids);

	/**
	 * @param map 插入userRole表时返回的第一个usroId(int firstUsroId),用户角色List(List<UserRole> userRoles).
	 * @return 影响行数
	 */
	public int batchInsertUserRoles(Map<String, Object> map);
	
	/**
	 * 通过部门和角色Id得到赋予这个角色的所有用户列表
	 * @param map 部门ID(int depaId),角色Id(int roleId)
	 * @return 返回用户角色的部份字段列表
	 */
	public List<Map<String, Object>> selectUserRoleByDepaIdAndRoleId(Map<String, Object> map);
}
