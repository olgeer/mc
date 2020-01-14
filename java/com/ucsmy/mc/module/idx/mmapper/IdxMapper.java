/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.module.idx.mmapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Department;
import com.ucsmy.mc.common.entity.Mctype;


/**
 * Description:部门Mapper
 * Time:2015年12月4日下午3:14:13
 *
 * @author chenchengteng
 * @version 1.0
 * @since 1.0
 */
public interface IdxMapper {

    /**
     * @param map
     * @return 满足查询条件的部门.
     */
    List<Map<String, Object>> getIdxList(Map<String, Object> map);

    void insertIdxSelective(Mctype mctype);

    Mctype selectIdxByPrimaryKey(String id);

    void updateIdx(Mctype mctype);

    void batchDeleteIdxByIds(List<String> depaIds);


//
//	/**
//	 * @return  该年份的、除了最低级的所有部门.
//	 */
//	List<Map<String, Object>> selectDepartmentMenu();
//
//	/**
//	 * @param depaIds
//	 * @return 删除成功影响行数.
//	 */
//	int batchDeleteDepartment(List<String> depaIds);
//
//	/**
//	 * @return 顶级部门.
//	 */
//	Department selectTopDepartment();
//
//	/**
//	 * 批量插入部门.
//	 * @param map
//	 * @return
//	 */
//	int batchInsertDepartment(Map<String, Object> map);

}
