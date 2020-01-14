package com.ucsmy.mc.common.mapper;

import com.ucsmy.mc.common.entity.UserBasic;

public interface UserBasicMapper {
    int deleteByPrimaryKey(String usbaId);

    int insert(UserBasic record);

    int insertSelective(UserBasic record);

    UserBasic selectByPrimaryKey(String usbaId);

    int updateByPrimaryKeySelective(UserBasic record);

    int updateByPrimaryKey(UserBasic record);
}