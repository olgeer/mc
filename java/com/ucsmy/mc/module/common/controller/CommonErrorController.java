/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.module.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucsmy.mc.util.constants.ErrorCodeConstants;

/**
 * Description:错误controller
 * Time:2016年1月4日上午11:27:30
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Controller
@RequestMapping("/common/error")
public class CommonErrorController {//错误信息的长度必须在200以内

	
	/**
	  * @Description: 通用保存或者更新失败提醒
	  * @param request
	  * @return
	  */
	@RequestMapping("/saveError")
	@ResponseBody
	public String saveError(HttpServletRequest request){
		return "保存失败,请稍后重试！";
	}
	
	/**
	 * 展示错误信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/showErrorFromCode")
	@ResponseBody
	public String showErrorFromCode(HttpServletRequest request){
		String code = request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
			if(Integer.parseInt(code) == ErrorCodeConstants.E1_USER_EXIST){
				return "已经存在该用户帐号，请重新输入";
			}
		}
		return "操作失败,请稍后重试！";
	}
	
	
	/**
	  * @Description: 通用保存或者更新失败提醒
	  * @param request
	  * @return
	  */
	@RequestMapping("/operateError")
	@ResponseBody
	public String operateError(HttpServletRequest request){
		return "操作失败,请稍后重试！";
	}
}
