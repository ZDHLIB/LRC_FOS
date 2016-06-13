package net.jtaq.utils;

import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * *************************************************************************************************
 * 配置文件的格式： 
 * <config> 
 * 	<database> 
 * 		<databasename value="lrc"/> 
 * 		<username value="root"/> 
 * 		<password value="xiaoluo"/> 
 *	</database> 
 *	<counter>
 * 		<counterfilepath value="d:/counter.inf"/> 
 *	</counter> 
 *</config>
 * *************************************************************************************************
 */

public class ConfigXMLDefParser 
{
	private final String dbConfigFile = "/sys-config.xml";
	private static final ConfigXMLDefParser instance = new ConfigXMLDefParser();
	private ConfigXMLDefine configXMLDefine = new ConfigXMLDefine();

	public static ConfigXMLDefParser getInstance() 
	{
		return instance;
	}

	private ConfigXMLDefParser() 
	{
		init();
	}

	@SuppressWarnings("static-access")
	private synchronized void init() 
	{
		try 
		{
			SAXBuilder builder = new SAXBuilder();
			String path = ConfigXMLDefParser.class.getResource(dbConfigFile).getFile();
			Document doc = builder.build(path);
			Element root = doc.getRootElement();
			Element dbRoot = root.getChild("database");
			String databasename = null;
			String username = null;
			String password = null;

			Element usernameElement = dbRoot.getChild("username");
			username = usernameElement.getAttribute("value").getValue();

			Element passwordElement = dbRoot.getChild("password");
			password = passwordElement.getAttribute("value").getValue();

			Element databasenameElement = dbRoot.getChild("databasename");
			databasename = databasenameElement.getAttribute("value").getValue();

			configXMLDefine.setDatabasename(databasename);
			configXMLDefine.setUsername(username);
			configXMLDefine.setPassword(password);
		} 
		catch(JDOMException e) 
		{
			e.printStackTrace();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}

	public ConfigXMLDefine getConfigXMLDefine() 
	{
		return configXMLDefine;
	}
}
