package com.ucsmy.mc.common.service;

import java.util.List;

import com.ucsmy.mc.common.entity.SystemLog;

public interface SystemLogService {
	  int deleteByPrimaryKey(Integer syloId);

	    int insert(SystemLog record);

	    SystemLog selectByPrimaryKey(Integer syloId);

	    int updateByPrimaryKey(SystemLog record);

		void bathInsertSystemLog(List<SystemLog> systemLoglist);
}
