package com.ucsmy.mc.module.activiti.mapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.module.activiti.entity.BpmPosition;

public interface BpmPositionMapper {
    int deleteByPrimaryKey(String bppoId);

    int insert(BpmPosition record);

    int insertSelective(BpmPosition record);

    BpmPosition selectByPrimaryKey(String bppoId);

    int updateByPrimaryKeySelective(BpmPosition record);

    int updateByPrimaryKey(BpmPosition record);
    
    List<BpmPosition> selectPositionList(Map<String, Object> map);
}