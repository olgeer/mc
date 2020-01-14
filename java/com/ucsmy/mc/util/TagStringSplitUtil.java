/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:Tag控件字符串格式转黄
 * Time:2015年12月31日上午10:49:38
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class TagStringSplitUtil {

	/**
	  * @Description: 用于前台显示Tag控件Text值
	  * @param list 主送人或者抄送人MAP集合
	  * @return
	  */
	public static String convertToTagText(List<Map<String, Object>> list){
		
		StringBuffer stringBuffer=new StringBuffer();
		for (Map<String, Object> map : list) {
			stringBuffer.append(map.get("usba_name"));
			stringBuffer.append("(");
			stringBuffer.append(map.get("usba_account"));
			stringBuffer.append(")");
			stringBuffer.append("[");
			stringBuffer.append(map.get("role_name"));
			stringBuffer.append("]");
			stringBuffer.append(",");
		}
		return stringBuffer.toString();
	}
	
	/**
	  * @Description: 用于前台隐藏的Tag控件Value值
	  * @param list 主送人或者抄送人MAP集合
	  * @return
	  */
	public static String convertToTagValue(List<Map<String, Object>> list){
		
		StringBuffer stringBuffer=new StringBuffer();
		for (Map<String, Object> map : list) {
			stringBuffer.append(map.get("usro_id"));
			stringBuffer.append("&");
			stringBuffer.append(map.get("usba_name"));
			stringBuffer.append("(");
			stringBuffer.append(map.get("usba_account"));
			stringBuffer.append(")");
			stringBuffer.append("[");
			stringBuffer.append(map.get("role_name"));
			stringBuffer.append("]");
			stringBuffer.append(",");
		}
		return stringBuffer.toString();
	}
	
	/**
	  * @Description: 将Tag控件值转换成：List
	  * @param originalStr
	  * @return
	  */
	public static List<String> convertToIds(String originalStr){
		List<String> list=new ArrayList<String>();
		//需要拆解的字符串格式为：用户ID&用户名称(用户账号),
		String[] str=originalStr.split(",");
		for (int i = 0; i < str.length; i++) {
			String id=str[i].split("&")[0];
			if(StringUtils.isNotBlank(id)){
				list.add(id);
			}
		}
		return list;
	}
}
