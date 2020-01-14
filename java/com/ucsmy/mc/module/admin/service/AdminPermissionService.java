package com.ucsmy.mc.module.admin.service;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Permission;
import com.ucsmy.mc.common.pojo.ComboTree;

/** 
 * @ClassName: AdminPermissionService 
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2016年11月28日 下午3:00:04
 * @version: V1.0     
 */
public interface AdminPermissionService {
	
	/**
	 * 获取列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getPermissionList(Map<String, Object> map);

	/**
	 * 获取
	 * @param id
	 * @return
	 */
	public Permission getPermission(String id);
	
	/**
	 * 保存
	 * @param t
	 * @return
	 */
	public String savePermission(Permission t);
	
	/**
	 * 修改
	 * @param t
	 * @return
	 */
	public int updatePermission(Permission t);
	
	/**
	 * 批量删除
	 * @param moduIds
	 * @return
	 */
	public int deletePermissionByIds(List<String> moduIds);
	
	/**
	 * 批量启用 关闭
	 * @param moduIds
	 * @param moduUseStatus
	 * @return
	 */
	public int updatePermissionByIds(List<String> moduIds, Byte moduUseStatus);
	
	/**
	 * 获取树结构
	 * @param roleId
	 * @return
	 */
	public List<ComboTree> comTree(String roleId);	
	
	/**
	 * 获取选中的所有节点id
	 * @param all
	 * @param moduId
	 * @return
	 */
	public List<String> getSelectedPermId(List<String> all,String moduId);
	
}
