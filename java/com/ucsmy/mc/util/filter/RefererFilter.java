/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ucsmy.mc.util.SpringContextUtil;
import com.ucsmy.mc.util.session.SessionPropertyUtil;

/** 
 * Referer校验
 * @ClassName: RefererFilter 
 * @Description: TODO 
 * @author: ucs_chenchengteng
 * @date: 2017年3月6日 下午5:28:11
 * @version: V1.0     
 */
public class RefererFilter implements Filter{

	private static Properties properties = SpringContextUtil.getPropertiesBean();
	
	private static  String ip = "";
	private static  List<String> domains ;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(properties.getProperty("server.domain"))){
			domains=Arrays.asList(properties.getProperty("server.domain").split(","));
		}
		if(StringUtils.isNotBlank(properties.getProperty("server.ip"))){
			ip=properties.getProperty("server.ip");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//判断是否已经登录，如果登录，则校验Referer
		 if(StringUtils.isNotBlank(SessionPropertyUtil.getSessionId())){
			 if(StringUtils.isNotBlank(ip)||domains!=null){
				 HttpServletRequest req = (HttpServletRequest) request;
				 HttpServletResponse res = (HttpServletResponse) response;
				 String url=req.getHeader("referer");
				 if(StringUtils.isNotBlank(url)){
					 String ipOrDomain=url.split("//")[1].split("/")[0];
					 if(ip.equals(ipOrDomain)||domains.contains(ipOrDomain)){
						 chain.doFilter(request, response);
					 }else{
						 res.sendRedirect(req.getContextPath());
					 }
				 }else{
					 chain.doFilter(request, response);
				 }
			 }else{
				 chain.doFilter(request, response);
			 }
		 }else{
			 chain.doFilter(request, response);
		 }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
