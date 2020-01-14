/**   
 * Copyright © 2016 广东网金控股股份有限公司. All rights reserved.
 * 
 */
package com.ucsmy.mc.util;

import java.util.UUID;

/**
 * @ClassName: UUIDUtil UUID生成器
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2016年11月23日 下午4:28:29
 * @version: V1.0
 */
public class UUIDUtil {
	public static String creatUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
