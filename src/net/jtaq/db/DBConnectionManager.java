package net.jtaq.db;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class DBConnectionManager {

	static private DBConnectionManager instance; 
	static private int clients;
	static private String Configfilename = "db.properties";
	private Vector<Driver> drivers = new Vector<Driver>();
	private PrintWriter log;
	private Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();

	//返回DBConnectionManager唯一实例,如果是第一次调用，则创建该实例
	static synchronized public DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		clients++;
		return instance;
	}
	
	//私有构造函数，防止其他对象创建本类实例
	private DBConnectionManager() {
		init();
	}

	/**
	 * 将连接对象返回给由名字指定的连接池
	 * 
	 * @param name
	 *            在属性文件中定义的连接池名字
	 * @param con
	 *            连接对象
	 */
	public void freeConnection(String name, Connection con) {
		//获取指定的连接池对象
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		
		//将连接对象返回给指定的连接池
		if (pool != null) {
			pool.freeConnection(con);
		}
	}

	/**
	 * 获得一个可用的(空闲的)连接。如果没有可用连接,且已有连接数小于最大连接数限制,则创建并返回一个新的连接
	 * 
	 * @param name 在属性文件中定义的连接池名字
	 * 
	 * @return Connection 可用连接或null
	 */
	public Connection getConnection(String name) {
		//获取指定的连接池对象
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		
		//从指定的连接池中获取一个可用的连接对象
		if (pool != null) {
			return pool.getConnection();
		}
		return null;
	}

	/**
	 * 获得一个可用连接，若没有可用连接，且已有连接数小于最大连接数限制，则创建并返回新连接。
	 * 否则,在指定的时间内等待其它线程释放连接。
	 * 
	 * @param name 连接池名字
	 * 
	 * @param time 以毫秒计的等待时间
	 * 
	 * @return Connection 可用连接或null
	 */
	public Connection getConnection(String name, long time) {
		//获取指定的连接池对象
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		//从指定的连接池中获取连接对象并设置超时时间
		if (pool != null) {
			return pool.getConnection(time);
		}
		return null;
	}

	/**
	 * 关闭所有连接,撤销驱动程序的注册
	 */
	@SuppressWarnings("unchecked")
	public synchronized void release() {
		// 等待直到最后一个客户程序调用本方法
		if (--clients != 0) {
			return;
		}

		//获取所有的连接池对象
		Enumeration allPools = pools.elements();
		
		//关闭所有连接池中的连接
		while (allPools.hasMoreElements()) {
			DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
			pool.release();
		}
		
		//获取所有已注册的驱动程序
		Enumeration allDrivers = drivers.elements();
		
		//撤销所有已注册的驱动程序
		while (allDrivers.hasMoreElements()) {
			Driver driver = (Driver) allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				log("撤销JDBC驱动程序 " + driver.getClass().getName() + "的注册");
			} catch (SQLException e) {
				log(e, "无法撤销下列JDBC驱动程序的注册: " + driver.getClass().getName());
			}
		}
	}

	/**
	 * 根据指定属性创建连接池实例。
	 * 
	 * @param props 连接池属性
	 */
	@SuppressWarnings("unchecked")
	private void createPools(Properties props) {
		// 读取系统配置
		PropertyResourceBundle resourceBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(Configfilename);
		// 将系统设置赋值给类变量
		Enumeration enu = resourceBundle.getKeys();

		while (enu.hasMoreElements()) {
			String propertyName = enu.nextElement().toString();

			if (propertyName.endsWith(".url")) {
				String poolName = propertyName.substring(0, propertyName
						.lastIndexOf("."));
				String url = resourceBundle.getString(poolName + ".url");
				if (url == null) {
					log("没有为连接池" + poolName + "指定URL");
					continue;
				}
				@SuppressWarnings("unused")
				String driver = "oracle.jdbc.driver.OracleDriver";
				String user = "system";
				String password = "zj";
				String dbip = "localhost";
				String dbport = "1521";
				String dbuid = "lingdot";
				String maxconn = "10";
				
				if (propertyName.endsWith(".driver"))
					driver = resourceBundle.getString(poolName + ".driver");
				if (propertyName.endsWith(".username"))
					user = resourceBundle.getString(poolName + ".username");
				if (propertyName.endsWith(".password"))
					password = resourceBundle.getString(poolName + ".password");
				if (propertyName.endsWith(".username"))
					user = resourceBundle.getString(poolName + ".username");
				if (propertyName.endsWith(".db_ip"))
					dbip = resourceBundle.getString(poolName + ".db_ip");
				if (propertyName.endsWith(".db_port"))
					dbport = resourceBundle.getString(poolName + ".db_port");
				if (propertyName.endsWith(".db_uid"))
					dbuid = resourceBundle.getString(poolName + ".db_uid");
				if (propertyName.endsWith(".maxconn"))
					maxconn = resourceBundle.getString(poolName + ".maxconn");

				String dbInfo = user + "/" + password + "@" + dbip + ":"
						+ dbport + ":" + dbuid;
				int max;
				
				try {
					max = Integer.valueOf(maxconn).intValue();
				} catch (NumberFormatException e) {
					log("错误的最大连接数限制: " + maxconn + " 。连接池: " + poolName);
					max = 0;
				}
				
				DBConnectionPool pool = new DBConnectionPool(poolName, url,
						dbInfo, max);
				pools.put(poolName, pool);
				log("成功创建连接池" + poolName);
			}
		}
	}

	/**
	 * 读取属性完成初始化
	 */
	private void init() {
		InputStream is = getClass().getResourceAsStream("db.properties");
		Properties dbProps = new Properties();
		
		try {
			dbProps.load(is);
		} catch (Exception e) {
			System.err.println("不能读取属性文件。"
					+ "请确保db.properties在CLASSPATH指定的路径中");
			return;
		}
		
		String logFile = dbProps.getProperty("logfile", "newslog.txt");
		
		try {
			log = new PrintWriter(new FileWriter(logFile, true), true);
		} catch (IOException e) {
			System.err.println("无法打开日志文件: " + logFile);
			log = new PrintWriter(System.err);
		}
		loadDrivers(dbProps);
		createPools(dbProps);
	}

	/**
	 * 装载和注册所有JDBC驱动程序。
	 * 
	 * @param props 属性
	 */
	@SuppressWarnings("unchecked")
	private void loadDrivers(Properties props) {
		PropertyResourceBundle resourceBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(Configfilename);
		
		// 将系统设置赋值给类变量
		Enumeration enu = resourceBundle.getKeys();
		String driverClassName = "oracle.jdbc.driver.OracleDriver";
		
		while (enu.hasMoreElements()) {
			String propertyName = enu.nextElement().toString();

			if (propertyName.endsWith(".driver")) {
				String poolName = propertyName.substring(0, propertyName
						.lastIndexOf("."));
				driverClassName = props.getProperty(poolName + ".driver");
			}

			try {
				Driver driver = (Driver) Class.forName(driverClassName)
						.newInstance();
				DriverManager.registerDriver(driver);
				drivers.addElement(driver);
				log("成功注册JDBC驱动程序" + driverClassName);
			} catch (Exception e) {
				log("无法注册JDBC驱动程序: " + driverClassName + ", 错误: " + e);
			}
		}
	}

	/**
	 * 将文本信息写入日志文件
	 */
	private void log(String msg) {
		log.println(new Date() + ": " + msg);
	}

	/**
	 * 将文本信息与异常写入日志文件
	 */
	private void log(Throwable e, String msg) {
		log.println(new Date() + ": " + msg);
		e.printStackTrace(log);
	}
}