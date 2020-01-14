package com.ucsmy.mc.common.mapper;

import com.ucsmy.mc.common.entity.SystemLog;

public interface SystemLogMapper {
    int deleteByPrimaryKey(String syloId);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(String syloId);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
}