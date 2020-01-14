package com.ucsmy.mc.util.filter;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 
 * 
 * Description:退出过滤器
 * 
 * Time:2016年3月2日下午5:38:04
 * @version 1.0
 * @since 1.0
 * @author yangdd
 */
public class UserLogoutFilter extends LogoutFilter{

	public UserLogoutFilter(String logoutSuccessUrl, LogoutHandler[] handlers) {
		super(logoutSuccessUrl, handlers);
	}

	public UserLogoutFilter(LogoutSuccessHandler logoutSuccessHandler,LogoutHandler[] handlers) {
		super(logoutSuccessHandler, handlers);
	}

}
