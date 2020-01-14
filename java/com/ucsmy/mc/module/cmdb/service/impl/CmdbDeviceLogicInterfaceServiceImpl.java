package com.ucsmy.mc.module.cmdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucsmy.mc.module.cmdb.entity.CmdbDeviceLogicInterface;
import com.ucsmy.mc.module.cmdb.entity.CmdbNetworkIp;
import com.ucsmy.mc.module.cmdb.mapper.CmdbDeviceLogicInterfaceMapper;
import com.ucsmy.mc.module.cmdb.mapper.CmdbNetworkIpMapper;
import com.ucsmy.mc.module.cmdb.service.CmdbDeviceLogicInterfaceService;

@Service
public class CmdbDeviceLogicInterfaceServiceImpl implements CmdbDeviceLogicInterfaceService {
	
	@Autowired
	private CmdbDeviceLogicInterfaceMapper cmdbDeviceLogicInterfaceMapper;
	
	@Autowired 
	private CmdbNetworkIpMapper cmdbNetworkIpMapper;
	
	
	@Override
	public int insertDeviceLogicInterface (CmdbDeviceLogicInterface record) {
		
		int count = 0;
		CmdbNetworkIp ip = new CmdbNetworkIp();
		
		if (record.getBelongsDeviceId() != null) {
			if (record.getId() == null) {
				record.setInterfaceDeviceType((byte)0);
				count = cmdbDeviceLogicInterfaceMapper.insertSelective(record);
			} else {
				count = cmdbDeviceLogicInterfaceMapper.updateByPrimaryKeySelective(record);
			}
			
			ip.setId(record.getIpId());
			ip.setStatus((byte)1);
			cmdbNetworkIpMapper.updateByPrimaryKeySelective(ip);
		
		} else {
			cmdbDeviceLogicInterfaceMapper.deleteByPrimaryKey(record.getId());
			
			ip.setId(record.getIpId());
			ip.setStatus((byte)0);
			cmdbNetworkIpMapper.updateByPrimaryKeySelective(ip);
		}
		
		
		
		return count;
	}
	
	
	
	

}
