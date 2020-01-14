package com.ucsmy.mc.common.mapper;

import com.ucsmy.mc.common.entity.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(String permId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String permId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}