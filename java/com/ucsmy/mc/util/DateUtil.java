/*
 * Copyright (c) 2016
 * 广东网金控股股份有限公司(http://www.ucsmy.com) 
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description:时间工具类
 * Time:2015年12月2日下午6:03:39
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class DateUtil {
	
	public static SimpleDateFormat chineseFormat = new SimpleDateFormat("yyyy年MM月dd日");
	public static SimpleDateFormat chineseFullFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	private static SimpleDateFormat sdf = new SimpleDateFormat();
	private static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	
	/**
	 * 把yyyy-MM-dd类型的时间转成java.util.Date类型
	 * @param adateStrteStr 日期字符串
	 * @return 返回转换成功的Date类型的日期
	 */
	public static Date convertDate(String adateStrteStr) {
		Date date = null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			date=sdf.parse(adateStrteStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 把yyyy-MM-dd HH:mm:ss类型的时间转成java.util.Date类型
	 * @param dateTime 日期字符串
	 * @return 返回转换成功的Date类型的日期
	 */
	public static Date convertDateTime(String dateTime) {
		Date date = null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			sdf.setLenient(false);
			date=sdf.parse(dateTime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return date;
	}
	

    /**
     * 根据格式返回日期
     *
     * @param format
     * @return
     */
    public static String getDate(String format) {
        sdf.applyPattern(format);
        return sdf.format(new Date());
    }

    /**
     * 根据格式,日期 返回日期
     *
     * @param format
     * @return
     */
    public static String formatDate(String format,Date date) {
        sdf.applyPattern(format);
        return sdf.format(date);
    }

    public static String currentTime(){
        return formatDate("yyyy-MM-dd HH:mm:ss",new Date());
    }

    /**
     * 获取今天是星期几
     * @return
     */
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 输入时期是否今天
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(new Date());
        Calendar inputDate = Calendar.getInstance();
        inputDate.setTime(date);

        if (nowCalendar.get(Calendar.YEAR) == inputDate.get(Calendar.YEAR)
                && nowCalendar.get(Calendar.MONTH) == inputDate.get(Calendar.MONTH)
                && nowCalendar.get(Calendar.DAY_OF_MONTH) == inputDate.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }
    
    /**
     * 获得当前时间
     * @return
     */
    public static Timestamp getCurrentTime(){
    	return new Timestamp(System.currentTimeMillis());
    }
    
    
    /**
     * 两个日期相差的天数（非严格意义上的天数，只考虑到天，不考虑时分秒等）
     * @param bfDate
     * @param afDate
     * @return
     */
    public static int getIntervalDays(Date bfDate, Date afDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bfDate);      
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(afDate);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

     }
    
    /**
     * 几天前
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
        return now.getTime();  
    }    
    
    /**
     * 几天后
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return now.getTime();  
    }

}
