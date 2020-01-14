/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.properties;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.stereotype.Component;

/**
 * Description:properties文件加载，并且交给Spring管理
 * Time:2015年12月1日下午3:26:21
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Component
public class ApplicationPropertiesFactoryBean extends PropertiesLoaderSupport implements FactoryBean<Properties>, InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(ApplicationPropertiesFactoryBean.class);
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private Properties properties;

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws IOException {
        // 判断加载哪些properties
        this.readProperties();
        // 默认设置找不到资源也不会报错
        this.setIgnoreResourceNotFound(true);
        // 加载properties
        this.properties = mergeProperties();
    }

    public Properties getObject() throws IOException {
        return properties;
    }

    public Class<Properties> getObjectType() {
        return Properties.class;
    }

    protected void readProperties() throws IOException {
        List<Resource> resources = new ArrayList<Resource>();
        resources.add(resourceLoader.getResource("classpath:/application.properties"));
        setLocations(resources.toArray(new Resource[0]));
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        for (String key : properties.stringPropertyNames()) {
            map.put(key, properties.get(key));
        }
        return map;
    }
}
