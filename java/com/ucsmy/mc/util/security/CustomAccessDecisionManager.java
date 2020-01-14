/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: CustomAccessDecisionManager 
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2017年3月8日 上午9:32:29
 * @version: V1.0     
 */
@Service("customAccessDecisionManager") 
public class CustomAccessDecisionManager implements AccessDecisionManager{

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		// TODO Auto-generated method stub
		 if (null == configAttributes) {  
	            return;  
	        }  
	          
	        Iterator<ConfigAttribute> cons = configAttributes.iterator();  
	          
	        while(cons.hasNext()){  
	            ConfigAttribute ca = cons.next();  
	            String needRole = ((SecurityConfig) ca).getAttribute();  
	            //gra 为用户所被赋予的权限，needRole为访问相应的资源应具有的权限  
	            for (GrantedAuthority gra : authentication.getAuthorities()) {  
	                if (needRole.trim().equals(gra.getAuthority().trim())) {  
	                    return;  
	                }  
	            }  
	        }  
	        throw new AccessDeniedException("Access Denied");  
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
}
