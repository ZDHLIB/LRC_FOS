package net.lrc.db;

import java.sql.*;

public class Mysql 
{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement prepstmt = null;
	private BeansConstants CONST = BeansConstants.getInstance("mysql");

	@SuppressWarnings("static-access")
	public Mysql() throws Exception 
	{
		Class.forName(CONST.dbdriver);
		conn = DriverManager.getConnection(CONST.dburl);
		stmt = conn.createStatement();
	}

	@SuppressWarnings("static-access")
	public Mysql(String sql) throws Exception 
	{
		Class.forName(CONST.dbdriver);
		conn = DriverManager.getConnection(CONST.dburl);
		//call the local method
		prepareStatement(sql);
	}

	public void prepareStatement(String sql) throws SQLException 
	{
		prepstmt = conn.prepareStatement(sql);
	}

	public Connection getConn() 
	{
		return conn;
	}

	public Statement getStmt() 
	{
		return stmt;
	}

	public PreparedStatement getPrepstmt() 
	{
		return prepstmt;
	}

	public ResultSet executeQuery(String sql) throws SQLException 
	{
		if(stmt != null) 
		{
			return(stmt.executeQuery(sql));
		} 
		else 
		{
			return null;
		}
	}

	public ResultSet executeQuery() throws SQLException
	{
		if(prepstmt != null) 
		{
			return(prepstmt.executeQuery());
		} 
		else 
		{
			return null;
		}
	}

	public void executeUpdate(String sql) throws SQLException 
	{
		if(stmt != null) 
		{
			stmt.executeUpdate(sql);
		}
	}

	public void executeUpdate() throws SQLException 
	{
		if(prepstmt != null) 
		{
			prepstmt.executeUpdate();
		}
	}

	public void close() throws Exception 
	{
		if(stmt != null) 
		{
			stmt.close();
			stmt = null;
		}
		
		if(prepstmt != null) 
		{
			prepstmt.close();
			prepstmt = null;
		}
		
		conn.close();
		conn = null;
	}
}
