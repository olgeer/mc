package com.ucsmy.mc.util.constants;

public class ErrorCodeConstants {

	/**
	 * 用户相关错误代码
	 */
	//已存在该用户
	public static final int E1_USER_EXIST=101;

	/**
	 * 登录异常
	 */
	//用户名或密码有误
	public static final String E2_LOGIN_USERPW = "201";
	public static final String E2_LOGIN_USERPW_DETAIL = "用户名或密码有误";
	//用户未分配权限
	public static final String E2_LOGIN_NOAUTHORITY = "202";
	public static final String E2_LOGIN_NOAUTHORITY_DETAIL = "用户未分配权限";
	
	
}
