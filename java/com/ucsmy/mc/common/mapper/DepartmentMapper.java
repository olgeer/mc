package com.ucsmy.mc.common.mapper;

import com.ucsmy.mc.common.entity.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String depaId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String depaId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}