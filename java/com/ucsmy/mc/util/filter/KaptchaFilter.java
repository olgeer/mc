/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.exmapper.ExUserBasicMapper;
import com.ucsmy.mc.util.SpringContextUtil;
import com.ucsmy.mc.util.UUIDUtil;
import com.ucsmy.mc.util.paging.PageContext;

/**
 * Description:Kaptcha校验码过滤器
 * Time:2015年12月1日上午9:53:46
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Component
public class KaptchaFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
         HttpServletResponse res = (HttpServletResponse) response;
         String kaptchaExpected=(String)req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
         String kaptchaReceived = request.getParameter("kaptcha");
         if(StringUtils.isBlank(kaptchaReceived)){
        	 PageContext.getContext().setPagination(false);
 			 UserBasic userBasic = SpringContextUtil.getBean(ExUserBasicMapper.class).selectUserBasicByUserAccount(request.getParameter("j_username"));
 			 if(userBasic==null){
 				chain.doFilter(request, response);
 			 }else if(userBasic.getUsbaFailureCount()!=null&&userBasic.getUsbaFailureCount()>=3){
 				res.sendRedirect(req.getContextPath() + "/captchaRequired");
 			 }else{
 				chain.doFilter(request, response);
 			 }
         }else if (!kaptchaReceived.equalsIgnoreCase(kaptchaExpected)){  
        	 res.sendRedirect(req.getContextPath() + "/codeCheckFailed");
         }else{
        	 chain.doFilter(request, response);
         }
         req.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, UUIDUtil.creatUUID());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
