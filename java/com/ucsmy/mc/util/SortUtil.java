/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:DataTable排序拼接工具类
 * Time:2015年12月29日下午3:51:01
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class SortUtil {
	
	/**
	  * @Description: 获取通过拼接的排序的SQL
	  * @param pagesql
	  * @return
	  */
	public static String getSortSQL(String pagesql){
		Map<String,Object> parameterMap=DataTableUtil.getParameterMap();
		String SortColNo=(String)parameterMap.get("iSortCol_0");//获取排序列数量（现只支持单列排序）
		String isSortable=(String)parameterMap.get("isSortable");//是否排序
		if(StringUtils.equals(isSortable, "true")){//判断是否需要排序
			if(SortColNo==null||StringUtils.equals(SortColNo, "0")){//没有排序
				return null;
			}else{
				String sortColKey="mDataProp_"+SortColNo;//DataTable需要排序列Key
				String sortColName=(String)parameterMap.get(sortColKey);//DataTable需要排序列Name(Value)
				if(sortColName.contains("_")){//DataTable查询列名称和数据库表中的列名一致时,方可拼接排序SQL（为了判断是否查询名称和列名是否一致，所以按照【表名缩写_字段】标准判断，这也是为了解决之前一部分代码可能报错问题）
					String sortType=(String)parameterMap.get("sSortDir_0");//DataTable需要排序类型(ASC或者DESC)
					String sql[]=pagesql.split(" limit");
					if(sql[0].contains("order by") ||sql[0].contains("ORDER BY")){//SQL本身包含order by
						sql[0]=sql[0]+" , "+sortColName+" "+sortType;//分页前面部分的SQL
					}else{//SQL本身没有包含order by
						sql[0]=sql[0]+" order by "+sortColName+" "+sortType;
					}
					return sql[0]+" limit"+sql[1];//拼接包含排序的最新SQL
				}else{
					return null;
				}
			}
		}else{
			return null;
		}
	}
}
