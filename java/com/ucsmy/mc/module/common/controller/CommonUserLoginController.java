/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucsmy.mc.common.entity.Permission;
import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.entity.UserRole;
import com.ucsmy.mc.common.mapper.RoleMapper;
import com.ucsmy.mc.common.mapper.UserBasicMapper;
import com.ucsmy.mc.common.service.CommonRolePermissionService;
import com.ucsmy.mc.module.admin.service.AdminDepartmentService;
import com.ucsmy.mc.module.admin.service.AdminPermissionService;
import com.ucsmy.mc.module.common.service.CommonUserLoginService;
import com.ucsmy.mc.util.DateUtil;
import com.ucsmy.mc.util.UUIDUtil;
import com.ucsmy.mc.util.constants.Constants;
import com.ucsmy.mc.util.session.SessionPropertyUtil;


/**
 * Description:
 * Time:2015年12月7日下午4:13:05
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Controller
@RequestMapping("/module/login")
public class CommonUserLoginController {
	
	@Resource(name="commonUserLoginServiceImpl")
	private CommonUserLoginService commonUserLoginService;
	@Resource(name="commonRolePermissionServiceImpl")
	private CommonRolePermissionService commonRolePermissionService;
	@Resource(name="adminDepartmentServiceImpl")
	private AdminDepartmentService adminDepartmentService;
	@Resource(name="adminPermissionServiceImpl")
	private AdminPermissionService adminPermissionService;
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private UserBasicMapper userBasicMapper;

	
	private Logger log = LoggerFactory.getLogger(CommonUserLoginController.class);
	
	@RequestMapping("/userCheckSuccess")
	@ResponseBody
	public Map<String, Object> userCheckSuccess(HttpServletRequest req,HttpServletResponse resp) {
		UserBasic userBasic = (UserBasic)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userBasic.setUsbaCredentialIp(req.getRemoteAddr());
		userBasic.setUsbaCredentialToken(UUIDUtil.creatUUID());
		userBasic.setUsbaCredentialExpired((byte) 1);
		userBasic.setUsbaFailureCount((byte) 0);
		userBasic.setUsbaLastActiveTime(DateUtil.getCurrentTime());
		userBasicMapper.updateByPrimaryKeySelective(userBasic);
		//根据级别高低降序排序
		List<UserRole> userRoles = commonUserLoginService.getUserRoleByUsbaId(userBasic.getUsbaId());
		Map<String, Object> msg = new HashMap<String, Object>();
		
		if(userRoles.size() == 0) {
			msg.put("message", "noAuthority");
		}else{
			
			HttpSession session = req.getSession();
			session.removeAttribute(Constants.USER_ROLE_ID);
			session.removeAttribute(Constants.USER_ROLE_ID_LIST);
			session.removeAttribute(Constants.ROLE_ID);
			session.removeAttribute(Constants.ROLE_ID_LIST);
			//userRoles.get(0)为级别最高的角色
			session.setAttribute(Constants.USER_ROLE_ID, userRoles.get(0).getUsroId());
			session.setAttribute(Constants.ROLE_ID,  userRoles.get(0).getRoleId());
			List<String> usroIdList=new ArrayList<String>();
			List<String> roleIdList=new ArrayList<String>();
			for (UserRole userRole : userRoles) {
				usroIdList.add(userRole.getUsroId());
				roleIdList.add(userRole.getRoleId());
				
			}
			//设置用户角色ID和角色IDList到Session
			session.setAttribute(Constants.USER_ROLE_ID_LIST,usroIdList);
			session.setAttribute(Constants.ROLE_ID_LIST, roleIdList);
			session.setAttribute(Constants.TOKEN_ID,  userBasic.getUsbaCredentialToken());
			session.setAttribute(Constants.USER_ACCOUNT, userBasic.getUsbaAccount());
			session.setAttribute(Constants.USER_NAME, userBasic.getUsbaName());
			if(StringUtils.isNotBlank(userBasic.getUsbaSuperiorId())){
				session.setAttribute(Constants.SUPERIOR,  userBasic.getUsbaSuperiorId());
			}else{
				session.setAttribute(Constants.SUPERIOR,  "");
			}
			session.setAttribute(Constants.USER_DEPAID,  userBasic.getDepaId());
			session.setAttribute(Constants.USER_ID,  userBasic.getUsbaId());
			if(StringUtils.isNotBlank(userRoles.get(0).getUsroRole().getGradId())){
					session.setAttribute(Constants.GRADE_NO,  Integer.parseInt(userRoles.get(0).getGrade().getGradGradeNo().toString()));
			}else{
				session.setAttribute(Constants.GRADE_NO,  "");
			}
			session.setAttribute(Constants.ROLE_NAME,  userRoles.get(0).getUsroRole().getRoleName());
			session.setAttribute(Constants.SESSION_ID, req.getSession().getId());
			
			
			msg.put("message", "success");
			msg.put("address", "module/login/initPage");
		}
		return msg;
	}
	
	@RequestMapping("/initPage")
	public String initPage( Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.ROLE_ID, SessionPropertyUtil.getRoleId());
		UserBasic userBasic = (UserBasic) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<UserRole> userRoles = commonUserLoginService.getUserRoleByUsbaId(userBasic.getUsbaId());
		model.addAttribute("userRoles", userRoles);
		List<Permission> permissions = commonUserLoginService.getPermissionListByRoleId(userRoles);
		//List<Permission> permissions = commonUserLoginService.getPermissionListByRoleId(SessionPropertyUtil.getRoleId());
		model.addAttribute("permissions", permissions);
		
		return "/common/userlogin/init_page";
	}

}
