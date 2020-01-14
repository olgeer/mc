package com.ucsmy.mc.module.cmdb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ucsmy.mc.module.cmdb.service.CmdbDataService;
import com.ucsmy.mc.module.cmdb.util.IpUtil;

@Service
public class CmdbDataServiceImpl implements CmdbDataService{
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Map<String, Object> getCmdbData(String hostName, Set<String> fieldList) {
		Map<String, Object> rs = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		
		Map<String, Object> item = new HashMap<>();
		if (fieldList.contains("platform_name")) {
			String sql = "SELECT p.platform_name FROM `host` h "
					+ "JOIN platform p ON h.belongs_to_PlatForm_id = p.id WHERE h.hostname = ?";
			
			String platformName = jdbcTemplate.queryForObject(sql, new Object[]{hostName}, String.class);
			item.put("platform_name", platformName);
		}
		
		
		if (fieldList.contains("ostype")) {
			String sql = "SELECT os.os_detail_name FROM `host` h "
					+ "JOIN os_type os ON h.belongs_to_ostype_id = os.id WHERE h.hostname = ?";
			
			String osType = jdbcTemplate.queryForObject(sql, new Object[]{hostName}, String.class);
			item.put("ostype", osType);
		}
		
		
		if (fieldList.contains("apptype") || fieldList.contains("applications")) {
			String sql = 
			  "SELECT a.application_name, app_type.app_type, app_type.app_detail_type, m.template_name "
			+ "FROM `host` h JOIN host_applications ha ON h.id = ha.host_id "
			+ "JOIN application a ON ha.application_id = a.id "
			+ "JOIN app_type ON app_type.id = a.belongs_to_apptype_id "
			+ "left JOIN monitor_for_apptype mfa ON mfa.apptype_id = app_type.id "
			+ "left JOIN monitor m ON mfa.monitortemplate_id = m.id "
			+ "WHERE h.hostname = ?";
			
			List<Map<String, Object>> appList = jdbcTemplate.queryForList(sql, hostName);
			
			Set<String> appTypes =  new HashSet<>();
			Set<String> applications =  new HashSet<>();
			
			for (Map<String, Object> map : appList) {
				String applicationName = (String) map.get("application_name");
				String appType = (String) map.get("app_type");
				String appDetailType = (String) map.get("app_detail_type");
				String templateName = (String) map.get("template_name");
				
				appTypes.add(appType + "-" + appDetailType + ":" + templateName);
				applications.add(appType + "-" + appDetailType + ":" + applicationName);
			}
			
			item.put("apptype", appTypes);
			item.put("applications", applications);
		}
		
		
		
		if (fieldList.contains("vip_info")) {
			String sql = "SELECT a.application_name, v.ip "
			+ "FROM `host` h JOIN host_applications ha ON h.id = ha.host_id "
			+ "JOIN application a ON ha.application_id = a.id "
			+ "JOIN application_with_vip awv ON awv.application_id = a.id "
			+ "JOIN vip v ON v.id = awv.vip_id "
			+ "WHERE h.hostname = ?";
			
			List<Map<String, Object>> vipList = jdbcTemplate.queryForList(sql, hostName);
			Map<String, String> vips = new HashMap<>();
			for (Map<String, Object> map : vipList) {
				String ip = (String) map.get("ip");
				vips.put(ip, "");
			}
			
			item.put("vip_info", vips);
		}
		
		
		
		
		
		list.add(item);
		rs.put("data", list);
		return rs;
	}
	
	
	@Override
	public Map<String, Object> getDnsRecordData(String dnsRecordId) {
		String sql = "SELECT * FROM dnspod_record dr "
		+ "JOIN dnspod_domain dd ON dr.belongs_to_domain_id = dd.id "
		+ "JOIN dnspod_account da ON dd.belongs_to_account_id = da.id "
		+ "JOIN domain_vendor dv ON da.belongs_to_vendor_id = dv.id "
		+ "WHERE dr.id = ?";
		
		return jdbcTemplate.queryForMap(sql, dnsRecordId);
	}
	
	
	@Override
	public boolean insertIpByIpPoolId(String poolId) {
		int count = jdbcTemplate.queryForObject(
				"SELECT count(1) FROM cmdb_network_ip WHERE belongs_ip_pool_id = ?", 
				Integer.class, poolId);
		
		if (count > 0) {
			return true;
			
			/*
			int useCount = jdbcTemplate.queryForObject(
					"SELECT count(1) FROM cmdb_network_ip WHERE belongs_ip_pool_id = ? AND `status` = '1'", 
					Integer.class, poolId);
			
			if (useCount > 0) return true;
			
			int deviceUse = jdbcTemplate.queryForObject(
					"SELECT count(1) FROM cmdb_network_ip cni JOIN cmdb_device_logic_interface cdli ON cni.id = cdli.ip_id"
					+ " WHERE cni.belongs_ip_pool_id = ?", 
					Integer.class, poolId);
			*/
			
		}
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT * FROM cmdb_network_ip_pool where id = ?", poolId);
		
		if (list.size() <= 0) return true;
		
		
		String insertSql = "INSERT INTO `cmdb_network_ip` "
				+ "(`ip`, `netmask`, `gateway`, `belongs_ip_pool_id`, `belongs_platform_id`, `status`) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		

		List<Object[]> args = new ArrayList<>();
		Map<String, Object> map = list.get(0);
		
		String netmask = (String) map.get("netmask");
		String gateway = (String) map.get("gateway");
		
		Integer belongs_ip_pool_id = (Integer) map.get("id");
		Integer belongs_platform_id = (Integer) map.get("belongs_platform_id");
		String status = "0";
		
		String startIp = (String) map.get("start_ip");
		String endIp = (String) map.get("en_ip");
		
		if (StringUtils.isNotBlank(startIp) && StringUtils.isNotBlank(endIp)) {
			List<String> grenerateIp = IpUtil.grenerateIp(startIp, endIp);
			for (String ip : grenerateIp) {
				args.add(new Object[]{
						ip,
						netmask,
						gateway,
						belongs_ip_pool_id,
						belongs_platform_id,
						status
				});
			}
		}
		
		jdbcTemplate.batchUpdate(insertSql, args);
		return true;
	}
	
	
	@Override
	public boolean insertVlanByVlanPoolId(String vlanPoolId) {
		int count = jdbcTemplate.queryForObject(
				"SELECT count(1) FROM cmdb_network_vlan WHERE vlan_pool_id = ?", 
				Integer.class,
				vlanPoolId);
		
		if (count > 0) {
			return true;
		}
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT * FROM cmdb_network_vlan_pool where id = ?", vlanPoolId);
		
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			
			Object poolId = map.get("id");
			String start_vlan = (String) map.get("start_vlan");
			String end_vlan = (String) map.get("end_vlan");

			int startVlan = Integer.parseInt(start_vlan);
			int endVlan = Integer.parseInt(end_vlan);

			String vlansql = "INSERT INTO `cmdb_network_vlan` "
					+ "(`vlan`, `vlan_pool_id`, `status`, `in_use`, `belongs_device_id`)"
					+ " VALUES (?, ?, '0', '1', NULL)";
			
			List<Object[]> vlanArgs = new ArrayList<>();
			for (int j = startVlan; j <= endVlan; j++) {
				vlanArgs.add(new Object[]{j, poolId});
			}
			
			jdbcTemplate.batchUpdate(vlansql, vlanArgs);
		}
		
		return true;
	}
	
}
