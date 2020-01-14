package com.ucsmy.mc.common.mapper;

import com.ucsmy.mc.common.entity.LoginLog;

public interface LoginLogMapper {
    int deleteByPrimaryKey(Integer loloId);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer loloId);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}