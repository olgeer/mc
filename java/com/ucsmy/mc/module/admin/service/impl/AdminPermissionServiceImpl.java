package com.ucsmy.mc.module.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.Permission;
import com.ucsmy.mc.common.exmapper.ExPermissionMapper;
import com.ucsmy.mc.common.mapper.PermissionMapper;
import com.ucsmy.mc.common.pojo.ComboTree;
import com.ucsmy.mc.common.pojo.TreeState;
import com.ucsmy.mc.module.admin.mmapper.AdminRoleManageMapper;
import com.ucsmy.mc.module.admin.service.AdminPermissionService;
import com.ucsmy.mc.util.UUIDUtil;
import com.ucsmy.mc.util.constants.Constants;

@Service
public class AdminPermissionServiceImpl implements AdminPermissionService{

	@Resource
	private PermissionMapper permissionMapper;
	
	@Resource
	private ExPermissionMapper exPermissionMapper;
	
	@Resource
	private AdminRoleManageMapper adminRoleManageMapper;
	
	
	/**
	 * 获取列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getPermissionList(Map<String, Object> map) {
		return exPermissionMapper.selectPermissionList(map);
	}
	
	/**
	 * 获取
	 * @param id
	 * @return
	 */
	@Override
	public Permission getPermission(String id){
		return permissionMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 保存
	 * @param t
	 * @return
	 */
	@Override
	public String savePermission(Permission permission){
		permission.setPermId(UUIDUtil.creatUUID());
		if("".equals(permission.getPermParentId())){
			permission.setPermParentId(null);
		}
		int success = permissionMapper.insertSelective(permission);
		
		if(success >= 0){
			return permission.getPermId();
		}else{
			return null;
		}	
	}
	
	/**
	 * 修改
	 * @param t
	 * @return
	 */
	@Override
	public int updatePermission(Permission permission){
		if("".equals(permission.getPermParentId())){
			permission.setPermParentId(null);
		}
		return permissionMapper.updateByPrimaryKeySelective(permission);
	}
	
	/**
	 * 批量删除
	 * @param moduIds
	 * @return
	 */
	public int deletePermissionByIds(List<String> moduIds) {
//	    CacheManager manager = CacheManager.create();     //通过manager可以生成指定名称的Cache对象
//	    manager.getCache("RolePermissionCache").removeAll();
//	    manager.getCache("PermissionCache").removeAll();
		for (String moduId : moduIds) {
			permissionMapper.deleteByPrimaryKey(moduId);
		}
		return Constants.SUCCESS;
	}

	/**
	 * 批量启用 关闭
	 * @param moduIds
	 * @param moduUseStatus
	 * @return
	 */
	public int updatePermissionByIds(List<String> permIds, Byte moduUseStatus) {
//	    CacheManager manager = CacheManager.create();     //通过manager可以生成指定名称的Cache对象
//	    manager.getCache("roleModelCache").removeAll();
//	    manager.getCache("parentPermissionCache").removeAll();
		Permission permission=new Permission();
		permission.setPermUseStatus(moduUseStatus);
		for (String permId : permIds) {
			permission.setPermId(permId);
			permissionMapper.updateByPrimaryKeySelective(permission);
		}
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 获取树结构
	 * @param roleId
	 * @return
	 */
	@Override
	public List<ComboTree> comTree(String roleId) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		List<Permission> permissions = exPermissionMapper.getPermissionRootList();
		
		List<String> selectPermIds = null;
		if(roleId != null){
			selectPermIds = adminRoleManageMapper.selectPermissionsByRoleId(roleId);
		}
		
		for (Permission permission : permissions) {
			trees.add(tree(selectPermIds, permission, true));
		}
		return trees;
	}
	
	/**
	 * 递归
	 * @param selectModuIds
	 * @param m
	 * @param recursive
	 * @return
	 */
	public ComboTree tree(List<String> selectPermIds, Permission permission, boolean recursive) {
		ComboTree tree = new ComboTree();
		tree.setId(String.valueOf(permission.getPermId()));
		tree.setText(permission.getPermName());
		//获取指定节点的所有子节点集合
		List<Permission> mList=exPermissionMapper.getPermissionChindren(permission.getPermId());
		TreeState state = new TreeState();
		if (mList != null && mList.size() > 0) {			
			state.setOpened(true);					
			tree.setState(state);
			if (recursive) {
				//递归查询子节点
				List<Permission> oList = new ArrayList<Permission>(mList);
				List<ComboTree> children = new ArrayList<ComboTree>();
				for (Permission d : oList) {
					ComboTree t = tree(selectPermIds, d, true);//递归
					children.add(t);
				}
				tree.setChildren(children);
			}
		}else{
			if(selectPermIds != null){
				if(selectPermIds.contains(String.valueOf(permission.getPermId()))){
					state.setSelected(true);
					tree.setState(state);
				}
			}	
		}
		return tree;
	}
	
	
	/**
	 * 获取选中的所有节点id
	 * @param all
	 * @param moduId
	 * @return
	 */
	public List<String> getSelectedPermId(List<String> all,String moduId){
		Permission m = permissionMapper.selectByPrimaryKey(moduId);
		if(m.getPermParentId()!=null){
			if(!all.contains(m.getPermParentId())){
				all.add(m.getPermParentId());
			}
			getSelectedPermId(all,m.getPermParentId());
		}	
		return all;		
	}
	
}
