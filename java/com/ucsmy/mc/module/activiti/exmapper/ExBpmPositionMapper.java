/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.module.activiti.exmapper;

import java.util.List;

import com.ucsmy.mc.common.pojo.BpmPositionTree;

/**
 * Description:用于获取整个岗位实体.
 * Time:2016年12月28日下午14:44:07
 * @version 1.0
 * @since 1.0
 * @author xuxiling
 */
public interface ExBpmPositionMapper {
	/**
	 * @return 整个岗位树实体.
	 */
	List<BpmPositionTree> selectBpmPositionTree();
}
