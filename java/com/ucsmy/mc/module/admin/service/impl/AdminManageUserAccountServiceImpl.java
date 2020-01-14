/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.Role;
import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.entity.UserRole;
import com.ucsmy.mc.common.exmapper.ExRoleMapper;
import com.ucsmy.mc.common.exmapper.ExUserBasicMapper;
import com.ucsmy.mc.common.exmapper.ExUserRoleMapper;
import com.ucsmy.mc.common.mapper.UserBasicMapper;
import com.ucsmy.mc.common.mapper.UserRoleMapper;
import com.ucsmy.mc.module.activiti.entity.BpmPositionUser;
import com.ucsmy.mc.module.activiti.mapper.BpmPositionUserMapper;
import com.ucsmy.mc.module.admin.mmapper.AdminManageUserAccountMapper;
import com.ucsmy.mc.module.admin.service.AdminManageUserAccountService;
import com.ucsmy.mc.util.DateUtil;
import com.ucsmy.mc.util.EncryptMD5Util;
import com.ucsmy.mc.util.UUIDUtil;
import com.ucsmy.mc.util.constants.Constants;

/**
 * Description:
 * Time:2015年12月9日上午10:21:05
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Service
public class AdminManageUserAccountServiceImpl implements AdminManageUserAccountService{

	@Resource
	private AdminManageUserAccountMapper adminManageUserAccountMapper;
	
	@Resource
	private UserBasicMapper userBasicMapper;
	
	@Resource
	private ExUserBasicMapper exUserBasicMapper;
	
	@Resource
	private ExRoleMapper exRoleMapper;
	
	@Resource
	private UserRoleMapper userRoleMapper;
	
	@Resource
	private ExUserRoleMapper exUserRoleMapper;
	
	@Autowired
	private BpmPositionUserMapper bpmPositionUserMapper;
	
	
	public List<Map<String, Object>> getUserAccountList(
			Map<String, Object> map) {
		return adminManageUserAccountMapper.selectUserAccountList(map);
	}
	
	public Map<String, Object> getUserDetailsByUsbaId(String usbaId) {
		Map<String, Object> map = adminManageUserAccountMapper.selectUserDetailsByUsbaId(usbaId);
		return map;
	}
	
	public int updateUserAccountPasswordByUsbaId(String usbaId, String resetPassword) {
		UserBasic UserBasic = new UserBasic();
		UserBasic.setUsbaId(usbaId);
		UserBasic.setUsbaPassword(EncryptMD5Util.eccrypt(resetPassword));
		int success = userBasicMapper.updateByPrimaryKeySelective(UserBasic);
		
		return success;
	}

	public int updateUserAccountByUsbaId(String usbaId, String usbaNo, String tebaName, 
			byte tebaAccountEnable, byte tebaAccountLocked, String depaId, String tebaAttribute4) {
		UserBasic UserBasic = new UserBasic();
		UserBasic.setUsbaId(usbaId);
		UserBasic.setUsbaAccount(usbaNo);
		UserBasic.setUsbaName(tebaName);
		UserBasic.setUsbaAccountEnable(tebaAccountEnable);
		UserBasic.setUsbaAccountLocked(tebaAccountLocked);
		UserBasic.setDepaId(depaId);
		int success = userBasicMapper.updateByPrimaryKeySelective(UserBasic);
		return success;
	}
	
	public int updateUserBasic(UserBasic userBasic) {
		int success = userBasicMapper.updateByPrimaryKeySelective(userBasic);
		return success;
	}
	
	
	public int updateUserBasic(UserBasic userBasic, String[] userRoleId, String [] userPositionId) {
		int success = userBasicMapper.updateByPrimaryKeySelective(userBasic);
		
		String usbaId = userBasic.getUsbaId();
		
		/**
		 * 更新角色
		 */
		if (userRoleId == null || userRoleId.length == 0) {
			userRoleMapper.deleteByUsbaId(usbaId);
		} else {
			List<String> beforeUserRoleIds = userRoleMapper.selectRoleIdListByUsbaId(usbaId);
			List<String> currentUserRoleId = Arrays.asList(userRoleId);
			
			List<String> deleteIds = new ArrayList<String>(beforeUserRoleIds);
			deleteIds.removeAll(currentUserRoleId);
			if (deleteIds.size() > 0) {
				userRoleMapper.batchDeleteByUsbaIdAndRoleId(usbaId, deleteIds);
			}
			
			List<String> addIds = new ArrayList<String>(currentUserRoleId);
			addIds.removeAll(beforeUserRoleIds);
			if (addIds.size() > 0) {
				List<UserRole> records = new ArrayList<UserRole>();
				for (String roleId: addIds) {
					UserRole userRole = new UserRole();
					userRole.setUsroId(UUIDUtil.creatUUID());
					userRole.setUsbaId(usbaId);
					userRole.setRoleId(roleId);
					userRole.setUsroStatus((byte) 2);
					userRole.setUsroCreateDate(new Date());
					records.add(userRole);
				}
				userRoleMapper.batchinsert(records);
			}
		}
		
		
		
		/**
		 * 更新岗位
		 */
		if (userPositionId == null || userPositionId.length == 0) {
			bpmPositionUserMapper.deleteByUsbaId(usbaId);
		} else {
			List<String> beforeUserPositionIds = bpmPositionUserMapper.selectPositionIdListByUsbaId(usbaId);
			List<String> currentUserPositionIds = Arrays.asList(userPositionId);
			
			List<String> deleteIds = new ArrayList<String>(beforeUserPositionIds);
			deleteIds.removeAll(currentUserPositionIds);
			if (deleteIds.size() > 0) {
				bpmPositionUserMapper.deleteByUsbaIdAndBppoId(usbaId, deleteIds);
			}
			
			List<String> addIds = new ArrayList<String>(currentUserPositionIds);
			addIds.removeAll(beforeUserPositionIds);
			if (addIds.size() > 0) {
				List<BpmPositionUser> records = new ArrayList<BpmPositionUser>();
				for (String bppoId: addIds) {
					BpmPositionUser bpmPositionUser = new BpmPositionUser();
					bpmPositionUser.setBpusId(UUIDUtil.creatUUID());
					bpmPositionUser.setUsbaId(usbaId);
					bpmPositionUser.setBppoId(bppoId);
					records.add(bpmPositionUser);
				}
				bpmPositionUserMapper.batchInsert(records);
			}
		}
		
		return success;
	}
	
	public int addUserBasic(UserBasic UserBasic) {
		UserBasic.setUsbaPassword(EncryptMD5Util.eccrypt(Constants.RESET_PASSWORD));
		UserBasic.setUsbaCreateDate(DateUtil.getCurrentTime());
		UserBasic.setUsbaId(UUIDUtil.creatUUID());
		int success = userBasicMapper.insertSelective(UserBasic);
		return success;
	}
	
	public int addUserBasic(UserBasic UserBasic, String[] userRoleId, String [] userPositionId) {
		UserBasic.setUsbaPassword(EncryptMD5Util.eccrypt(Constants.RESET_PASSWORD));
		UserBasic.setUsbaCreateDate(DateUtil.getCurrentTime());
		UserBasic.setUsbaId(UUIDUtil.creatUUID());
		int success = userBasicMapper.insertSelective(UserBasic);
		
		String usbaId = UserBasic.getUsbaId();
		
		/**
		 * 添加角色
		 */
		if (userRoleId != null && userRoleId.length > 0) {
			List<UserRole> records = new ArrayList<UserRole>();
			for (String roleId: userRoleId) {
				UserRole userRole = new UserRole();
				userRole.setUsroId(UUIDUtil.creatUUID());
				userRole.setUsbaId(usbaId);
				userRole.setRoleId(roleId);
				userRole.setUsroStatus((byte) 2);
				userRole.setUsroCreateDate(new Date());
				records.add(userRole);
			}
			userRoleMapper.batchinsert(records);
		}
		
		
		/**
		 * 添加岗位
		 */
		if (userPositionId != null && userPositionId.length > 0) {
			List<BpmPositionUser> records = new ArrayList<BpmPositionUser>();
			for (String bppoId: userPositionId) {
				BpmPositionUser bpmPositionUser = new BpmPositionUser();
				bpmPositionUser.setBpusId(UUIDUtil.creatUUID());
				bpmPositionUser.setUsbaId(usbaId);
				bpmPositionUser.setBppoId(bppoId);
				records.add(bpmPositionUser);
			}
			bpmPositionUserMapper.batchInsert(records);
		}
		
		
		return success;
	}
	
	public UserBasic selectByPrimaryKey(String usbaId) {
		return userBasicMapper.selectByPrimaryKey(usbaId);
	}

	public UserBasic getUserBasicByUserName(String username) {
		UserBasic UserBasic = exUserBasicMapper.selectUserBasicByUsbaAccount(username);
		return UserBasic;
	}

	public int updateUserBasicEnableByUsbaIds(List<String> usbaIds,
			byte accountEnable) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usbaIds", usbaIds);
		map.put("usbaAccountEnable", accountEnable);
		int num = adminManageUserAccountMapper.updateUserBasicEnableByUsbaIds(map);
		return num;
	}

	public int removeUserBasicByUsbaId(List<String> usbaIds) {
		int num = exUserBasicMapper.batchDeleteUserBasicByUsbaIds(usbaIds);
		return num;
	}

	public List<Map<String, Object>> getUserRoleByUsbaId(String usbaId) {
		List<Map<String, Object>> list = adminManageUserAccountMapper.selectUserRoleByUsbaId(usbaId);
		return list;
	}

	public List<Role> getRoleByRoleTypes(List<Byte> roleTypes) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(roleTypes.size()>0){
			map.put("roleTypes", roleTypes);
		}
		List<Role> roles = exRoleMapper.selectRolesByRoleTypes(map);
		return roles;
	}

	public int addUserRole(UserRole userRole) {
		userRole.setUsroId(UUIDUtil.creatUUID());
		int num = userRoleMapper.insertSelective(userRole);
		return num;
	}

	public int updateUserRoleStatusByUsroIds(List<String> usroIds, byte status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usroIds", usroIds);
		map.put("status", status);
		return adminManageUserAccountMapper.updateUserRoleStatusByUsroIds(map);
	}

	public int removeUserRoleByUsroIds(List<String> teroIds) {
		int num = exUserRoleMapper.batchDeleteUserRole(teroIds);
		return num;
	}

	public int batchUpdateUserAccountEnable(List<Integer> usbaIds,
			byte enableStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usbaIds", usbaIds);
		map.put("enableStatus", enableStatus);
		int success = adminManageUserAccountMapper.batchUpdateUserAccountEnableByUsbaIds(map);
		return success;
	}

	@Override
	public UserBasic getUserBasicByUsbaAccount(String usbaAccount) {
		UserBasic UserBasic = exUserBasicMapper.selectUserBasicByUsbaAccount(usbaAccount);
		return UserBasic;
	}
	
}
