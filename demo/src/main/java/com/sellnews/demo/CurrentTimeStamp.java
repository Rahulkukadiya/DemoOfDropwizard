package com.sellnews.demo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class CurrentTimeStamp {
	public static Timestamp generateTimeStamp() {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		return new Timestamp(now.getTime());
	}
}
