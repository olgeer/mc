package com.ucsmy.mc.common.exmapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Permission;

public interface ExPermissionMapper {
	
	public List<Map<String, Object>> selectPermissionList(Map<String, Object> map);
	
	public List<Permission> getPermissionRootList();
	
	public List<Permission> getPermissionChindren(String pId);
	
	/**
	 * 查询指定角色的所有根节点模块
	 * @param roleId
	 * @return
	 */
	List<Permission> selectRootPermissionsByRoleId(String roleId);
	
	/**
	 * 查询指定角色指定父节点的所有子节点模块
	 * @param map
	 * @return
	 */
	List<Permission> selectChildrenPermissionsByRoleId(Map<String, Object> map);
	
	
	
	/**
	 * 查询指定角色的所有根节点模块
	 * @param roleId
	 * @return
	 */
	List<Permission> selectPermissionsByRoleIds(List<String> roleIds);
}