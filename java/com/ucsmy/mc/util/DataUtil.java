/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.util;

/**
 * Description:时间工具类
 * Time:2015年12月2日下午6:03:39
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class DataUtil {
	
    /**
     * 浮点型转换成汉字数字
     * @return
     */
    public static String douToChineseNumeral(double num,String endChar){
    	DataUtil.ChineseNumeral chineseNumeral =new DataUtil().new ChineseNumeral();
    	return chineseNumeral.formatDecimal(num).concat(endChar==null?"":endChar);
    }

    /**
     * 整型转换成汉字数字
     * @return
     */
    public static String intToChineseNumeral(int num,String endChar){
    	DataUtil.ChineseNumeral chineseNumeral =new DataUtil().new ChineseNumeral();
    	return chineseNumeral.foematInteger(num).concat(endChar==null?"":endChar);
    }
    
    /**
     * 数字处理成员内部类 start
     * @author Administrator
     *
     */
     class ChineseNumeral {
    	String[] units = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿", "万亿" };
    	char[] numArray = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
    	
    	private  String foematInteger(int num) {
    		char[] val = String.valueOf(num).toCharArray();
    		int len = val.length;
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < len; i++) {
    			String m = val[i] + "";
    			int n = Integer.valueOf(m);
    			boolean isZero = n == 0;
    			String unit = units[(len - 1) - i];
    			if (isZero) {
    				if ('0' == val[i - 1]) {
    					// not need process if the last digital bits is 0
    					continue;
    				} else {
    					// no unit for 0
    					sb.append(numArray[n]);
    				}
    			} else {
    				sb.append(numArray[n]);
    				sb.append(unit);
    			}
    		}
    		return sb.toString();
    	}
    	private  String formatDecimal(double decimal) {
    		String decimals = String.valueOf(decimal);
    		int decIndex = decimals.indexOf(".");
    		int integ = Integer.valueOf(decimals.substring(0, decIndex));
    		int dec = Integer.valueOf(decimals.substring(decIndex + 1));
    		String result = foematInteger(integ) + "." + formatFractionalPart(dec);
    		return result;
    	}
    	
    	private  String formatFractionalPart(int decimal) {
    		char[] val = String.valueOf(decimal).toCharArray();
    		int len = val.length;
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < len; i++) {
    			int n = Integer.valueOf(val[i] + "");
    			sb.append(numArray[n]);
    		}
    		return sb.toString();
    	}
    }
 /**数字处理成员内部类 end*/
}
