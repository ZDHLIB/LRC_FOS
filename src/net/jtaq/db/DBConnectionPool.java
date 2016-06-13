package net.jtaq.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import java.io.*;

/***************���ӳ���*************************************************/
/**
 * ���ڲ��ඨ����һ�����ӳأ����ܹ�����Ҫ�󴴽�������,ֱ��Ԥ������ ��������Ϊֹ���ڷ������Ӹ��ͻ�����֮ǰ,���ܹ���֤���ӵ���Ч�ԡ�
 */
class DBConnectionPool {
	private int checkedOut;
	private Vector<Connection> freeConnections = new Vector<Connection>();
	private int maxConn;
	private String name;
	private String URL;
	private String dbInfo;
	private PrintWriter log;

	/**
	 * �����µ����ӳ�
	 * 
	 * @param name
	 *            ���ӳ�����
	 * @param URL
	 *            ���ݿ��JDBC URL
	 * @param dbInfo
	 *            ���ݿ�������Ϣ
	 * @param maxConn
	 *            �����ӳ������������������
	 */
	public DBConnectionPool(String name, String URL, String dbInfo, int maxConn) {
		this.name = name;
		this.URL = URL;
		this.dbInfo = dbInfo;
		this.maxConn = maxConn;
	}

	/**
	 * ������ʹ�õ����ӷ��ظ����ӳ�
	 * 
	 * @param con
	 *            �ͻ������ͷŵ�����
	 */
	public synchronized void freeConnection(Connection con) {
		freeConnections.addElement(con);
		checkedOut--;
		notifyAll();
	}

	/**
	 * �����ӳػ��һ���������ӡ���û�п��е������ҵ�ǰ������С���������������,�򴴽������ӡ���ԭ���Ǽ�Ϊ���õ����Ӳ�����Ч,�������ɾ��֮,
	 * Ȼ��ݹ�����Լ��Գ����µĿ������ӡ�
	 */
	public synchronized Connection getConnection() {
		Connection con = null;
		if (freeConnections.size() > 0) {
			// ��ȡ�����е�һ����������
			con = (Connection) freeConnections.firstElement();
			freeConnections.removeElementAt(0);
			try {
				if (con.isClosed()) {
					log("�����ӳ�" + name + "ɾ��һ����Ч����");
					// �ݹ�����Լ�,�����ٴλ�ȡ��������
					con = getConnection();
				}
			} catch (SQLException e) {
				log("�����ӳ�" + name + "ɾ��һ����Ч����");
				// �ݹ�����Լ�,�����ٴλ�ȡ��������
				con = getConnection();
			}
		} else if (maxConn == 0 || checkedOut < maxConn) {
			con = newConnection();
		}
		if (con != null) {
			checkedOut++;
		}
		return con;
	}

	/**
	 * �����ӳػ�ȡ�������ӡ�����ָ���ͻ������ܹ��ȴ����ʱ��(�μ�ǰһ��getConnection()����)��
	 * 
	 * @param timeout
	 *            �Ժ���Ƶĵȴ�ʱ������
	 */
	public synchronized Connection getConnection(long timeout) {
		long startTime = new Date().getTime();
		Connection con;
		while ((con = getConnection()) == null) {
			try {
				wait(timeout);
			} catch (InterruptedException e) {
			}
			if ((new Date().getTime() - startTime) >= timeout) {
				// wait()���ص�ԭ���ǳ�ʱ
				return null;
			}
		}
		return con;
	}

	/**
	 * �ر���������
	 */
	@SuppressWarnings("unchecked")
	public synchronized void release() {
		Enumeration allConnections = freeConnections.elements();
		while (allConnections.hasMoreElements()) {
			Connection con = (Connection) allConnections.nextElement();
			try {
				con.close();
				log("�ر����ӳ�" + name + "�е�һ������");
			} catch (SQLException e) {
				log(e, "�޷��ر����ӳ�" + name + "�е�����");
			}
		}
		freeConnections.removeAllElements();
	}

	/**
	 * �����µ�����
	 */
	private Connection newConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL + dbInfo);
			log("���ӳ�" + name + "����һ���µ�����");
		} catch (SQLException e) {
			log(e, "�޷���������URL������: " + URL);
			return null;
		}
		return con;
	}

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
