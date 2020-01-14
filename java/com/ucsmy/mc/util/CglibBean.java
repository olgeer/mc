/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;

/**
 * @ClassName: CglibBean
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2016年12月6日 上午9:47:33
 * @version: V1.0
 */
public class CglibBean {
	/**
	 * 实体Object
	 */
	public Object object = null;
	/**
	 * 属性map
	 */
	public BeanMap beanMap = null;

	public CglibBean() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public CglibBean(Map propertyMap,Map valueMap, String className) {
		this.object = generateBean(propertyMap, className);
		this.beanMap = BeanMap.create(this.object);
		this.beanMap.putAll(valueMap);
	}
	
	@SuppressWarnings("unchecked")
	public CglibBean(Map propertyMap, String className) {
		this.object = generateBean(propertyMap, className);
		this.beanMap = BeanMap.create(this.object);
	}
	/**
	 * 给bean属性赋值
	 * @param property 属性名
	 * @param value 值
	 */
	public void setValue(String property, Object value) {
		beanMap.put(property, value);
	}
	
	/**
	 * 通过属性名得到属性值
	 * @param property 属性名
	 * @return 值
	 */
	public Object getValue(String property) {
		return beanMap.get(property);
	}
	
	/**
	 * 得到该实体bean对象
	 * @return
	 */
	public Object getObject() {
		return this.object;
	}
	
	@SuppressWarnings("unchecked")
	private Object generateBean(Map propertyMap, final String className) {
		BeanGenerator generator = new BeanGenerator();
//		generator.setSuperclass(Entity.class);
		NamingPolicy namingPolicy = new NamingPolicy() {
			@Override
			public String getClassName(String arg0, String arg1, Object arg2, Predicate arg3) {
				// TODO Auto-generated method stub
				return className;
			}
		};
		generator.setNamingPolicy(namingPolicy);
		generator.setUseCache(false);
		generator.setAttemptLoad(true);
		Set keySet = propertyMap.keySet();
		for (Iterator i = keySet.iterator(); i.hasNext();) {
			String key = (String) i.next();
			generator.addProperty(key, (Class) propertyMap.get(key));
		}
		return generator.create();
	}

}
