/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.common.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ucsmy.mc.common.entity.LoginLog;
import com.ucsmy.mc.util.constants.ErrorCodeConstants;

/** 
 * @ClassName: CommonIndexController 
 * @Description: 系统登录入口Controller
 * @author: ucs_chenchengteng
 * @date: 2016年11月28日 下午1:59:48
 * @version: V1.0     
 */
@Controller
public class CommonIndexController {
	private Logger log = LoggerFactory.getLogger(CommonIndexController.class);
	public static final String MESSAGE = "message";
	
	
	@Autowired  
    private Producer captchaProducer = null;
	
	/** 
	 * @Title: index 
	 * @Description: TODO
	 * @param req
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"/","/index.html","/index"}, method = RequestMethod.GET)
	public String index(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		for(int i = 0; cookies != null && i < cookies.length; i++) {
			if(cookies[i].getName().equals("loginInfo")){
				String loginType = cookies[i].getValue().split("-")[0];
				String loginName = cookies[i].getValue().split("-")[1];
				req.setAttribute("cookiesLoginType", loginType);
				req.setAttribute("cookiesLoginName", loginName);
				
			}
		}
		return "common/userlogin/login";
	}
	
	@RequestMapping(value = {"/admin","/admin_login.html"}, method = RequestMethod.GET)
	public String admin_login() {
		return "common/userlogin/login";
	}
	
	/**
	 * session过期
	 * @return
	 */
	@RequestMapping(value = {"/sessionTimeout"}, method = RequestMethod.GET)
	public String sessionTimeout() {
		return "common/userlogin/session_timeout";
	}
	
	@RequestMapping(value = {"/common/init","/portal.html","/portal"}, method = RequestMethod.GET)
	public String portal() {
		return "redirect:common/login/initPage";
	}
	/**
	 * 获取验证码
	 * @param req HttpServletRequest
	 * @param resp HttpServletResponse
	 * @throws IOException 
	 */
	@RequestMapping("/getCaptcha")
	public void getCaptcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();  
        resp.setDateHeader("Expires", 0);  
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        resp.setHeader("Pragma", "no-cache");  
        resp.setContentType("image/jpeg");  
        String capText = captchaProducer.createText();  
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out= resp.getOutputStream();  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        }catch(Exception e) { 
        	log.info(e.getMessage());
        }finally { 
            out.close();  
        }  
	}

	/**
	  * @Description: 校验码输入错误
	  * @param req
	  * @param resp
	  * @return
	  */
	@RequestMapping("/codeCheckFailed")
	@ResponseBody
	public Map<String, Object> codeCheckFailed(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put(MESSAGE, "captchaError");
		return msg;
	}
	
	/**
	 * @Description: 需要输入校验码
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/captchaRequired")
	@ResponseBody
	public Map<String, Object> captchaRequired(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put(MESSAGE, "captchaRequired");
		return msg;
	}

	/**
	  * @Description: 用户名或者密码输入错误
	  * @param req
	  * @param resp
	  * @return
	  */
	@RequestMapping("/usernameCheckFailed")
	@ResponseBody
	public Map<String, Object> usernameCheckFailed(HttpServletRequest req, HttpServletResponse resp) {
		//登录日志记录
		HttpSession session = req.getSession();
		LoginLog loginLog =  (LoginLog)session.getAttribute(com.ucsmy.mc.util.constants.Constants.LOGIN_LOG);
		if(loginLog != null){
			loginLog.setLoloType(com.ucsmy.mc.util.constants.Constants.SIGN_1);//异常日志
			//用户名或密码错误
			loginLog.setLoloExceptionCode(ErrorCodeConstants.E2_LOGIN_USERPW);
			loginLog.setLoloExceptionDetail(ErrorCodeConstants.E2_LOGIN_USERPW_DETAIL);
			loginLog.setLoloLoginDate(new Date());
			
		}else{
			log.error("记录登录日志失败了：获取不到记录日志的信息");
		}
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put(MESSAGE, "usernameError");
		return msg;
	}
	
	
	@RequestMapping(value = {"user/home/page","module/home/page"}, method = RequestMethod.GET)
	public String home(HttpServletRequest req,HttpServletResponse resp) {		
		return "/common/userlogin/home";
	}
}
