package com.ucsmy.mc.common.exmapper;

import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Dictionary;

/**
 * Description:
 * 
 * Time:2015年12月2日上午10:12:45
 * @version 1.0
 * @since 1.0
 * @author lidongxu
 */
public interface ExDictionaryMapper {
   public List<Dictionary> getDictionaryMap(Map<String, Object> map);
}