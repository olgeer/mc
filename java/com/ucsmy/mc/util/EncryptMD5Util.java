package com.ucsmy.mc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:MD5加密类
 * Time:2015年12月4日下午4:33:28
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class EncryptMD5Util {
	
	public static String eccrypt(String info){		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(info.getBytes());
			byte[]byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			//32位加密
			return buf.toString();
			// 16位的加密
			//return buf.toString().substring(8, 24); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
