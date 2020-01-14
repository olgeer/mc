package com.ucsmy.mc.common.service;

import java.util.List;

import com.ucsmy.mc.common.entity.UserBasic2;
/**
 * 同步OA数据服务
 * @author ucs_xuxiling
 *
 */
public interface GetOADataService {
	 List<UserBasic2> selectAll();
	 /**
	  * 校验部门跟上一级
	  */
	 void getOAData();
	 /**
	  * 新增组织架构
	  */
	 void addedOrExamineDepartment();
}
