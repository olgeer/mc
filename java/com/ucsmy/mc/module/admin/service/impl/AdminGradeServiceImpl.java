package com.ucsmy.mc.module.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.Grade;
import com.ucsmy.mc.common.mapper.GradeMapper;
import com.ucsmy.mc.module.admin.mmapper.AdminGradeMapper;
import com.ucsmy.mc.module.admin.service.AdminGradeService;
import com.ucsmy.mc.util.UUIDUtil;

@Service
public class AdminGradeServiceImpl implements AdminGradeService{
	
	@Resource
	private AdminGradeMapper adminGradeMapper;
	@Resource 
	 private GradeMapper gradeMapper; 
  
	/**
	  * @Description: 根据级别类型查询出级别列表
	  * @param map
	  * @return
	  */
	public List<Grade> getGradeList(Map<String, Object> map,Byte gradType) {
		// TODO Auto-generated method stub
		map.put("gradType", gradType);
		return adminGradeMapper.selectGradeList(map);
	}

	

	@Override
	public int insert(Grade record) {
		record.setGradId(UUIDUtil.creatUUID());
		int result=	gradeMapper.insertSelective(record);
		return result;
	}

	@Override
	public Grade selectByPrimaryKey(String gradId) {
		return gradeMapper.selectByPrimaryKey(gradId);
	}

	@Override
	public int updateByPrimaryKey(Grade record) {
		int result=	gradeMapper.updateByPrimaryKey(record);
		return result;
	}
     /**
      * 高级查询列表
      */
	@Override
	public List<Map<String, Object>>  selectGradeList(Map<String, Object> map) {
		
		return gradeMapper.selectGradeList(map);
	}



	@Override
	public int batchDeleteGrade(List<String> gradIds) {
		return adminGradeMapper.batchDeleteGrade(gradIds);
	}
	

}
