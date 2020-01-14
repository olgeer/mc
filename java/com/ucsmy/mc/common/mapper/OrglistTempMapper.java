package com.ucsmy.mc.common.mapper;

import java.util.List;

import com.ucsmy.mc.common.entity.OrglistTemp;

public interface OrglistTempMapper {
    int deleteByPrimaryKey(String dpid);

    int insert(OrglistTemp record);

    int insertSelective(OrglistTemp record);

    OrglistTemp selectByPrimaryKey(String dpid);

    int updateByPrimaryKeySelective(OrglistTemp record);

    int updateByPrimaryKey(OrglistTemp record);
    
    void bathInsertOrgList(List<OrglistTemp> orglistTempList);

	List<OrglistTemp> selectAll();

	List<OrglistTemp> getDepartmentByDepaName(String depaName);
}