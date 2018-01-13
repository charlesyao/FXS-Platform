package com.fxs.platform.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getCurrentDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(new Date());
	}
	
	public static String getFetureDate(String source, int past) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = sdf.parse(source, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.DATE, past);
		
		return sdf.format(calendar.getTime());
	}
}
