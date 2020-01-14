package com.ucsmy.mc.module.activiti.mapper;

import java.util.List;

import com.ucsmy.mc.module.activiti.entity.BpmPositionUser;

public interface BpmPositionUserMapper {
    int deleteByPrimaryKey(String bpusId);

    int insert(BpmPositionUser record);

    int insertSelective(BpmPositionUser record);

    BpmPositionUser selectByPrimaryKey(String bpusId);

    int updateByPrimaryKeySelective(BpmPositionUser record);

    int updateByPrimaryKey(BpmPositionUser record);
    
    List<String> selectPositionIdListByUsbaId(String usbaId);
    
    int batchInsert(List<BpmPositionUser> record);
    
    int deleteByUsbaId(String usbaId);
    
    int deleteByUsbaIdAndBppoId(String usbaid, List<String> bppoIds);
}