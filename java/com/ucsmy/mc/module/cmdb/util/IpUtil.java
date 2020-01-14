package com.ucsmy.mc.module.cmdb.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class IpUtil {
	
	
	/**
	 * 处理以指定开始ip到结束ip模板  [模板：start-end]
	 * @param start 开始ip
	 * @param end 结束ip
	 * @return
	 */
	public static List<String> grenerateIp(String startIp, String endIp) {
		// eg : 192.168.1.100-192.168.3.200
		long start = ipToLong(startIp);
		long end = ipToLong(endIp);
		
		List<String> rs = new ArrayList<>();
		for (; start <= end; start++) {
			rs.add(LongToIp(start));
		}
		
		return rs;
	}
	
	
	
	/**
	 * 处理以掩码方式指定的ip模板   [模版 ： start:mask]
	 * @param start 起始IP地址 
	 * @param ipMask 子网掩码
	 */
	public static List<String> grenerateIpByNetmask(String startIp, String netmask) {
		// eg : 122.13.216.0/255.255.255.224
		long start = ipToLong(netmask);
		return grenerateIp(startIp, Long.bitCount(start));
	}
	
	
	/**
	 * 处理以掩码方式指定的ip模板   [模版 ： start/mask]
	 * @param start 起始IP地址 
	 * @param ipMask 子网掩码
	 */
	public static List<String> grenerateIp(String startIp, int ipMask) {
		// eg : 192.168.1.0/24
		long start = ipToLong(startIp);
		Long e_ = start | ((int) Math.pow(2, 32 - ipMask) - 1);
		
		
		List<String> rs = new ArrayList<>();
		for (; start <= e_; start++) {
			rs.add(LongToIp(start));
		}
		
		return rs;
	}
	
	
	/**
	 * 获取下一个Ip
	 * @param ip
	 * @return
	 */
	public static String grenerateNextIp(String ip) {
		long start = ipToLong(ip);
		return LongToIp(start + 1);
	}
	
	
	private static long ipToLong(String ip) {
		String[] ips = ip.split("\\.");
		int[] s = { Integer.parseInt(ips[0]), Integer.parseInt(ips[1]), Integer.parseInt(ips[2]),
				Integer.parseInt(ips[3]) };
		long s_ = (long) (s[0] * Math.pow(2, 24) + s[1] * Math.pow(2, 16) + s[2] * Math.pow(2, 8) + s[3]);
		return s_;
	}
	
	
	private static String LongToIp(long ip) {
		int i, j, k, l;
		String binaryString = Long.toBinaryString(ip);
		if (binaryString.length() < 32) {
			binaryString = StringUtils.leftPad(binaryString, 32, '0');
		}
		
		i = Integer.parseInt(String.valueOf(binaryString).substring(0, 8), 2);
		j = Integer.parseInt(String.valueOf(binaryString).substring(8, 16), 2);
		k = Integer.parseInt(String.valueOf(binaryString).substring(16, 24), 2);
		l = Integer.parseInt(String.valueOf(binaryString).substring(24, 32), 2);
		
		return i + "." + j + "." + k + "." + l;
	}
	
}
