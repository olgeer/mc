package com.ucsmy.mc.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.SystemLog;
import com.ucsmy.mc.common.exmapper.ExSystemLogMapper;
import com.ucsmy.mc.common.mapper.SystemLogMapper;
import com.ucsmy.mc.common.service.SystemLogService;
@Service
public class SystemLogServiceImpl implements SystemLogService {
@Resource
private SystemLogMapper systemLogMapper;
@Resource
private ExSystemLogMapper exSystemLogMapper;
	@Override
	public int deleteByPrimaryKey(Integer syloId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(SystemLog record) {
		// TODO Auto-generated method stub
		return systemLogMapper.insert(record);
	}

	@Override
	public SystemLog selectByPrimaryKey(Integer syloId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(SystemLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void bathInsertSystemLog(List<SystemLog> systemLoglist) {
		exSystemLogMapper.bathInsertSystemLog(systemLoglist);
		
	}

}
