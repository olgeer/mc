package com.ucsmy.mc.common.mapper;

import com.ucsmy.mc.common.entity.SystemLogExtend;

public interface SystemLogExtendMapper {
    int deleteByPrimaryKey(String sleId);

    int insert(SystemLogExtend record);

    int insertSelective(SystemLogExtend record);

    SystemLogExtend selectByPrimaryKey(String sleId);

    int updateByPrimaryKeySelective(SystemLogExtend record);

    int updateByPrimaryKey(SystemLogExtend record);
}