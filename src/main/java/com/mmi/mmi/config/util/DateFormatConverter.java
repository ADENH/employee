package com.mmi.mmi.config.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatConverter {
	
	public Date dateConverter(String tanggal) throws ParseException {
//		DateFormat formatIncoming = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		SimpleDateFormat formatOutgoing = new SimpleDateFormat("yyyy-MM-dd");
//		TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
		
//		System.out.println(tz.getDisplayName(false, TimeZone.SHORT, Locale.ENGLISH)); // WIB

//		formatOutgoing.setTimeZone(tz);
//		String s = formatOutgoing.format(formatIncoming.parse("Tue Mar 03 00:00:00 WIB 2015"));

		
		Date date = formatOutgoing.parse(tanggal);
		return date;
	}
	
}
