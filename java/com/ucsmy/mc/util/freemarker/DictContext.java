/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.freemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Dictionary;
import com.ucsmy.mc.common.exmapper.ExDictionaryMapper;
import com.ucsmy.mc.util.SpringContextUtil;
import com.ucsmy.mc.util.paging.PageContext;

/**
 * Description:字典上下文
 * Time:2016年1月18日下午4:42:04
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class DictContext {
	private static Map<String, List<Dict>> map;
	
	private DictContext(){
		map = new HashMap<String, List<Dict>>();
		PageContext.getContext().setPagination(false);
		List<Dictionary> list =((ExDictionaryMapper) SpringContextUtil.getBean("exDictionaryMapper")).getDictionaryMap(new HashMap<String, Object>());
		for (Dictionary dictionary : list) {
			List<Dict> dictList = new ArrayList<Dict>();
			if(map.containsKey(dictionary.getKind())){
				dictList=(List<Dict>) map.get(dictionary.getKind());
			}
			dictList.add(new Dict(dictionary.getCode(),dictionary.getValue()));
			map.put(dictionary.getKind(), dictList);
		}
	}
	
	private static class SingletonHolder {
		static DictContext instance = new DictContext();
	}
	
	public static DictContext getInstance() {
        return SingletonHolder.instance;
    }
	
	
	/** 
	 * @Title: reloadDictionary 
	 * @Description: TODO 重新加载字典数据
	 * @return: void
	 */
	public static void reloadDictionary(){
		
		SingletonHolder.instance= new DictContext();
	}
	/**
	  * @Description: 根据key获得数据字典集
	  * @param key 数据字典标识
	  * @return 数据字典集合List
	  */
	public List<Dict> getDict(String key) {
		if(!map.containsKey(key))
			return null;
		return map.get(key);
	}
	
	/**
	  * @Description: 根据key和code获得对应的文本
	  * @param key 数据字典标识
	  * @param value 数据字典值
	  * @return 数据字典对应的文本值
	  */
	public String getDictValue(String key, byte code) {
		List<Dict> list = map.get(key);
		if (list == null)
			return "";
		for (Dict dict : list) {
			if (dict.getCode()==null)
				continue;
			if (dict.getCode() == code)
				return dict.getValue();
		}
		return "";
	}


	/** 
	 * 通过kind和value获取字典code值
	 * @Title: getDictCode 
	 * @Description: TODO
	 * @param kind
	 * @param value
	 * @return
	 * @return: Object
	 */
	public Object getDictCode(String kind, String value) {
		List<Dict> list = map.get(kind);
		if (list == null)
			return null;
		for (Dict dict : list) {
			if (value.equals(dict.getValue())){
				return dict.getCode();
			}
		}
		return null;
	}
}