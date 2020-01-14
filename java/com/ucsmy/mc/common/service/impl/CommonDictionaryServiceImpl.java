/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
 

package com.ucsmy.mc.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.Dictionary;
import com.ucsmy.mc.common.exmapper.ExDictionaryMapper;
import com.ucsmy.mc.common.mapper.DictionaryMapper;
import com.ucsmy.mc.common.service.CommonDictionaryService;

/**
 * Description:
 * 
 * Time:2015年12月2日上午10:12:45
 * @version 1.0
 * @since 1.0
 * @author lidongxu
 */
@Service(value="CommonDictionaryServiceImpl")
public class CommonDictionaryServiceImpl implements CommonDictionaryService {
	@Resource(name="exDictionaryMapper")	
	private ExDictionaryMapper exDictionaryMapper;
	
	@Resource(name="dictionaryMapper")	
	private DictionaryMapper DictionaryMapper;
	
	public List<Dictionary> getValueByMap(Map<String, Object> map)
	{
		return exDictionaryMapper.getDictionaryMap(map);
	}
	public String getCodeByKindAndValue(String kind ,String value)
	{
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("kind",kind);map.put("value", value);
    	List<Dictionary> list = exDictionaryMapper.getDictionaryMap(map);
    	return list.size()<1?null:list.get(0).getCode().toString();
	}
    public String getValueByKindAndCode(String kind ,String code)
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("kind",kind);map.put("code", code);
    	List<Dictionary> list = exDictionaryMapper.getDictionaryMap(map);
    	return list.size()<1?null:list.get(0).getValue();
    }
    public List<Dictionary> getDictionaryByKind(String kind)
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("kind",kind);
    	return exDictionaryMapper.getDictionaryMap(map);
    }
    
    public Dictionary getDictionaryById(int id)
    {
    	return DictionaryMapper.selectByPrimaryKey(id);
    }
    public int updateDictionary(Dictionary dic){
    	return DictionaryMapper.updateByPrimaryKey(dic);
    }
    public int insertDictionary(Dictionary dic){
    	return DictionaryMapper.insert(dic);
    }
    public int deleteDictionary(Dictionary dic){
    	return DictionaryMapper.deleteByPrimaryKey(dic.getId());
    }
}
