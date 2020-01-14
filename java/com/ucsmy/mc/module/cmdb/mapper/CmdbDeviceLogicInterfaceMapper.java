package com.ucsmy.mc.module.cmdb.mapper;

import com.ucsmy.mc.module.cmdb.entity.CmdbDeviceLogicInterface;

public interface CmdbDeviceLogicInterfaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmdbDeviceLogicInterface record);

    int insertSelective(CmdbDeviceLogicInterface record);

    CmdbDeviceLogicInterface selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmdbDeviceLogicInterface record);

    int updateByPrimaryKey(CmdbDeviceLogicInterface record);
    
    CmdbDeviceLogicInterface selectByIpId(Integer ipId);
}