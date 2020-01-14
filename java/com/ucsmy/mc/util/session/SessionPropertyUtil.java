/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.util.session;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ucsmy.mc.util.DepartmentTreeUtil;
import com.ucsmy.mc.util.constants.Constants;

/**
 * Description:获取session属性
 * Time:2015年12月2日下午6:03:39
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class SessionPropertyUtil {
	
	/**
	  * @Description: 获取普通用户角色Id
	  * @return usroId
	  */
	public static String getUsroId() {
		return getSessionValueByKey(Constants.USER_ROLE_ID);
	}
	
	/**
	  * @Description: 获取管理员角色Id
	  * @return adroId
	  */
	public static String getAdroId() {
		return getSessionValueByKey(Constants.ADMIN_ROLE_ID);
	}
	
	/**
	  * @Description: 获取sessionId
	  * @return 
	  */
	public static String getSessionId() {
		return getSessionValueByKey(Constants.SESSION_ID);
	}
	
	/**
	  * @Description: 获取角色Id
	  * @return
	  */
	public static String getRoleId() {
		return getSessionValueByKey(Constants.ROLE_ID);
		
	}
	
	/**
	  * @Description: 检查是否有登录用户
	  * @return YES返回True，NO返回False
	  */
	public static boolean isLogin() {
		if(getAdroId() != null || getUsroId() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @Description:获取Session中的管理员账号
	 * @return
	 */
	public static String getAdmiAccount(){
		return getSessionValueByKey(Constants.ADMI_ACCOUNT);
	}
	
	/**
	 * @Description:获取Session中的普通用户账号
	 * @return
	 */
	public static String getUserAccount(){
		return getSessionValueByKey(Constants.USER_ACCOUNT);
	}
	
	/**
	 * @Description:获取Session中的普通用户部门ID
	 * @return
	 */
	public static String getUserDepaId(){
		return getSessionValueByKey(Constants.USER_DEPAID);
	}
	
	/**
	  * @Description: 获取用户部门名称
	  * @return
	  */
	public static String getUserDepaName(){
		return DepartmentTreeUtil.getDepartmentTreeByDepaId(getUserDepaId()).getDepaName();
	}
	/**
	 * @Description:获取Session中的普通用户名称（非账号）
	 * @return
	 */
	public static String getUserName(){
		return getSessionValueByKey(Constants.USER_NAME);
	}

	/**
	 * @Description:获取Session中的普通用户ID
	 * @return
	 */
	public static String getUsbaId(){
		return getSessionValueByKey(Constants.USER_ID);
	}

	/**@Description：获取角色名称
	 * @return
	 */
	public static String getRoleName(){
		return getSessionValueByKey(Constants.ROLE_NAME);
	}
	
	/**
	 * Description:获取用户访问IP
	 * @return
	 */
	public static String getIP(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = request.getHeader("X-Forwarded-For");
		//String ip = request.getHeader("X-Real-IP");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (StringUtils.isNotBlank(ip)) {
			ip = ip.split(",")[0];
		}
		return ip;
		//return request.getRemoteAddr();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<String> getRoleIdList(){
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Object roleIdList = req.getSession().getAttribute(Constants.ROLE_ID_LIST);
		if(roleIdList!=null){
			return (List<String>)roleIdList;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getUsroIdList(){
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Object usroIdList = req.getSession().getAttribute(Constants.USER_ROLE_ID_LIST);
		if(usroIdList!=null){
			return (List<String>)usroIdList;
		}
		return null;
	}
	
	private static String getSessionValueByKey(String key){
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String value = String.valueOf(req.getSession().getAttribute(key));
		if(value == "null") {
			return null;
		} else {
			return value;
		}
	}
	
	/**
	 * 获取用户的机构id
	 * @return
	 */
	public static String getDepaId(){
		return getSessionValueByKey(Constants.USER_DEPAID);
	}
	
	/** 
	 * @Title: getSuperior 
	 * @Description: TODO 获取直属上级ID
	 * @return
	 * @return: String
	 */
	public static String getSuperior() {
		return getSessionValueByKey(Constants.SUPERIOR);
	}
	
	/** 
	 * @Title: getGradeNo 
	 * @Description: TODO 获取角色对应级别
	 * @return
	 * @return: String
	 */
	public static String getGradeNo() {
		return getSessionValueByKey(Constants.GRADE_NO);
	}
	
	/** 
	 * @Title: getTokenId 
	 * @Description: TODO 获取TokenId
	 * @return
	 * @return: String
	 */
	public static String getTokenId(){
		return getSessionValueByKey(Constants.TOKEN_ID);
	}


	/**
	 * 获取Activity事件监听获取的数据
	 * @Title: getActivitiListenerMap
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static Map<String,Object>  getActivitiListenerMap(){
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Object activitiListenerMap = req.getSession().getAttribute(Constants.ACTIVITI_LISTENER_MAP);
		if(activitiListenerMap!=null){
			return (Map<String,Object>)activitiListenerMap;
		}
		return null;
	}
	public static void  setActivitiListenerMap(Map<String,Object>  map){
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		req.getSession().setAttribute(Constants.ACTIVITI_LISTENER_MAP,map);
	}

//	/**
//	 * 获取用户的机构名字
//	 * @return
//	 */
//	public static String getOrgName(){
//		String orgName=getSessionValueByKey(Constants.us);
//		if(orgName == null) {
//			return null;
//		} else {
//			return orgName;
//		}
//	}
	

}
