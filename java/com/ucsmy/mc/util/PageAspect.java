/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import com.ucsmy.mc.util.paging.PageContext;

/**
 * Description:添加默认不分页的设置
 * Time:2016年1月22日下午2:32:41
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class PageAspect {

    public void doPageFalseBefore() throws Throwable{
    	PageContext.getContext().setPagination(false);
    }
}