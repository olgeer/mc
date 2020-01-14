/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

/**
 * @ClassName: CustomFilterSecurityInterceptor
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2017年3月8日 上午9:42:13
 * @version: V1.0
 */
@Service("customFilterSecurityInterceptor")
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	@Resource(name="customInvocationSecurityMetadataSource")
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	@Resource(name="customAccessDecisionManager")
	@Override
	public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
		// TODO Auto-generated method stub
		super.setAccessDecisionManager(accessDecisionManager);
	}
	@Resource(name="authenticationManager")
	@Override
	public void setAuthenticationManager(AuthenticationManager newManager) {
		super.setAuthenticationManager(newManager);
	}
	private void infoke(FilterInvocation fi) throws IOException, ServletException {
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}
	@Override
	public Class<?> getSecureObjectClass() {
		// TODO Auto-generated method stub
		return FilterInvocation.class;
	}
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		// TODO Auto-generated method stub
		return this.securityMetadataSource;
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		infoke(fi);
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}
	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
}
