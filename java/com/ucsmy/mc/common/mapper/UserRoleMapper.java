package com.ucsmy.mc.common.mapper;

import java.util.List;

import com.ucsmy.mc.common.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(String usroId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String usroId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    List<String> selectRoleIdListByUsbaId(String usbaId);
    
    int deleteByUsbaId(String usbaId);
    
    int batchDeleteByUsbaIdAndRoleId(String usbaId, List<String> roleId);
    
    int batchinsert(List<UserRole> record);
    
}