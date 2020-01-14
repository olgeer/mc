package com.ucsmy.mc.common.service;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.SystemLogExtend;

public interface SystemLogExtendService {
	/**
	 * 获取所有全局日志
	 * @return
	 */
	 List<SystemLogExtend> getAllSystemLogExtend();
	 int insert(SystemLogExtend record);
	SystemLogExtend selectSystemLogExtendBYClassAndMethod(Map<String, Object> maps, String className,
			String methodName);
	void bathInsertSystemLogExtend(List<SystemLogExtend> systemLogExtendList);
}
