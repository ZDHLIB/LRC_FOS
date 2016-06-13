package net.lrc.util;

public class Charset {
	
	public static String toGBK(String str) throws java.io.UnsupportedEncodingException {
		return new String(str.getBytes("ISO-8859-1"),"GBK") ;
		}
	
}
