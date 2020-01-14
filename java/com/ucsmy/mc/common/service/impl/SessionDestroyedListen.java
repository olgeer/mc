package com.ucsmy.mc.common.service.impl;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;

import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.exmapper.ExUserBasicMapper;
import com.ucsmy.mc.util.constants.Constants;

//@Service
public class SessionDestroyedListen implements ApplicationListener<HttpSessionDestroyedEvent> {
	
	@Autowired
	private ExUserBasicMapper exuserBasicMapper;
	
	@Override
	public void onApplicationEvent(HttpSessionDestroyedEvent event) {
		HttpSession session = event.getSession();
		String usbaId = (String) session.getAttribute(Constants.USER_ID);
		String tokenId = (String) session.getAttribute(Constants.TOKEN_ID);
		
		if (StringUtils.isNotBlank(usbaId)) {
			UserBasic userBasic = new UserBasic();
			userBasic.setUsbaId(usbaId);
			userBasic.setUsbaCredentialToken(tokenId);
			userBasic.setUsbaCredentialExpired((byte) 0);
			exuserBasicMapper.updateCredentialExpiredByUsbaIdAndToken(userBasic);
		}	
	}

}

