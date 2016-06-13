package net.jtaq.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import net.jtaq.db.DBConnection;
import net.jtaq.utils.AdminDetails;
import net.jtaq.utils.MD5EncryptUtils;
import net.jtaq.utils.RoleDetails;
import net.lrc.javabean.CommonBean;

public class AdminAdmin {

	private ArrayList<AdminDetails> admins;

	/**
	 * to add a administrator's information
	 * 
	 * @param adminName
	 * @param password
	 * @param operatorID
	 * @return
	 * @throws Exception
	 */
	public boolean addAdminInfo(String adminName, String password,
			int operatorID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		Statement st = null;

		try {
			con = DBConnection.getConnection();
			password = MD5EncryptUtils.MD5Encode(password);
			con.setAutoCommit(false);
			st = con.createStatement();

			//the administrator's name must be unique
			String checkString = "select * from " + DBConnection.AdminTablename
					+ " where Admin_Name='" + adminName + "'";
			String insertString = "INSERT INTO "
					+ DBConnection.AdminTablename
					+ "(Admin_Name,Password,CreateTime,UpdateTime,OperatorID) values('"
					+ adminName + "','" + password + "',curdate(),curdate(),"
					+ operatorID + ")";
			rs = st.executeQuery(checkString);

			if (rs.next()) {
				return false;
			} else {
				st.executeUpdate(insertString);
			}

			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (Exception ex) {
			con.rollback();
			throw ex;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
	}

	public boolean updateAdminInfo(int Admin_ID, String Admin_Name,
			String Password, int OperatorID) throws Exception {
		Connection con = null;

		ResultSet rs = null;
		Statement st = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			Password = MD5EncryptUtils.MD5Encode(Password);
			String updateString = "update " + DBConnection.AdminTablename
					+ "  set  Admin_Name='" + Admin_Name + "',Password='"
					+ Password + "',OperatorID=" + OperatorID
					+ ",UpdateTime=curdate() where Admin_ID=" + Admin_ID;
			st.executeUpdate(updateString);
			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (Exception ex) {
			con.rollback();
			throw ex;
		} finally {
			DBConnection.closeResultSet(rs);
			st.close();
			DBConnection.closeConnection(con);

		}

	}

	public boolean ChangeAdminPassword(int Admin_ID, String oldpassword,
			String newPassword) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			oldpassword = MD5EncryptUtils.MD5Encode(oldpassword);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String checkString = "select * from " + DBConnection.AdminTablename
					+ " where Admin_ID=" + Admin_ID + " and Password='"
					+ oldpassword + "'";
			rs = st.executeQuery(checkString);
			if (!rs.next()) {
				return false;
			} else {
				newPassword = MD5EncryptUtils.MD5Encode(newPassword);
				String updateString = "update " + DBConnection.AdminTablename
						+ "  set  Password='" + newPassword
						+ "',UpdateTime=curdate()  where Admin_ID=" + Admin_ID;
				st.executeUpdate(updateString);
				con.commit();
				con.setAutoCommit(true);
				return true;
			}
		} catch (Exception ex) {
			con.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
			st.close();
		}

	}

	public int getNumberOfAdmins() throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		admins = new ArrayList<AdminDetails>();
		try {
			con = DBConnection.getConnection();
			String selectStatement = "select * from "
					+ DBConnection.AdminTablename + "   order by Admin_ID asc";
			prepStmt = con.prepareStatement(selectStatement);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				AdminDetails ad = new AdminDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(3), rs
						.getDate(4), rs.getInt(5));
				admins.add(ad);
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
		return admins.size();

	}

	public Collection getAdmins(int start, int pagesize) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		admins = new ArrayList<AdminDetails>();
		try {
			con = DBConnection.getConnection();
			String selectStatement = "select * from "
					+ DBConnection.AdminTablename + "   order by Admin_ID asc";
			prepStmt = con.prepareStatement(selectStatement);
			rs = prepStmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			if (pagesize == 0)
				pagesize = numberOfColumns;

			int i = 0;
			rs.absolute(start);
			rs.previous();
			while (rs.next()) {
				AdminDetails ad = new AdminDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(3), rs
						.getDate(4), rs.getInt(5));

				admins.add(ad);
				i++;
				if (i == pagesize)
					break;
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

		return admins;

	}

	/**
	 * to get the administrator list
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Collection getAdmins() throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		admins = new ArrayList<AdminDetails>();

		try {
			con = DBConnection.getConnection();
			String selectStatement = "select * from "
					+ DBConnection.AdminTablename + " order by Admin_ID asc";
			prepStmt = con.prepareStatement(selectStatement);
			rs = prepStmt.executeQuery();
			CommonBean comm = new CommonBean();

			while (rs.next()) {
				AdminDetails ad = new AdminDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				ad.setDeptName(comm.getDepNamebyAdminID(ad.getAdminID()));
				admins.add(ad);
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}

		return admins;
	}

	public Collection getAdmins(String searchIndex, String value, int start,
			int pagesize) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		admins = new ArrayList<AdminDetails>();
		try {
			String selectstatement = null;
			con = DBConnection.getConnection();

			if (searchIndex != null && !searchIndex.equals("")) {
				if (!searchIndex.equalsIgnoreCase("all")) {
					if (searchIndex.equalsIgnoreCase("AdminName"))
						selectstatement = "select * from "
								+ DBConnection.AdminTablename
								+ "   where Admin_Name=? order by  Admin_ID asc";

					prepStmt = con.prepareStatement(selectstatement);
					prepStmt.setString(1, value);
					rs = prepStmt.executeQuery();

				} else if (searchIndex.equalsIgnoreCase("all")) {
					Statement stmt = con.createStatement();
					rs = stmt.executeQuery("select * from "
							+ DBConnection.AdminTablename
							+ "   order by Admin_ID asc");

				}

			}

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			if (pagesize == 0)
				pagesize = numberOfColumns;

			int i = 0;
			rs.absolute(start);
			rs.previous();
			while (rs.next()) {
				AdminDetails ad = new AdminDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(3), rs
						.getDate(4), rs.getInt(5));
				admins.add(ad);
				i++;
				if (i == pagesize)
					break;
			}

			return admins;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}
	}

	public Collection getAdmins(String name, String value) throws Exception {
		return getAdmins(name, value, 0, 0);

	}

	public AdminDetails getAdminDetails(int AdminID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			String selectstatement = "select * from "
					+ DBConnection.AdminTablename + " where Admin_ID=? ";
			prepStmt = con.prepareStatement(selectstatement);
			prepStmt.setInt(1, AdminID);
			rs = prepStmt.executeQuery();

			if (rs.next()) {
				AdminDetails ad = new AdminDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));

				return ad;
			} else {
				return null;
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
	}

	public AdminDetails getAdminDetails(String adminName, String password)
			throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			password = MD5EncryptUtils.MD5Encode(password);
			String selectstatement = "select * from "
					+ DBConnection.AdminTablename
					+ " where Admin_Name=? and Password=?";
			prepStmt = con.prepareStatement(selectstatement);
			prepStmt.setString(1, adminName);
			prepStmt.setString(2, password);
			rs = prepStmt.executeQuery();
			if (rs.next()) {
				AdminDetails ad = new AdminDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				return ad;
			} else {
				return null;
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public boolean deleteAdmin(int AdminID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String checkString = "select * from " + DBConnection.AdminTablename
					+ " where Admin_ID=?";
			String deleteString = "delete from " + DBConnection.AdminTablename
					+ " where Admin_ID=?";
			prepStmt = con.prepareStatement(checkString);
			prepStmt.setInt(1, AdminID);
			rs = prepStmt.executeQuery();

			if (!rs.next()) {
				return false;
			} else {
				prepStmt = con.prepareStatement(deleteString);
				prepStmt.setInt(1, AdminID);
				prepStmt.executeUpdate();

				deleteString = "delete from "
						+ DBConnection.Admin_RoleTablename
						+ " where Admin_ID=?";
				prepStmt.setInt(1, AdminID);
				prepStmt.executeUpdate();
			}

			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (Exception ex) {
			con.rollback();
			throw ex;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
	}

	public int getNumberofRolesOfAdmin(int Admin_ID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<RoleDetails> adminRoles = new ArrayList<RoleDetails>();
		try {
			con = DBConnection.getConnection();
			String selectStatement = "select * from "
					+ DBConnection.RoleTablename
					+ "  where Role_ID in (select Role_ID from "
					+ DBConnection.Admin_RoleTablename
					+ " where Admin_ID=? )order by Role_ID asc";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, Admin_ID);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				RoleDetails rd = new RoleDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getDate(5), rs
								.getInt(6));
				adminRoles.add(rd);
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
		return adminRoles.size();

	}

	@SuppressWarnings("unchecked")
	public Collection getRolesOfAdmin(int Admin_ID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<RoleDetails> userRoles = new ArrayList<RoleDetails>();
		
		try {
			con = DBConnection.getConnection();
			String selectStatement = "select * from "
					+ DBConnection.RoleTablename
					+ " where Role_ID in(select Role_ID from "
					+ DBConnection.Admin_RoleTablename
					+ " where Admin_ID=?) order by Role_ID asc";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, Admin_ID);
			rs = prepStmt.executeQuery();
			
			while (rs.next()) {
				RoleDetails rd = new RoleDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getDate(5), rs
								.getInt(6));
				userRoles.add(rd);
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
		
		return userRoles;
	}

	public boolean addRoleOfAdmin(int Admin_ID, int Role_ID, int OperatorID)
			throws Exception {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String insertString = "insert into "
					+ DBConnection.Admin_RoleTablename
					+ "(Admin_ID,Role_ID,CreateTime,UpdateTime,OperatorID)values("
					+ Admin_ID + "," + Role_ID + ",curdate(),curdate(),"
					+ OperatorID + ")";
			st.executeUpdate(insertString);
			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (Exception ex) {
			con.rollback();
			throw ex;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeConnection(con);
			st.close();
		}

	}

	public boolean deleteRoleOfAdmin(int Admin_ID, int Role_ID)
			throws Exception {
		Connection con = null;

		ResultSet rs = null;
		PreparedStatement prepStmt = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String insertString = "delete from "
					+ DBConnection.Admin_RoleTablename
					+ " where Admin_ID=? and Role_ID=?";
			prepStmt = con.prepareStatement(insertString);
			prepStmt.setInt(1, Admin_ID);
			prepStmt.setInt(2, Role_ID);
			prepStmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (Exception ex) {
			con.rollback();
			throw ex;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeConnection(con);
			DBConnection.closePrepStmt(prepStmt);
		}

	}

	public boolean deleteALlRoleOfAdmin(int Admin_ID) throws Exception {
		Connection con = null;

		ResultSet rs = null;
		PreparedStatement prepStmt = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String insertString = "delete from "
					+ DBConnection.Admin_RoleTablename + " where Admin_ID=?  ";
			prepStmt = con.prepareStatement(insertString);
			prepStmt.setInt(1, Admin_ID);

			prepStmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (Exception ex) {
			con.rollback();
			throw ex;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeConnection(con);
			DBConnection.closePrepStmt(prepStmt);
		}

	}
}
