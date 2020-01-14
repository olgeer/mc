/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucsmy.mc.module.common.mmaper.CommonQueryUserMapper;
import com.ucsmy.mc.module.common.service.CommonQueryUserService;

/**
 * Description:
 * Time:2015年12月30日上午10:50:30
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Service
public class CommonQueryUserServiceImp implements CommonQueryUserService {
	@Resource
	private CommonQueryUserMapper commonQueryUserMapper;
	
	@Override
	public List<Map<String, Object>> getUserAccountInfo(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return commonQueryUserMapper.selectUserAccountInfo(map);
	}

}
