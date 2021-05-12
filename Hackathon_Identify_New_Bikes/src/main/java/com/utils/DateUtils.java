package com.utils;

import java.util.Date;

public class DateUtils {
	
	
	 //Method used to print 'Date' on the extend report
	 
	
	public static String getTimeStamp() {
		Date date =new Date();
		return ( date.toString().replaceAll(" ", "_").replaceAll(":", "_"));
	}

}
