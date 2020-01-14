/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.paging.PageContext;

/**
 * Description:前台DataTable数据接收和通用方法
 * Time:2015年12月28日下午12:37:39
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class DataTableUtil {

	/**
	  * @Description: 从request中获得参数Map，并返回可读的Map
	  * @param request
	  * @return
	  */
	public static Map<String, Object> getParameterMap() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Object isSortable=request.getAttribute("isSortable");
		if(isSortable==null){
			returnMap.put("isSortable","true");
		}else{
			returnMap.put("isSortable",isSortable.toString());
		}
	    // 参数Map
	    Map<String, String[]> properties = request.getParameterMap();
	    // 返回值Map
	    Iterator<?> entries = properties.entrySet().iterator();
	    Entry<?, ?> entry;
	    String name = "";
	    String value = "";
	    while (entries.hasNext()) {
	        entry = (Entry<?, ?>) entries.next();
	        name = (String) entry.getKey();
	        Object valueObj = entry.getValue();
	        if(null == valueObj){
	            value = "";
	        }else if(valueObj instanceof String[]){
	            String[] values = (String[])valueObj;
	            for(int i=0;i<values.length;i++){
	                value = values[i] + ",";
	            }
	            value = value.substring(0, value.length()-1);
	        }else{
	            value = valueObj.toString();
	        }
	        
	        if(StringUtils.isNotBlank(value)){
	        	returnMap.put(name, value);
            }
	    }
	    return returnMap;
	}
	
	/**
	  * @Description: 获取DataTable的操作ID数组
	  * @param parameterMap
	  * @return
	  */
	public static String[] getDataTableStrIds( Map<String, Object> parameterMap){
		String idStrs = (String) parameterMap.get(DTConstants.ID_ARRAY);
		String[] idList = null;
		if (idStrs != null){
			idList = idStrs.split(",");
		}
		return idList;
	}
	
	/**
	  * @Description: 获取DataTable的操作IDList
	  * @param parameterMap
	  * @return
	  */
	@SuppressWarnings("null")
	public static List<String> getDataTableListIds( Map<String, Object> parameterMap){
		String idStrs = (String) parameterMap.get(DTConstants.ID_ARRAY);
		List<String> idList = new ArrayList<String>();
		if (idStrs != null){
			String[] ids=idStrs.split(",");
			for (String string : ids) {
				idList.add(string);
			}
		}
		return idList;
	}
	
	/**
	  * @Description: 添加DataTable操作结果反馈信息
	  * @param dataTableMap 
	  * @param actionType 操作类型
	  * @param totalCount 需要操作总条数
	  * @param successCount 成功操作总条数
	  */
	public static void addActionMessage(Map<String, Object> dataTableMap,String actionType,int totalCount,int successCount){
		addActionMessage(dataTableMap,actionType,totalCount,successCount,null,null);
	}
	
	/**
	  * @Description: 添加DataTable操作结果反馈信息
	  * @param dataTableMap 
	  * @param actionType 操作类型
	  * @param totalCount 需要操作总条数
	  * @param successCount 成功操作总条数
	  * @param remark 备注
	  */
	public static void addActionMessage(Map<String, Object> dataTableMap,String actionType,int totalCount,int successCount,String resultStatus,String remark){
		String actionName="";
		String message="";
		int failCount=totalCount-successCount;
		if(DTConstants.SUBMIT.equals(actionType)){
			actionName="提交";
		}else if(DTConstants.DELETE.equals(actionType)){
			actionName="删除";
		}else{
			actionName="操作";
		}
		if(successCount>0 && failCount>0) {
			message="["+successCount + "]条记录被"+actionName+"，无法"+actionName +"["+ failCount + "]条记录";
       } else if(successCount > 0 && failCount == 0) {
       	message="["+successCount + "]条记录被"+actionName;
       } else if(successCount == 0 && failCount > 0) {
       	message="无法"+actionName  + "["+ failCount + "]条记录";
       } else {
       	message="没有需要["+actionName+"]记录";
       }
	   if(remark!=null){
		   message=message+"("+remark+")";
	   }
	   if(resultStatus!=null){
		   dataTableMap.put(DTConstants.STATUS, resultStatus);
	   }else{
		   dataTableMap.put(DTConstants.STATUS, DTConstants.OK);
	   }
		dataTableMap.put(DTConstants.MESSAGE, message);
	}
	
	
	/**
	  * @Description: 设置前台页面分页信息
	  * @param pageContext
	  * @param dataTableMap
	  * @param parameterMap
	  */
	public static void setPageArgs(PageContext pageContext,Map<String, Object> dataTableMap,Map<String, Object> parameterMap){
		dataTableMap.put(DTConstants.ECHO, parameterMap.get(DTConstants.ECHO)); // sEcho页面发来的参数，原样返回
		dataTableMap.put(DTConstants.DISPLAY_LENGTH,  parameterMap.get(DTConstants.DISPLAY_LENGTH));
		dataTableMap.put(DTConstants.TOTAL_RECORDS, pageContext.getTotalRows());
		dataTableMap.put(DTConstants.TOTAL_DISPLAY_RECORDS, pageContext.getTotalRows());
	}
	/**
	  * @Description: 设置是否使用系统自动拼接的排序功能
	  * @param isSortable
	  */
	public static void setSortable(boolean isSortable){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("isSortable", isSortable);
	}
}
