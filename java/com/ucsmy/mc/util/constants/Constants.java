/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.util.constants;

import java.io.File;

/**
 * Description:全局的容器常量类
 * Time:2015年12月2日下午5:57:04
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class Constants {

	//操作码 
	/** 操作成功 */
	public static final int SUCCESS = 1;
	/** 操作失败 */
	public static final int FAILURE = 0;

	//删除标识
	/** 未删除 */
	public static final Byte DELETE_FLAG_NO = 0;
	/** 已删除 */
	public static final Byte DELETE_FLAG_YES = 1;
	

	//锁定标识
	/** 未锁定 */
	public static final Byte LOCK_FLAG_NO = 0;
	/** 已锁定 */
	public static final Byte LOCK_FLAG_YES = 1;
	
	//主送人and抄送人
	/** 抄送人 */
	public static final Byte COPY_SENT = 0;
	/** 主送人 */
	public static final Byte MAIN_SENT = 1;
	
	/** 附件上传Webapp下文件夹. */
	public static final String DRIVER_FILE = "/driverFile";
	
	//存放在session中的变量
	public static final String USER_ROLE_ID = "usroId";
	public static final String USER_ROLE_ID_LIST = "usroIdList";
	public static final String USER_ID = "userId";
	public static final String USER_NAME="userName";
	public static final String USER_DEPAID="userDepaid";
	public static final String USER_ACCOUNT="userAccount";
	public static final String ADMIN_ROLE_ID = "adroId";
	public static final String ROLE_ID = "roleId";
	public static final String ROLE_ID_LIST = "roleIdList";
	public static final String SESSION_ID = "sessionId";
	public static final String TOKEN_ID = "tokenId";
	public static final String ROLE_NAME="roleName";
	public static final String ADMI_ACCOUNT="admiAccount";
	public static final String ADMI_NAME="adminName";
	public static final String LOGIN_LOG="loginLog";
	public static final String ACTIVITI_LISTENER_MAP = "activitiListenerMap";
	//直属上级Id
	public static final String SUPERIOR="superior";
	//角色级别Id
	public static final String GRADE_NO="gradeNo";
	
	//部门级别
	public static final int GRAD_FIRST = 1;
	public static final int GRAD_SECOND = 2;
	public static final int GRAD_THIRD = 3;
	public static final int GRAD_FOURTH = 4;
	public static final int GRAD_FIFTH = 5;
	public static final int GRAD_SIXTH = 6;
	public static final int GRAD_SEVENTH = 7;
	
	//批量插入时,Map放入初始化ID值
	public static final int FIRST_INSERT_ID=0;
	
	/** 重置密码后的默认密码为666666 */
	public final static String RESET_PASSWORD = "666666";
	
	/** CKFinder的权限控制key */
	public final static String CKFINDER_USERROLE_KEY = "CKFinder_UserRole";
	/** CKFinder的管理员权限value */
	public final static String CKFINDER_USERROLE_ADMIN = "admin";
	
	
	/** maximum number of bytes to read */
	public final static int READ_MAXIMUM = 256;
	
	
	public final static Byte DATASOURCE_PAGE = 0;
	public final static Byte DATASOURCE_IMPORT = 1;
	
	public final static Byte SIGN_0 = 0;
	public final static Byte SIGN_1 = 1;
	
	public final static Byte SOLTYPE_0 = 0;
	
	public final static Byte SOLTYPE_1 = 1;
	public final static Byte LOCK_NO = 0;
	public final static Byte LOCK_YES = 1;
	
	public final static Byte CECH_TYPE_SECRETS = 1;
	public final static Byte CECH_TYPE_PRODUCT = 2;
	public final static Byte CECH_TYPE_RESEARCH = 3;
	
	
	public final static Byte DECRYPT_TYPE_MANUAL = 0;//手动
	public final static Byte DECRYPT_TYPE_AUTO = 1;//自动
	
	public final static Byte INCUMBENCY_STATUS_YES = 1;
	public final static Byte INCUMBENCY_STATUS_NO = 2;
	
	public final static Integer DEPA_TYPE_AREA = 0;
	public final static Integer DEPA_TYPE_DEPARTMENT = 1;
	
//	public final static Byte ROLE_TYPE_USER = 1;
//	public final static Byte ROLE_TYPE_S_ADMIN = 2;
//	public final static Byte ROLE_TYPE_QX_ADMIN = 3;
//	public final static Byte ROLE_TYPE_ADMIN = 100;
	
	//部门类型
	//区县、市直
	public static final int DEPARTMENT_TYPE_COUNTY = 0;
	//内部部门
	public static final int DEPARTMENT_TYPE_DEPARTMENT = 1;
	
	public static final String FILE_PATH=new File(System.getProperty("user.dir")).getParent()+File.separator+"file";
	
}
