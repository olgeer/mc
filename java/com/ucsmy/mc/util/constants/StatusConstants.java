/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.util.constants;

/**
 * Description:操作状态常量
 * Time:2015年12月4日下午3:18:04
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class StatusConstants {
	
	/** 已删除状态 */
	public static final byte DELETE_STATUS_YES = 1;
	/** 未删除状态 */
	public static final byte DELETE_STATUS_NO = 0;
	
	/** 已回收状态 */
	public static final byte RECYCLE_STATUS_YES = 1;
	/** 未回收状态 */
	public static final byte RECYCLE_STATUS_NO = 0;
	
	
	/** 禁用状态 */
	public static final byte CLOSE=0;
	/** 启用状态 */
	public static final byte OPEN=1;
	
	/*开题报告提交草稿的回复状态*/
	/** 未回复 */
	public static final byte NOT_REPLY = 0;
	/** 已回复 */
	public static final byte REPLIED = 1;
	
	/** 流程或者任务当前状态  PT代表流程和任务*/
	/** 未启动 */
	public static final byte  PTNOSTART= 0;
	/** 进行中 */
	public static final byte  PTSTARTING= 1;
	/** 已结束 */
	public static final byte  PTFINISH= 2;
	/** 不存在 */
//	public static final byte  PTNOEXIST= 3;
	
	/** 流程或者任务当前状态  PT代表流程和任务*/
	/**  未启动*/
	public static final String  PTNOSTART_STR = "PTNOSTART";
	/**  进行中*/
	public static final String  PTSTARTING_STR = "PTSTARTING";
	/** 已结束 */
	public static final String  PTFINISH_STR = "PTFINISH";
	/**  不存在*/
	public static final String  PTNOEXIST_STR= "PTNOEXIST";
	

	//补交状态
	/** 拦截器信息 */
	public static final String INTE_MESSAGE = "message";
	public static final String INTE_PROCESS = "process";
	
	
	
	//未提交
	public static final byte NOSUBMIT=0;
	//待审核
	public static final byte WAITAUDIT = 1;
	//审核最终通过
	public static final byte PASS=2;
	//退回
	public static final byte ROLLBACK = 3;
	
	
}
