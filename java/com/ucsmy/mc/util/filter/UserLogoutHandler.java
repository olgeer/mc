package com.ucsmy.mc.util.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.ucsmy.mc.common.entity.LoginLog;
import com.ucsmy.mc.util.constants.Constants;

/**
 * 
 * 
 * Description:退出成功处理器
 * 
 * Time:2016年3月2日下午5:38:29
 * @version 1.0
 * @since 1.0
 * @author yangdd
 */
public class UserLogoutHandler implements LogoutHandler {
	
	private Logger log = LoggerFactory.getLogger(UserLogoutHandler.class);
	
	public UserLogoutHandler() {}
	
	
	@Override
	public void logout(HttpServletRequest req, HttpServletResponse arg1, Authentication arg2) {
		// TODO Auto-generated method stub
		//modify by qinguidong 添加try catch 为了防止session超时，而取到的loginLog为空，报错。不能返回到登录页面
		try {
			HttpSession session = req.getSession();
			LoginLog loginLog =  (LoginLog)session.getAttribute(Constants.LOGIN_LOG);
//			CommonLogService commonLogService = (CommonLogService)SpringContextUtil.getBean("commonLogServiceImpl");
			
			//清除session
			if (session != null) {  
			    session.invalidate();  
			}  
			SecurityContextHolder.clearContext(); 
			
			if(loginLog!=null){
				loginLog.setLoloLogoutDate(new Date());//退出时间
				//入库
//				int count = commonLogService.updateLoginLog(loginLog);
//				if(count != Constants.SUCCESS){
//					log.error("记录登录日志失败了："+loginLog.getLoloUsroName());
//				}
			}		
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
