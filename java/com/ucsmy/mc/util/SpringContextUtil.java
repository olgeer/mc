/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Description:获取Spring容器中的bean
 * Time:2015年12月9日下午4:07:31
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Component
public class SpringContextUtil implements ApplicationContextAware{

	 // Spring应用上下文环境  
    private static ApplicationContext applicationContext;  
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 SpringContextUtil.applicationContext = applicationContext;  
	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	/**
	  * @Description: 获取对象
	  * @param name
	  * @return
	  * @throws BeansException
	  */
	public static Object getBean(String name) throws BeansException{
		return applicationContext.getBean(name); 
	}
	
	/**
	  * @Description: 获取配置文件bean
	  * @return
	  */
	public static Properties getPropertiesBean(){
		return (Properties)getBean("applicationPropertiesFactoryBean");
	}
    /**
      * @Description: 根据class获得bean.
      * @param clz
      * @return
      */
    public static <T> T getBean(Class<T> clz) {
        return applicationContext.getBean(clz);
    }
}
