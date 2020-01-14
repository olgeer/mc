/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.exmapper.ExUserBasicMapper;
import com.ucsmy.mc.common.mapper.UserBasicMapper;

/** 
 * 用户登录失败处理器
 * @ClassName: LoginFailureHandler 
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2017年4月6日 下午3:44:26
 * @version: V1.0     
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	@Resource
	private UserBasicMapper userBasicMapper;
	@Resource
	private ExUserBasicMapper exUserBasicMapper;
	@Override
	public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//获取用户名，并且更改用户登录失败次数
		UserBasic userBasic = exUserBasicMapper.selectUserBasicByUserAccount(arg0.getParameter("j_username"));
		if(userBasic!=null){
			byte failureCount=0;
			if(userBasic.getUsbaFailureCount()!=null){
				 failureCount= userBasic.getUsbaFailureCount();
			}
			userBasic.setUsbaFailureCount(++failureCount);
			userBasicMapper.updateByPrimaryKeySelective(userBasic);
		}
		arg1.sendRedirect(arg0.getContextPath() + "/usernameCheckFailed");

	}
}
