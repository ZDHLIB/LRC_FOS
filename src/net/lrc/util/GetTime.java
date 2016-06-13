package net.lrc.util;

import java.util.Date;

   public class GetTime {
	   
	//system now time
	public static String gettheTime()
	{
		  Date d = new Date();   
		  java.text.SimpleDateFormat   dateFormat=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		  String s=dateFormat.format(d);   
		  return s;
	}
	
	public static String format(Date date){
		  java.text.SimpleDateFormat   dateFormat=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		  String s=dateFormat.format(date);   
		  return s;
	}
}
