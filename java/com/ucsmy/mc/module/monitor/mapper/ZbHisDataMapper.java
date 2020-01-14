package com.ucsmy.mc.module.monitor.mapper;

import com.ucsmy.mc.module.monitor.entity.ZbHisData;

public interface ZbHisDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZbHisData record);

    int insertSelective(ZbHisData record);

    ZbHisData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZbHisData record);

    int updateByPrimaryKeyWithBLOBs(ZbHisData record);
}