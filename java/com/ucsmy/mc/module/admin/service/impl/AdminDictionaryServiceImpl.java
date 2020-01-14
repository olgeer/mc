package com.ucsmy.mc.module.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.Dictionary;
import com.ucsmy.mc.common.mapper.DictionaryMapper;
import com.ucsmy.mc.module.admin.mmapper.AdminDictionaryMapper;
import com.ucsmy.mc.module.admin.service.AdminDictionaryService;
@Service
public class AdminDictionaryServiceImpl implements AdminDictionaryService {
  @Resource
  private DictionaryMapper dictionaryMapper;
  @Resource
  private AdminDictionaryMapper adminDictionaryMapper;
  @Override
	public int insert(Dictionary record) {
		return dictionaryMapper.insert(record);
	}

	@Override
	public Dictionary selectByPrimaryKey(Integer id) {
		return dictionaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Dictionary record) {
		return dictionaryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Dictionary> selectDictionaryList() {
		return adminDictionaryMapper.selectDictionaryList();
	}

}
