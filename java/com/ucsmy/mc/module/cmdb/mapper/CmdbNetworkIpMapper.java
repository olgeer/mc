package com.ucsmy.mc.module.cmdb.mapper;

import com.ucsmy.mc.module.cmdb.entity.CmdbNetworkIp;

public interface CmdbNetworkIpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmdbNetworkIp record);

    int insertSelective(CmdbNetworkIp record);

    CmdbNetworkIp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmdbNetworkIp record);

    int updateByPrimaryKey(CmdbNetworkIp record);
}