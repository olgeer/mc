package com.ucsmy.mc.module.admin.service;

import java.util.List;

import com.ucsmy.mc.common.entity.Dictionary;

public interface AdminDictionaryService {
	int insert(Dictionary record);

	Dictionary selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKey(Dictionary record);
	
	List<Dictionary> selectDictionaryList();
}
