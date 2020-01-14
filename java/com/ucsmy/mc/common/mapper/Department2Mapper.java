package com.ucsmy.mc.common.mapper;

import java.util.List;

import com.ucsmy.mc.common.entity.Department2;

public interface Department2Mapper {
    int deleteByPrimaryKey(String depaId);

    int insert(Department2 record);

    int insertSelective(Department2 record);

    Department2 selectByPrimaryKey(String depaId);

    int updateByPrimaryKeySelective(Department2 record);

    int updateByPrimaryKey(Department2 record);
    Department2 getDepartmentByDepaName(String depaName);
	
	List<Department2>selectAllDepartment();
}