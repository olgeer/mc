package com.ucsmy.mc.common.mapper;

import java.util.List;

import com.ucsmy.mc.common.entity.UserBasic2;

public interface UserBasic2Mapper {
    int deleteByPrimaryKey(String usbaId);

    int insert(UserBasic2 record);

    int insertSelective(UserBasic2 record);

    UserBasic2 selectByPrimaryKey(String usbaId);

    int updateByPrimaryKeySelective(UserBasic2 record);

    int updateByPrimaryKey(UserBasic2 record);
    List<UserBasic2> selectAll();

	UserBasic2 selectUserBasic2ByUsbaAccount(String usbaAccount);

	void bathUpdateUserBasic(List<UserBasic2> userBasics);

	List<UserBasic2> selectUserBasisByDepaIdNUll();
}