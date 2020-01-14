/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.util.Map;

import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.paging.PageContext;

/**
 * Description:分页工具类
 * Time:2015年12月3日上午8:40:05
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class PageUtil {

	/**
	 * @param start 当前页起始记录下标
	 * @param size 页宽
	 * @param totalNum 总记录数
	 * @return PageContext
	 */
	public static PageContext setPageArgs(String iDisplayStart, String iDisplayLength, String iTotalRows) {
		int totalRows = 0;
		int pageSize = 10;
		int displayStart = 0;
		int currentPage = 1;
		int totalPages = 0;
		if (iTotalRows != null) {
			totalRows = Integer.parseInt(iTotalRows);
		}
		if (iDisplayLength != null) {
			pageSize = Integer.parseInt(iDisplayLength);
			if(pageSize == -1 && totalRows!=0){//all
				pageSize = totalRows;
			}
		}
		if (iDisplayStart != null) {
			displayStart = Integer.parseInt(iDisplayStart);
		}
		// 计算第几页
		if (iDisplayStart != null && iDisplayLength != null) {
			currentPage = displayStart / pageSize + 1;
		}
		// 计算共几页
		if (iTotalRows != null && iDisplayLength != null) {
			if (totalRows % pageSize == 0) {
				totalPages = totalRows / pageSize;
			} else {
				totalPages = totalRows / pageSize + 1;
			}
		}

		PageContext pagec = PageContext.getContext();
		pagec.setCurrentPage(currentPage);
		pagec.setPageSize(pageSize);
		pagec.setTotalPages(totalPages);
		pagec.setOriginalTotalRows(totalRows);
		pagec.setPagination(true);
		return pagec;
	}
	
	/**
	  * @Description: 通过获取request中的parameterMap信息设置分页信息
	  * @param parameterMap
	  * @return
	  */
	public static PageContext setPageArgs(Map<String, Object> parameterMap) {
		String iDisplayStart=(String)parameterMap.get(DTConstants.DISPLAY_START);
		String iDisplayLength=(String)parameterMap.get(DTConstants.DISPLAY_LENGTH);
		String iTotalRows=(String)parameterMap.get(DTConstants.TOTAL_RECORDS);
		return setPageArgs(iDisplayStart, iDisplayLength, iTotalRows);
	}
}
