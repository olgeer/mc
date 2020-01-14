package com.ucsmy.mc.common.mapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Grade;

public interface GradeMapper {
    int deleteByPrimaryKey(String gradId);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(String gradId);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);
    /**
     * 查询所有级别列表
     * @param map
     * @return
     */
    List<Map<String, Object>>  selectGradeList(Map<String, Object> map);
}