/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.freemarker;

/**
 * Description:字典实体
 * Time:2016年1月19日上午8:35:47
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class Dict {
	//数据字典标识 Dictionary中对应的code值
	private Byte code;
	//数据字典值  Dictionary中对应的value值
	private String value;
	
	public Dict(Byte code,String value){
		this.code=code;
		this.value=value;
	}
	public Byte getCode() {
		return code;
	}

	public void setCode(Byte code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
