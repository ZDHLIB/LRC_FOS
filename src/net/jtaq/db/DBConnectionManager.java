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

	//����DBConnectionManagerΨһʵ��,����ǵ�һ�ε��ã��򴴽���ʵ��
	static synchronized public DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		clients++;
		return instance;
	}
	
	//˽�й��캯������ֹ�������󴴽�����ʵ��
	private DBConnectionManager() {
		init();
	}

	/**
	 * �����Ӷ��󷵻ظ�������ָ�������ӳ�
	 * 
	 * @param name
	 *            �������ļ��ж�������ӳ�����
	 * @param con
	 *            ���Ӷ���
	 */
	public void freeConnection(String name, Connection con) {
		//��ȡָ�������ӳض���
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		
		//�����Ӷ��󷵻ظ�ָ�������ӳ�
		if (pool != null) {
			pool.freeConnection(con);
		}
	}

	/**
	 * ���һ�����õ�(���е�)���ӡ����û�п�������,������������С���������������,�򴴽�������һ���µ�����
	 * 
	 * @param name �������ļ��ж�������ӳ�����
	 * 
	 * @return Connection �������ӻ�null
	 */
	public Connection getConnection(String name) {
		//��ȡָ�������ӳض���
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		
		//��ָ�������ӳ��л�ȡһ�����õ����Ӷ���
		if (pool != null) {
			return pool.getConnection();
		}
		return null;
	}

	/**
	 * ���һ���������ӣ���û�п������ӣ�������������С��������������ƣ��򴴽������������ӡ�
	 * ����,��ָ����ʱ���ڵȴ������߳��ͷ����ӡ�
	 * 
	 * @param name ���ӳ�����
	 * 
	 * @param time �Ժ���Ƶĵȴ�ʱ��
	 * 
	 * @return Connection �������ӻ�null
	 */
	public Connection getConnection(String name, long time) {
		//��ȡָ�������ӳض���
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		//��ָ�������ӳ��л�ȡ���Ӷ������ó�ʱʱ��
		if (pool != null) {
			return pool.getConnection(time);
		}
		return null;
	}

	/**
	 * �ر���������,�������������ע��
	 */
	@SuppressWarnings("unchecked")
	public synchronized void release() {
		// �ȴ�ֱ�����һ���ͻ�������ñ�����
		if (--clients != 0) {
			return;
		}

		//��ȡ���е����ӳض���
		Enumeration allPools = pools.elements();
		
		//�ر��������ӳ��е�����
		while (allPools.hasMoreElements()) {
			DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
			pool.release();
		}
		
		//��ȡ������ע�����������
		Enumeration allDrivers = drivers.elements();
		
		//����������ע�����������
		while (allDrivers.hasMoreElements()) {
			Driver driver = (Driver) allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				log("����JDBC�������� " + driver.getClass().getName() + "��ע��");
			} catch (SQLException e) {
				log(e, "�޷���������JDBC���������ע��: " + driver.getClass().getName());
			}
		}
	}

	/**
	 * ����ָ�����Դ������ӳ�ʵ����
	 * 
	 * @param props ���ӳ�����
	 */
	@SuppressWarnings("unchecked")
	private void createPools(Properties props) {
		// ��ȡϵͳ����
		PropertyResourceBundle resourceBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(Configfilename);
		// ��ϵͳ���ø�ֵ�������
		Enumeration enu = resourceBundle.getKeys();

		while (enu.hasMoreElements()) {
			String propertyName = enu.nextElement().toString();

			if (propertyName.endsWith(".url")) {
				String poolName = propertyName.substring(0, propertyName
						.lastIndexOf("."));
				String url = resourceBundle.getString(poolName + ".url");
				if (url == null) {
					log("û��Ϊ���ӳ�" + poolName + "ָ��URL");
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
					log("������������������: " + maxconn + " �����ӳ�: " + poolName);
					max = 0;
				}
				
				DBConnectionPool pool = new DBConnectionPool(poolName, url,
						dbInfo, max);
				pools.put(poolName, pool);
				log("�ɹ��������ӳ�" + poolName);
			}
		}
	}

	/**
	 * ��ȡ������ɳ�ʼ��
	 */
	private void init() {
		InputStream is = getClass().getResourceAsStream("db.properties");
		Properties dbProps = new Properties();
		
		try {
			dbProps.load(is);
		} catch (Exception e) {
			System.err.println("���ܶ�ȡ�����ļ���"
					+ "��ȷ��db.properties��CLASSPATHָ����·����");
			return;
		}
		
		String logFile = dbProps.getProperty("logfile", "newslog.txt");
		
		try {
			log = new PrintWriter(new FileWriter(logFile, true), true);
		} catch (IOException e) {
			System.err.println("�޷�����־�ļ�: " + logFile);
			log = new PrintWriter(System.err);
		}
		loadDrivers(dbProps);
		createPools(dbProps);
	}

	/**
	 * װ�غ�ע������JDBC��������
	 * 
	 * @param props ����
	 */
	@SuppressWarnings("unchecked")
	private void loadDrivers(Properties props) {
		PropertyResourceBundle resourceBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(Configfilename);
		
		// ��ϵͳ���ø�ֵ�������
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
				log("�ɹ�ע��JDBC��������" + driverClassName);
			} catch (Exception e) {
				log("�޷�ע��JDBC��������: " + driverClassName + ", ����: " + e);
			}
		}
	}

	/**
	 * ���ı���Ϣд����־�ļ�
	 */
	private void log(String msg) {
		log.println(new Date() + ": " + msg);
	}

	/**
	 * ���ı���Ϣ���쳣д����־�ļ�
	 */
	private void log(Throwable e, String msg) {
		log.println(new Date() + ": " + msg);
		e.printStackTrace(log);
	}
}