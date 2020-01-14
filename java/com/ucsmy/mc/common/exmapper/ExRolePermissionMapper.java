package com.ucsmy.mc.common.exmapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.RolePermission;

public interface ExRolePermissionMapper {

    /**
     * 通过角色ID得到角色模块列表.
     * @param map 封装角色ID(String roleId)
     * @return 角色模块实体
     */
    public List<RolePermission> selectRolePermissionByRoleId(Map<String, Object> map);
    
}
