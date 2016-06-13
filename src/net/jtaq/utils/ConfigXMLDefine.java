package net.jtaq.utils;

public class ConfigXMLDefine 
{
	static String databasename;
	static String username;
	static String password;

	public static String getPassword() 
	{
		return password;
	}

	public static void setPassword(String password) 
	{
		ConfigXMLDefine.password = password;
	}

	public static String getUsername() 
	{
		return username;
	}

	public static void setUsername(String username) 
	{
		ConfigXMLDefine.username = username;
	}

	public static String getDatabasename() 
	{
		return databasename;
	}

	public static void setDatabasename(String databasename) 
	{
		ConfigXMLDefine.databasename = databasename;
	}
}
