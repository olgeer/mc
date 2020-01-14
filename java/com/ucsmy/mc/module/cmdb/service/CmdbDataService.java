package com.ucsmy.mc.module.cmdb.service;

import java.util.Map;
import java.util.Set;

public interface CmdbDataService {

	Map<String, Object> getCmdbData(String hostName, Set<String> fieldList);

	Map<String, Object> getDnsRecordData(String dnsRecordId);

	boolean insertIpByIpPoolId(String poolId);

	boolean insertVlanByVlanPoolId(String vlanPoolId);

}
