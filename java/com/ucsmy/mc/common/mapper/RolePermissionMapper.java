package com.ucsmy.mc.common.mapper;

import com.ucsmy.mc.common.entity.RolePermission;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(String ropeId);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(String ropeId);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}