package com.sosop.service.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);
	
	// 本月的第一天
	public static String getCurMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	// 本月的最后一天
	public static String getCurMonthLastDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	// 获得昨天
	public static String getYesterDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
	}

	// 获得一周的开始日期
	public static String getLastWeekMonday() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.WEEK_OF_YEAR, -1);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(c.getTime());
	}

	// 获得一周的开始日期
	public static String getLastWeekSunday() {
		Calendar c = Calendar.getInstance();
		// c.add(Calendar.WEEK_OF_YEAR, -1);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(c.getTime());
	}

	// 获得一周的开始日期
	public static String getLastMonthBegin() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(c.getTime());
	}

	// 获得一周的开始日期
	public static String getLastMonthEnd() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(c.getTime());
	}

	// 获得几天前
	public static String getBeforeDays(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -days);
		// c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
	}

	// 获得几月前
	public static String getBeforeMonths(int months) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -months);
		// c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
	}
	
	public static String getBeforeOrAfterDate(String pattern, String date, int step) {
		Calendar c = Calendar.getInstance();
		try {
			Date d = new SimpleDateFormat(pattern).parse(date);
			c.setTime(d);
			c.add(Calendar.DATE, step);
		} catch (ParseException e) {
			// e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new SimpleDateFormat(pattern).format(c.getTime());
	}
	
	public static String format(String pattern, Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static String format(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static String timestamp() {
		return format("yyyyMMddHHmmss", new Date());
	}
	
	public static String timestamp(int start, int len) {
		String ts = String.valueOf(new Date().getTime());
		return ts.substring(start, len);
	}
	
	public static Date formatToDate(String pattern, String date) {
		Date d = null;
		try {
			 d = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			// e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return d;
	}
}
