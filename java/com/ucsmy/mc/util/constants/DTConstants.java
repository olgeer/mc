/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.util.constants;


/**
 * Description:jquery-datatables常量类
 * Time:2015年12月3日上午8:36:47
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class DTConstants {
	
	//ajax-datatable在服务器返回前端的参数名称
	public static final String ECHO = "sEcho";
	public static final String TOTAL_RECORDS = "iTotalRecords";
	public static final String DISPLAY_START = "iDisplayStart";
	public static final String DISPLAY_LENGTH = "iDisplayLength";
	public static final String TOTAL_DISPLAY_RECORDS = "iTotalDisplayRecords";
	public static final String DATA = "aaData";
	public static final String ACTION = "sAction";
	public static final String MESSAGE = "sMessage";
	public static final String STATUS = "sStatus";
	public static final String USER_TASK_ID = "userTaskId";
	public static final String LAST_ID = "lastId";
	
	//ajax-datatable在服务器返回前端的sAction参数值
	public static final String GROUP_ACTION = "group_action";
	public static final String FILTER_ACTION = "filter";
	public static final String FILTER_CANCEL_ACTION = "filter_cancel";
	public static final String OK = "OK";
	public static final String DANGER = "danger";
	
	//ajax-datatable在服务器接收前端的参数名称
	public static final String ID_ARRAY = "idArarry";
	public static final String GROUP_ACTION_NAME = "sGroupActionName";
	
	//ajax-datatable在服务器接收前端的sGroupActionName参数值
	public static final String SUBMIT = "submit";
	public static final String DELETE = "delete";
	public static final String OPEN = "open";
	public static final String CLOSE = "close";
	public static final String PASS = "pass";
	public static final String ROLLBACK = "rollback";
	public static final String CANCEL = "cancel";
	public static final String CLAIM = "claim";
	public static final String EXPORT = "export";
}
