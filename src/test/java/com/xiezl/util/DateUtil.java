package com.xiezl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String YMD = "yyyy-MM-dd";
	public static final String YMDHMS = "yyyy-MM-dd HH:MM:ss";
	private static SimpleDateFormat ymdFormat = new SimpleDateFormat(YMD);
	private static SimpleDateFormat ymdhmsFormat = new SimpleDateFormat(YMDHMS);
	
	public static String dateToYmdString(Date date) {
		return ymdFormat.format(date);
	}
	
	public static String dateToYmdhmsString(Date date) {
		return ymdhmsFormat.format(date);
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		
		System.out.println(ymdFormat.format(date));
	}
}

