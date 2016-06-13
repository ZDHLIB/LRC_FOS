package net.jtaq.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.PropertyResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnetionForAll {
	static String url;
	static String driverClassName;
	static String username;
	static String password;
	static String type;
	static String datasource;
	private final static String fileName = "db";
	private static ThreadLocal<Connection> connection = new ThreadLocal<Connection>();

	static {
		config();
	}

	@SuppressWarnings("unchecked")
	private static void config() {
		// 读取系统配置
		PropertyResourceBundle resourceBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(fileName);
		// 将系统设置赋值给类变量
		Enumeration enu = resourceBundle.getKeys();
		while (enu.hasMoreElements()) {
			String propertyName = enu.nextElement().toString();
			if (propertyName.equals("database.url"))
				url = resourceBundle.getString("database.url");
			if (propertyName.equals("database.driver"))
				driverClassName = resourceBundle
						.getString("database.driverClassName");
			if (propertyName.equals("database.username"))
				username = resourceBundle.getString("database.username");
			if (propertyName.equals("database.password"))
				password = resourceBundle.getString("database.password");
			if (propertyName.equals("database.type"))
				type = resourceBundle.getString("database.type");
			if (propertyName.equals("database.datasource"))
				datasource = resourceBundle.getString("database.datasource");
		}
	}

	public synchronized static java.sql.Connection getConnection()
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = (Connection) connection.get();
		if (con != null && !con.isClosed()) {
			return con;
		}
		if ("pooled".equalsIgnoreCase(type)) {
			// 从JNDI中取得数据源
			try {
				Hashtable<String, String> env = new Hashtable<String, String>();
				env.put(Context.INITIAL_CONTEXT_FACTORY,
						"org.apache.commons.dbcp.BasicDataSourceFactory");
				Context ctx = new InitialContext(env);

				DataSource dataSource = (DataSource) ctx.lookup(datasource);
				con = dataSource.getConnection();
				connection.set(con);
				return con;
			} catch (NamingException e) {
				e.printStackTrace();
			}
		} else {
			// 直接使用JDBC驱动连接
			try {
				Class.forName(driverClassName).newInstance();
				con = DriverManager.getConnection(url, username, password);
				con.setAutoCommit(false);
				connection.set(con);
				return con;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void commit() {
		Connection con = (Connection) connection.get();
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback() {
		Connection con = (Connection) connection.get();
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void releaseConnection(Connection connection) {
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection = null;
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closePrepStmt(PreparedStatement prepStmt) {
		try {
			if (prepStmt != null)
				prepStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}