package net.jtaq.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.jtaq.utils.ConfigXMLDefParser;
import net.jtaq.utils.ConfigXMLDefine;

public class DBConnection 
{
	public static String UserTablename = "users";
	public static String User_RoleTablename = "user_role";
	public static String RoleTablename = "role";
	public static String Role_FunctionTablename = "role_function";
	public static String FunctionTablename = "function";
	public static String Function_OperationTablename = "function_operation";
	public static String OperationTablename = "operation";
	public static String AdminTablename = "admin";
	public static String Admin_RoleTablename = "admin_role";

	public DBConnection() throws Exception 
	{
	}

	@SuppressWarnings("static-access")
	public static Connection getConnection() throws Exception 
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		ConfigXMLDefParser config = ConfigXMLDefParser.getInstance();
		ConfigXMLDefine configvo = config.getConfigXMLDefine();
		String url = "jdbc:mysql://localhost:3306/" + configvo.getDatabasename() + "?useUnicode=true&characterEncoding=utf-8";
		String dbUser = configvo.getUsername();
		String dbPwd = configvo.getPassword();
		
		return java.sql.DriverManager.getConnection(url, dbUser, dbPwd);
	}

	public static void closeConnection(Connection con) 
	{
		try 
		{
			if (con != null) 
			{
				con.close();
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void closePrepStmt(PreparedStatement prepStmt) 
	{
		try 
		{
			if(prepStmt != null) 
			{
				prepStmt.close();
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) 
	{
		try 
		{
			if(rs != null) 
			{
				rs.close();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
