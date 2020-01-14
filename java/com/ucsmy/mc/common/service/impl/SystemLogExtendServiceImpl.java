package com.ucsmy.mc.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.SystemLogExtend;
import com.ucsmy.mc.common.exmapper.ExSystemLogExtendMapper;
import com.ucsmy.mc.common.mapper.SystemLogExtendMapper;
import com.ucsmy.mc.common.service.SystemLogExtendService;
@Service
public class SystemLogExtendServiceImpl implements SystemLogExtendService {
  @Resource
  private ExSystemLogExtendMapper exSystemLogExtendMapper;
  @Resource SystemLogExtendMapper systemLogExtendMapper;
	@Override
	public List<SystemLogExtend> getAllSystemLogExtend() {
		return exSystemLogExtendMapper.getAllSystemLogExtend();
	}
	@Override
	public int insert(SystemLogExtend record) {
		return systemLogExtendMapper.insert(record);
	}
	@Override
	public SystemLogExtend selectSystemLogExtendBYClassAndMethod(Map<String, Object> maps, String className,
			String methodName) {
		maps.put("className", className);
		maps.put("methodName",methodName);
		return exSystemLogExtendMapper.selectSystemLogExtendBYClassAndMethod(maps);
	}
	@Override
	public void bathInsertSystemLogExtend(List<SystemLogExtend> systemLogExtendList) {
		
		exSystemLogExtendMapper.bathInsertSystemLogExtend(systemLogExtendList);
		
	}
	

}
