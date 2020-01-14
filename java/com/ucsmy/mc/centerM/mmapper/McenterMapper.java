package com.ucsmy.mc.centerM.mmapper;

import com.ucsmy.mc.centerM.bean.Itype;
import com.ucsmy.mc.centerM.bean.Monitor;

import java.util.List;
import java.util.Map;

public interface McenterMapper {
    void insertMonitorBean(Monitor monitor);
    void insertItypeBean(Itype itype);
    List<Map<String, Object>>  findByMetric(String Metric);
    List<Monitor> selectMonitorList(Map<String, ?> map);
    List<Itype> selectItypeList(Map<String, ?> map);
    int updateMonitorState(String ids);
    List<Monitor> selectMonitorListNotitleContent(Map<String, ?> map);
}
