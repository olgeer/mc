/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.controller;

import com.ucsmy.mc.common.entity.Department;
import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.mapper.UserRoleMapper;
import com.ucsmy.mc.common.pojo.DepartmentTree;
import com.ucsmy.mc.module.activiti.entity.BpmPosition;
import com.ucsmy.mc.module.activiti.mapper.BpmPositionMapper;
import com.ucsmy.mc.module.activiti.mapper.BpmPositionUserMapper;
import com.ucsmy.mc.module.admin.service.AdminDepartmentService;
import com.ucsmy.mc.module.admin.service.AdminManageUserAccountService;
import com.ucsmy.mc.module.admin.service.AdminRoleManageService;
import com.ucsmy.mc.util.DataTableUtil;
import com.ucsmy.mc.util.DepartmentTreeUtil;
import com.ucsmy.mc.util.EncryptMD5Util;
import com.ucsmy.mc.util.PageUtil;
import com.ucsmy.mc.util.constants.Constants;
import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.constants.ErrorCodeConstants;
import com.ucsmy.mc.util.constants.StatusConstants;
import com.ucsmy.mc.util.paging.PageContext;
import com.ucsmy.mc.util.session.SessionPropertyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Description:管理普通用户账号
 * Time:2015年12月4日下午4:18:35
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Controller
@RequestMapping("/admin/manageUserAccount")
public class AdminManageUserAccountController {
	
	/** 用户角色状态为启用 */
	private static final byte OPEN = 2;
	/** 用户角色状态为未启用 */
	private static final byte CLOSE = 0;
	
	@Resource(name="adminManageUserAccountServiceImpl")
	private AdminManageUserAccountService adminManageUserAccountService;
	
	@Resource(name="adminDepartmentServiceImpl")
	private AdminDepartmentService adminDepartmentService;
	
	@Resource(name="adminRoleManageServiceImpl")
	private AdminRoleManageService adminRoleManageService;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private BpmPositionMapper bpmPositionMapper;
	
	@Autowired
	private BpmPositionUserMapper bpmPositionUserMapper;
	
	@RequestMapping("/toUserAccountList")
	public String toUserAccountList(Model model){
		return "admin/manageuseraccount/user_account_list";
	}

	/**
	 * @param request
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param iTotalRows
	 * @param sAction
	 * @param sGroupActionName
	 * @return 角色列表.
	 */
	@RequestMapping("/getUserAccountList")
	@ResponseBody
	public Map<String, Object> getUserAccountDatatable() {
		
        //存储DataTable数据Map
		Map<String, Object> dataTableMap = new HashMap<String, Object>();
		//存储Request数据Map
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		//查询过滤添加Map
		Map<String, Object> filterMap = new HashMap<String, Object>();
		parameterMap.putAll(DataTableUtil.getParameterMap());
		// DataTable操作类型
		String sAction=(String)parameterMap.get(DTConstants.ACTION);
	    if(DTConstants.FILTER_ACTION.equals(sAction)){
	    	
			filterMap.putAll(parameterMap);
		}else if(DTConstants.GROUP_ACTION.equals(sAction)){
			List<String> usbaIds = DataTableUtil.getDataTableListIds(parameterMap);
			
			if (usbaIds != null) {
				String sGroupActionName=(String)parameterMap.get(DTConstants.GROUP_ACTION_NAME);
				if(StringUtils.equals("open", sGroupActionName)){	//启用
					try{
						int num = adminManageUserAccountService.updateUserBasicEnableByUsbaIds(usbaIds, StatusConstants.OPEN);
						dataTableMap.put("sStatus", "OK");
						dataTableMap.put("sMessage", num + "条记录已启用");
					}catch(Exception e){
						e.printStackTrace();
						dataTableMap.put("sMessage", "启用失败，请重试...");
					}
				}else if(StringUtils.equals("close", sGroupActionName)){ //禁用
					try{
						int num = adminManageUserAccountService.updateUserBasicEnableByUsbaIds(usbaIds, StatusConstants.CLOSE);
						dataTableMap.put("sStatus", "OK");
						dataTableMap.put("sMessage", num + "条记录已禁用");
					}catch(Exception e){
						e.printStackTrace();
						dataTableMap.put("sMessage", "禁用失败，请重试...");
					}
				}else if (sGroupActionName.equals("delete")) { //删除
					try{
						int num = adminManageUserAccountService.removeUserBasicByUsbaId(usbaIds);
						dataTableMap.put("sStatus", "OK");
						dataTableMap.put("sMessage", num + "条记录已删除");
					}catch(Exception e){
						e.printStackTrace();
						dataTableMap.put("sMessage", "删除失败，请重试...");
					}
				}
			}
		}
		PageContext pageContext = PageUtil.setPageArgs(parameterMap);// 分页
		List<Map<String, Object>> list = adminManageUserAccountService.getUserAccountList(filterMap); 
		DataTableUtil.setPageArgs(pageContext, dataTableMap, parameterMap);
		dataTableMap.put(DTConstants.DATA, list);
		return dataTableMap;
	}
	
	
	/**
	 * 跳转到用户账号编辑的页面.
	 * @param usbaId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toEditUserAccount/{usbaId}")
	public String toEditUserAccount(@PathVariable("usbaId") String usbaId, Model model){
		Map<String, Object> userDetails = adminManageUserAccountService.getUserDetailsByUsbaId(usbaId);
		List<DepartmentTree> departmentTrees = new ArrayList<DepartmentTree>();
		DepartmentTree departmentTree = DepartmentTreeUtil.getDepartmentTree();	
		departmentTrees.add(0, departmentTree);
		
		List<Map<String, Object>> roleList = adminRoleManageService.getRoleList(Collections.EMPTY_MAP);
		List<String> selectRoleIdsByusbaId = userRoleMapper.selectRoleIdListByUsbaId(usbaId);
		
		
		
		List<BpmPosition> positionList = bpmPositionMapper.selectPositionList(Collections.EMPTY_MAP);
		List<String> userPositionId = bpmPositionUserMapper.selectPositionIdListByUsbaId(usbaId);
		
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("departmentTrees", departmentTrees);
		model.addAttribute("roleList", roleList);
		model.addAttribute("userRoleIds", StringUtils.join(selectRoleIdsByusbaId, ","));
		model.addAttribute("positionList", positionList);
		model.addAttribute("userPositionIds", StringUtils.join(userPositionId, ","));
		
		return "admin/manageuseraccount/edit_user_account";
	}
	
	
	
	/**
	 * 跳转到个人信息.
	 * @param usbaId
	 * @param model
	 * @return
	 */
	@RequestMapping({"/toViewUserAccount"})
	public String toViewUserAccount(String usbaId, Model model) {
		if (StringUtils.isBlank(usbaId)) {
			usbaId = SessionPropertyUtil.getUsbaId();
		}
		UserBasic userDetails = adminManageUserAccountService.selectByPrimaryKey(usbaId);
		model.addAttribute("userDetails", userDetails);
		
		String depaId = userDetails.getDepaId();
		if (StringUtils.isNotBlank(depaId)) {
			Department department = adminDepartmentService.getDepartmentByDepaId(depaId);
			if (department != null) {
				model.addAttribute("department", department);
			}
		}
		
		return "admin/manageuseraccount/view_user_account";
	}
	@RequestMapping({"/toUpdateMailAndPhone"})
	@ResponseBody
	public Map<String, Object> toUpdateMailAndPhone(UserBasic us,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.err.println("测试中-------");
		System.err.println("usbaMail"+us.getUsbaMail());
		
		try{
			UserBasic userBasic=adminManageUserAccountService.selectByPrimaryKey(SessionPropertyUtil.getUsbaId());
			userBasic.setUsbaPhone(us.getUsbaPhone());
			userBasic.setUsbaMail(us.getUsbaMail());
			int success = adminManageUserAccountService.updateUserBasic(userBasic);
			if(success == Constants.SUCCESS){
				map.put("sStatus", true);
				map.put("sMessage", "更新成功");
			}
			else{
				map.put("sStatus", false);
				map.put("sMessage", "更新失败,请重新...");
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("sStatus", false);
			map.put("sMessage", "更新失败,请重新...");
		}
		
		return map;
	}
	
	/**
	 * 跳转到修改.
	 * @param usbaId
	 * @param model
	 * @return
	 */
	@RequestMapping({"/toModifyUserPwd"})
	public String toModifyUserPwd(Model model) {
		String usbaId = SessionPropertyUtil.getUsbaId();
		UserBasic userDetails = adminManageUserAccountService.selectByPrimaryKey(usbaId);
		model.addAttribute("userDetails", userDetails);
		return "admin/manageuseraccount/modify_user_pwd";
	}
	
	
	
	/**
	 * 重置用户的账号密码.
	 * @param usbaId
	 * @return
	 */
	@RequestMapping("/resetUserAccountPassword")
	@ResponseBody
	public Map<String, Object> resetUserAccountPassword(String usbaId){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			int success = adminManageUserAccountService.updateUserAccountPasswordByUsbaId(usbaId, Constants.RESET_PASSWORD);
			if(success == Constants.SUCCESS){
				map.put("sStatus", true);
				map.put("sMessage", "重置密码成功");
			}
			else{
				map.put("sStatus", false);
				map.put("sMessage", "重置密码失败,请重新...");
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("sStatus", false);
			map.put("sMessage", "重置密码失败,请重新...");
		}
		
		return map;
	}
	
	
	/**
	 * 检查用户的原密码.
	 * @param usbaId
	 * @return true/false
	 */
	@RequestMapping("/checkUserAccountPassword")
	@ResponseBody
	public boolean checkUserAccountPassword(String usbaId, String oldPwd) {
		try{
			UserBasic userBasic = adminManageUserAccountService.selectByPrimaryKey(usbaId);
			String oldPwdMd5 = EncryptMD5Util.eccrypt(oldPwd);
			if (userBasic.getPassword().equals(oldPwdMd5)) {
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * 更新用户的账号密码.
	 * @param usbaId
	 * @return
	 */
	@RequestMapping("/updateUserAccountPassword")
	@ResponseBody
	public Map<String, Object> updateUserAccountPassword(String usbaId, String oldPwd, String newPwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			UserBasic userBasic = adminManageUserAccountService.selectByPrimaryKey(usbaId);
			String oldPwdMd5 = EncryptMD5Util.eccrypt(oldPwd);
			if (userBasic.getPassword().equals(oldPwdMd5)) {
				int success = adminManageUserAccountService.updateUserAccountPasswordByUsbaId(usbaId, newPwd);
				
				if(success == Constants.SUCCESS){
					map.put("sStatus", true);
					map.put("sMessage", "修改密码成功");
				}
				else{
					map.put("sStatus", false);
					map.put("sMessage", "修改密码失败,请重新...");
				}
			} else {
				map.put("sStatus", false);
				map.put("sMessage", "原密码不正确,请重新输入...");
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			map.put("sStatus", false);
			map.put("sMessage", "修改密码失败,请重新...");
		}
		
		return map;
	}
	
	
	/**
	 * 更新用户账号信息.
	 * @param usbaId
	 * @param tebaNo
	 * @param tebaName
	 * @param tebaAccountEnable
	 * @param tebaAccountLocked
	 * @param depaId
	 * @param tebaAttribute4
	 * @return
	 */
	@RequestMapping("/updateUserAccount")
	public String updateUserAccount(UserBasic userBasic, String[] userRoleId, String [] userPositionId){		
		int success = adminManageUserAccountService.updateUserBasic(userBasic, userRoleId, userPositionId);
		if(success == Constants.FAILURE){
			return "redirect:/common/error/operateError";
		}
		return "admin/manageuseraccount/user_account_list";
	}
	
	/**
	 * 跳转到添加用户账号的页面.
	 * @param model model.
	 * @return
	 */
	@RequestMapping("/toAddUserAccount")
	public String toAddUserAccount(Model model){
		DepartmentTree departmentTree = DepartmentTreeUtil.getDepartmentTree();
		List<Map<String, Object>> roleList = adminRoleManageService.getRoleList(Collections.EMPTY_MAP);
		List<BpmPosition> positionList = bpmPositionMapper.selectPositionList(Collections.EMPTY_MAP);
		
		model.addAttribute("departmentTree",departmentTree);
		model.addAttribute("roleList", roleList);
		model.addAttribute("positionList", positionList);
		
		return "admin/manageuseraccount/add_user_account";
	}
	
	/**
	 * 添加用户.
	 * @param userBasic
	 * @return
	 */
	@RequestMapping("/addNewUserAccount")
	public String addNewUserAccount(UserBasic userBasic, String[] userRoleId, String [] userPositionId){
		UserBasic UserBasic = adminManageUserAccountService.getUserBasicByUsbaAccount(userBasic.getUsbaAccount());
		if(null != UserBasic){
			return "redirect:/common/error/showErrorFromCode?code="+ErrorCodeConstants.E1_USER_EXIST; //账号已存在
		}
		int success = adminManageUserAccountService.addUserBasic(userBasic, userRoleId, userPositionId);
		if(success == Constants.FAILURE){
			return "redirect:/common/error/operateError";
		}
		
		return "admin/manageuseraccount/user_account_list";
	}
	
	/**
	 * 启用所有的用户账号.
	 * @return
	 */
	@RequestMapping("/activateAllUserAccount")
	@ResponseBody
	public Map<String, Object> activateAllUserAccount(){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			adminManageUserAccountService.updateUserBasicEnableByUsbaIds(null, StatusConstants.OPEN);
			map.put("sStatus", true);
			map.put("sMessage", "启用成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("sStatus", false);
			map.put("sMessage", "操作失败，请重试...");
		}
		
		return map;
	}
	
}
