package net.lrc.db;

import net.jtaq.utils.ConfigXMLDefParser;
import net.jtaq.utils.ConfigXMLDefine;

public class BeansConstants 
{
	public static String dbdriver;
	public static String dburl;
	private String database = null;
	private String usr = null;
	private String passwd = null;

	@SuppressWarnings("static-access")
	//database message
	private BeansConstants(String dbms) 
	{
		ConfigXMLDefParser config = ConfigXMLDefParser.getInstance();
		ConfigXMLDefine configvo = config.getConfigXMLDefine();
		this.database = configvo.getDatabasename();
		this.usr = configvo.getUsername();
		this.passwd = configvo.getPassword();

		if(dbms == "mysql") 
		{
			this.dbdriver = "com.mysql.jdbc.Driver";
			this.dburl = "jdbc:mysql://localhost:3306/" + this.database + "?useUnicode=true" + "&user=" + this.usr + "&password=" + this.passwd + "&characterEncoding=utf-8";
		} 
		else 
		{
			this.dbdriver = "none";
		}
	}

	public static BeansConstants getInstance(String dbms) 
	{
		BeansConstants bc = new BeansConstants(dbms);
		return (bc);
	}

	public String getDatabase() 
	{
		return database;
	}

	public String getUsr() 
	{
		return usr;
	}

	public String getPasswd() 
	{
		return passwd;
	}

	public String getDbdriver() 
	{
		return dbdriver;
	}

	public String getDburl() 
	{
		return dburl;
	}

	public void setDatabase(String database) 
	{
		this.database = database;
	}

	public void setUsr(String usr) 
	{
		this.usr = usr;
	}

	public void setPasswd(String passwd) 
	{
		this.passwd = passwd;
	}
}
