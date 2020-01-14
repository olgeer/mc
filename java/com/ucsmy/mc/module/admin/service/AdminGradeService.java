package com.ucsmy.mc.module.admin.service;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Grade;

public interface AdminGradeService {
	/**
	 * @param gradIds
	 * @return 删除成功影响行数.
	 */
	int batchDeleteGrade(List<String> gradIds);
	  
     /**
      * 插入一条级别对象
      * @param record
      * @return
      */
	    int insert(Grade record);
	    /**
	     * 根據id值查詢級別對象
	     * @param gradId
	     * @return
	     */

	    Grade selectByPrimaryKey(String gradId);

       /**
        * 更新一個級別對象
        * @param record
        * @return
        */
	    int updateByPrimaryKey(Grade record);
	    /**
	     * 查询所有级别列表
	     * @param map
	     * @return
	     */
	    List<Map<String, Object>>  selectGradeList(Map<String, Object> map);
	
	/**根据级别类型查询出级别列表
	 * @param gradType
	 * @return
	 */
	List<Grade> getGradeList(Map<String, Object> map,Byte gradType);
}
