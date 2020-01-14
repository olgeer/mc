/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * Description:
 * Time:2015年12月3日下午12:25:33
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class FreeMarkerViewUtil extends FreeMarkerView {
	/**
	 * 部署路径属性名称
	 */
	public static final String CONTEXT_PATH = "base";

	/**
	 * 在model中增加部署路径base，方便处理部署路径问题。
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void exposeHelpers(Map model, HttpServletRequest request)
			throws Exception {
		super.exposeHelpers(model, request);
		model.put(CONTEXT_PATH, /*"http://" + request.getServerName() + ":" + request.getServerPort() +*/ request.getContextPath());
	}
}