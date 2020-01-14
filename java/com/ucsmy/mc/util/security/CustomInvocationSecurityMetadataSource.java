/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.ucsmy.mc.common.exmapper.ExPermissionMapper;

/**
 * @ClassName: CustomInvocationSecurityMetadataSource
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2017年3月8日 上午9:36:16
 * @version: V1.0
 */
@Service("customInvocationSecurityMetadataSource")
public class CustomInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private PathMatcher pathMatcher = new AntPathMatcher();
    private HashMap<String, Collection<ConfigAttribute>> resourceMap = null;
    @Resource
    private ExPermissionMapper exPermissionMapper;

    @PostConstruct
    public void init() {
        loadResourceDefine();
    }

    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        List<Map<String, Object>> list = exPermissionMapper.selectPermissionList(Collections.EMPTY_MAP);
        for (Map<String, Object> map : list) {
            String permResource = (String) map.get("perm_resource");
            String permUrl = (String) map.get("perm_url");
            if (StringUtils.isNotBlank(permResource) && StringUtils.isNotBlank(permUrl)) {
                ConfigAttribute ca = new SecurityConfig(permResource);
                Collection<ConfigAttribute> configAttribute = new ArrayList<ConfigAttribute>();
                configAttribute.add(ca);
                resourceMap.put(permUrl, configAttribute);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        //object 是一个URL ,为用户请求URL
        String url = ((FilterInvocation) object).getRequestUrl();
        if ("/".equals(url)) {
            return null;
        }
        int firstQuestionMarkIndex = url.indexOf("?");
        //判断请求是否带有参数 如果有参数就去掉后面的后缀和参数(/index?xxx  --> /index)
        if (firstQuestionMarkIndex != -1) {
            String paramStr = url.substring(firstQuestionMarkIndex + 1, url.length());

            url = url.substring(0, firstQuestionMarkIndex);
            if ("/mc".equals(url) || "/mc/".equals(url)) {
	            String[] params = paramStr.split("&");
	            String tempStr = null;
	            String actionStr = null;
	            String tablenameStr = null;
	            for (String param : params) {
	                if (param.length() > 0) {
	                    tempStr = param.split("=")[0];
	                    if (tempStr.compareToIgnoreCase("_action") == 0) {
	                        actionStr = param;
	                    }
	                    if (tempStr.compareToIgnoreCase("_tablename") == 0) {
	                        tablenameStr = param;
	                    }
	                }
	            }
	            
	            if (actionStr != null && tablenameStr != null) 
	            	url = "/mc?" + actionStr + "&" + tablenameStr;
            }
        }
    
        if (url.endsWith("/")) {
        	url = url.substring(1, url.length() - 1);
        } else {
        	url = url.substring(1, url.length());
        }
        
        Collection<ConfigAttribute> rs = resourceMap.get(url);
        if (rs != null) {
        	return rs;
        }
    
    
        Iterator<String> ite = resourceMap.keySet().iterator();
        //取到请求的URL后与上面取出来的资源做比较  
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (pathMatcher.match(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }
}
