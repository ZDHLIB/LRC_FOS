package net.jtaq.managers;

import net.jtaq.db.DBConnection;
import net.jtaq.utils.*;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class RightsAdmin {

	/*
	 * 角色信息处理模块
	 * 
	 * 
	 */

	public int getNumberOfTotalRoles() throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<RoleDetails> totalRoles = new ArrayList<RoleDetails>();
		try {
			con = DBConnection.getConnection();
			String selectStatement = "select * from "
					+ DBConnection.RoleTablename + "  order by Role_ID asc";
			prepStmt = con.prepareStatement(selectStatement);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				RoleDetails rd = new RoleDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getDate(5), rs
								.getInt(6));
				totalRoles.add(rd);
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
		return totalRoles.size();

	}

	public Collection getRoles(int start, int pagesize) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String select = null;
		ArrayList<RoleDetails> totalRoles = new ArrayList<RoleDetails>();
		try {
			con = DBConnection.getConnection();

			select = "select * from " + DBConnection.RoleTablename
					+ " order by Role_ID asc";

			prepStmt = con.prepareStatement(select);

			rs = prepStmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			if (pagesize == 0)
				pagesize = numberOfColumns;
			int i = 0;
			rs.absolute(start);
			rs.previous();

			while (rs.next()) {
				RoleDetails rd = new RoleDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getDate(5), rs
								.getInt(6));
				totalRoles.add(rd);
				i++;
				if (i == pagesize)
					break;
			}

			return totalRoles;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	@SuppressWarnings("unchecked")
	public Collection getAllRoles() throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String select = null;
		ArrayList<RoleDetails> totalRoles = new ArrayList<RoleDetails>();
		
		try {
			con = DBConnection.getConnection();
			select = "select * from " + DBConnection.RoleTablename
					+ " order by Role_ID asc";
			prepStmt = con.prepareStatement(select);
			rs = prepStmt.executeQuery();

			while (rs.next()) {
				RoleDetails rd = new RoleDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getDate(5), rs
								.getInt(6));
				totalRoles.add(rd);
			}

			return totalRoles;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
	}

	public RoleDetails getRoleDetails(int Role_ID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			String selectString = "select * from " + DBConnection.RoleTablename
					+ " where Role_ID=? ";
			prepStmt = con.prepareStatement(selectString);
			prepStmt.setInt(1, Role_ID);
			rs = prepStmt.executeQuery();
			if (rs.next()) {
				RoleDetails rd = new RoleDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getDate(5), rs
								.getInt(6));
				return rd;
			} else {
				return null;
			}

		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public boolean addRole(String Role_Name, String Resume, int OperatorID)
			throws Exception {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String checkString = "select * from " + DBConnection.RoleTablename
					+ " where Role_Name='" + Role_Name + "'";
			// String checkString="select * from Role where
			// Role_Name='"+Role_Name+"'";
			// String insertString="INSERT INTO
			// Role(ROLE_ID,ROLE_NAME,RESUME,CREATETIME,UPDATETIME,OPERATORID)values(RoleID_SEQ.nextval,'"+Role_Name+"','"+Resume+"',sysdate,sysdate,"+OperatorID+")";

			String insertString = "insert into "
					+ DBConnection.RoleTablename
					+ "(Role_Name,Resume,CreateTime,UpdateTime,OperatorID)values('"
					+ Role_Name + "','" + Resume + "',curdate(),curdate(),"
					+ OperatorID + ")";
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
			DBConnection.closeConnection(con);
			st.close();
		}

	}

	public boolean updateRole(int Role_ID, String Role_Name, String Resume,
			int OperatorID) throws Exception {
		Connection con = null;

		ResultSet rs = null;
		Statement st = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String updateString = "update " + DBConnection.RoleTablename
					+ "  set Role_Name='" + Role_Name + "',Resume='" + Resume
					+ "',UpdateTime=curdate(),OperatorID=" + OperatorID
					+ " where Role_ID=" + Role_ID;
			st.executeUpdate(updateString);
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

	public boolean deleteRole(int Role_ID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);

			String deleteString = "delete from " + DBConnection.RoleTablename
					+ " where Role_ID=? ";
			prepStmt = con.prepareStatement(deleteString);
			prepStmt.setInt(1, Role_ID);
			prepStmt.executeUpdate();

			deleteString = "delete from " + DBConnection.Admin_RoleTablename
					+ "  where Role_ID=? ";
			prepStmt = con.prepareStatement(deleteString);
			prepStmt.setInt(1, Role_ID);
			prepStmt.executeUpdate();

			deleteString = "delete from " + DBConnection.User_RoleTablename
					+ "  where Role_ID=? ";
			prepStmt = con.prepareStatement(deleteString);
			prepStmt.setInt(1, Role_ID);
			prepStmt.executeUpdate();

			deleteString = "delete from " + DBConnection.Role_FunctionTablename
					+ "  where Role_ID=? ";
			prepStmt = con.prepareStatement(deleteString);
			prepStmt.setInt(1, Role_ID);
			prepStmt.executeUpdate();

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

	/*
	 * 
	 * 
	 * 角色功能记录处理模块
	 * 
	 */
	public int getNumberOfFunctionsOfRole(int Role_ID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		// ArrayList<Role_FunctionDetails> functionsOfrole=new
		// ArrayList<Role_FunctionDetails>();
		ArrayList<FunctionDetails> functionsOfRole = new ArrayList<FunctionDetails>();
		try {
			con = DBConnection.getConnection();
			// String selectStatement="select * from Role_Function where
			// RoleID=? order by Role_Function_ID asc";
			String selectStatement = "select * from "
					+ DBConnection.FunctionTablename
					+ " where Function_ID in(select Function_ID from "
					+ DBConnection.Role_FunctionTablename
					+ "   where Role_ID=?)order by Function_ID  asc";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, Role_ID);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				// Role_FunctionDetails rfd=new
				// Role_FunctionDetails(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getInt(6));
				// functionsOfrole.add(rfd);
				FunctionDetails fd = new FunctionDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				functionsOfRole.add(fd);

			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
		return functionsOfRole.size();

	}

	public Collection getFunctionsOfRole(int Role_ID, int start, int pagesize)
			throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		ArrayList<FunctionDetails> functionsOfRole = new ArrayList<FunctionDetails>();
		try {
			con = DBConnection.getConnection();
			// String selectStatement="select * from Role_Function where
			// RoleID=? order by Role_Function_ID asc";
			String selectStatement = "select * from "
					+ DBConnection.FunctionTablename
					+ " where Function_ID in(select Function_ID from "
					+ DBConnection.Role_FunctionTablename
					+ "  where Role_ID=?)order by Function_ID  asc";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, Role_ID);
			rs = prepStmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			if (pagesize == 0)
				pagesize = numberOfColumns;

			int i = 0;
			rs.absolute(start);
			rs.previous();
			while (rs.next()) {
				FunctionDetails fd = new FunctionDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				functionsOfRole.add(fd);
				i++;
				if (i == pagesize)
					break;
			}

			return functionsOfRole;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public Collection getFunctionsOfRole(int Role_ID) throws Exception {

		// return getFunctionsOfRole(Role_ID,0,0);
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		ArrayList<FunctionDetails> functionsOfRole = new ArrayList<FunctionDetails>();
		try {
			con = DBConnection.getConnection();
			// String selectStatement="select * from Role_Function where
			// RoleID=? order by Role_Function_ID asc";
			String selectStatement = "select * from "
					+ DBConnection.FunctionTablename
					+ " where Function_ID in(select Function_ID from "
					+ DBConnection.Role_FunctionTablename
					+ "  where Role_ID=?)order by Function_ID  asc";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, Role_ID);
			rs = prepStmt.executeQuery();

			while (rs.next()) {
				FunctionDetails fd = new FunctionDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				functionsOfRole.add(fd);

			}

			return functionsOfRole;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}
	}

	public Collection getFunctionOwnerRoles(int Function_ID, int start,
			int pagesize) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<RoleDetails> roles = new ArrayList<RoleDetails>();

		try {
			String selectstatement = null;
			con = DBConnection.getConnection();
			selectstatement = "select * from " + DBConnection.RoleTablename
					+ "   where Role_ID in (select Role_ID from "
					+ DBConnection.Role_FunctionTablename
					+ "  where Function_ID =?)";
			prepStmt = con.prepareStatement(selectstatement);
			prepStmt.setInt(1, Function_ID);
			rs = prepStmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			if (pagesize == 0)
				pagesize = numberOfColumns;
			int i = 0;
			rs.absolute(start);
			rs.previous();
			while (rs.next()) {
				RoleDetails rd = new RoleDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getDate(5), rs
								.getInt(6));
				roles.add(rd);
				i++;
				if (i == pagesize)
					break;

			}

		}

		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}
		return roles;
	}

	public Collection getFunctionOwnerRoles(int Function_ID) throws Exception {
		return getFunctionOwnerRoles(Function_ID, 0, 0);

	}

	public boolean addFunctionOfRole(int Role_ID, int Function_ID,
			int OperatorID) throws Exception {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			String insertString = "insert into "
					+ DBConnection.Role_FunctionTablename
					+ "(Role_ID,Function_ID,CreateTime,UpdateTime,OperatorID)  values("
					+ Role_ID + "," + Function_ID + ",curdate(),curdate(),"
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

	public boolean deleteFunctionOfRole(int Role_ID, int Function_ID)
			throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String deleteString = "delete from "
					+ DBConnection.Role_FunctionTablename
					+ "  where Role_ID=? and Function_ID=?";
			prepStmt = con.prepareStatement(deleteString);
			prepStmt.setInt(1, Role_ID);
			prepStmt.setInt(2, Function_ID);
			prepStmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (Exception ex) {
			con.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public boolean deleteAllFunctionOfRole(int Role_ID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String deleteString = "delete from "
					+ DBConnection.Role_FunctionTablename + "  where Role_ID=?";
			prepStmt = con.prepareStatement(deleteString);
			prepStmt.setInt(1, Role_ID);

			prepStmt.executeUpdate();
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

	/*
	 * 功能信息处理模块
	 * 
	 */

	public int getNumberOfTotalFunctions() throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<FunctionDetails> totalrecords = new ArrayList<FunctionDetails>();
		try {
			con = DBConnection.getConnection();
			String selectStatement = "select * from "
					+ DBConnection.FunctionTablename
					+ " order by Functions_ID asc";
			prepStmt = con.prepareStatement(selectStatement);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				FunctionDetails fd = new FunctionDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				totalrecords.add(fd);
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
		return totalrecords.size();

	}

	public Collection getFunctions(int start, int pagesize) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String select = null;
		ArrayList<FunctionDetails> totalFunctions = new ArrayList<FunctionDetails>();
		try {
			con = DBConnection.getConnection();

			select = "select * from " + DBConnection.FunctionTablename
					+ "  order by Function_ID asc";

			prepStmt = con.prepareStatement(select);

			rs = prepStmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			if (pagesize == 0)
				pagesize = numberOfColumns;

			int i = 0;
			rs.absolute(start);
			rs.previous();

			while (rs.next()) {
				FunctionDetails fd = new FunctionDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				totalFunctions.add(fd);
				i++;
				if (i == pagesize)
					break;
			}

			return totalFunctions;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public Collection getAllFunctions() throws Exception {
		// return getFunctions(0,0);
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String select = null;
		ArrayList<FunctionDetails> totalFunctions = new ArrayList<FunctionDetails>();
		try {
			con = DBConnection.getConnection();

			select = "select * from " + DBConnection.FunctionTablename
					+ "  order by Function_ID asc";

			prepStmt = con.prepareStatement(select);

			rs = prepStmt.executeQuery();

			while (rs.next()) {
				FunctionDetails fd = new FunctionDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				totalFunctions.add(fd);

			}

			return totalFunctions;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}
	}

	public FunctionDetails getFunctionDetails(int Function_ID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			String selectString = "select * from "
					+ DBConnection.FunctionTablename + " where Function_ID=? ";
			prepStmt = con.prepareStatement(selectString);
			prepStmt.setInt(1, Function_ID);
			rs = prepStmt.executeQuery();
			if (rs.next()) {
				FunctionDetails fd = new FunctionDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				return fd;
			} else {
				return null;
			}

		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public boolean addFunction(String Function_Name, String Resume,
			int OperatorID) throws Exception {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String checkString = "select * from "
					+ DBConnection.FunctionTablename + " where Function_Name='"
					+ Function_Name + "'";
			String insertString = "insert into "
					+ DBConnection.FunctionTablename
					+ " (Function_Name,Resume,CreateTime,UpdateTime,OperatorID)values('"
					+ Function_Name + "','" + Resume + "',curdate(),curdate(),"
					+ OperatorID + ")";
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
			DBConnection.closeConnection(con);
			st.close();
		}

	}

	public boolean updateFunction(int Function_ID, String Function_Name,
			String Resume, int OperatorID) throws Exception {
		Connection con = null;

		ResultSet rs = null;
		Statement st = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String updateString = "update " + DBConnection.FunctionTablename
					+ " set Function_Name='" + Function_Name + "',Resume='"
					+ Resume + "',UpdateTime= curdate(),OperatorID="
					+ OperatorID + " where Function_ID=" + Function_ID;
			st.executeUpdate(updateString);
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

	public boolean deleteFunction(int Function_ID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String deleteString = "delete from "
					+ DBConnection.FunctionTablename + " where Function_ID=? ";
			prepStmt = con.prepareStatement(deleteString);
			prepStmt.setInt(1, Function_ID);
			prepStmt.executeUpdate();

			deleteString = "delete from "
					+ DBConnection.Function_OperationTablename
					+ " where Function_ID=? ";
			prepStmt.setInt(1, Function_ID);
			prepStmt.executeUpdate();

			deleteString = "delete from " + DBConnection.Role_FunctionTablename
					+ " where Function_ID=? ";
			prepStmt.setInt(1, Function_ID);
			prepStmt.executeUpdate();

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

	/*
	 * 功能操作记录处理模块
	 * 
	 * 
	 */
	public int getNumberOfOperationsOfFunction(int Function_ID)
			throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		// ArrayList<Function_OperationsDetails> operationsOfFunction=new
		// ArrayList<Function_OperationsDetails>();
		ArrayList<OperationDetails> operationsOfFunction = new ArrayList<OperationDetails>();
		try {
			con = DBConnection.getConnection();
			// String selectStatement="select * from Function_Operation where
			// FunctionID=? order by Function_Operation asc";
			String selectStatement = "select * from "
					+ DBConnection.OperationTablename
					+ " where Operation_ID in(select Operation_ID from "
					+ DBConnection.Function_OperationTablename
					+ "  where Function_ID=?)order by Operation_ID  asc";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, Function_ID);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				// Function_OperationDetails fod=new
				// Function_Operation(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getInt(6));
				// operationsOfFunction.add(fod);
				OperationDetails od = new OperationDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				operationsOfFunction.add(od);

			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
		return operationsOfFunction.size();

	}

	public Collection getOperationsOfFunction(int Function_ID, int start,
			int pagesize) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		ArrayList<OperationDetails> operationsOfRole = new ArrayList<OperationDetails>();
		try {
			con = DBConnection.getConnection();
			// String selectStatement="select * from Role_Function where
			// RoleID=? order by Role_Function_ID asc";
			String selectStatement = "select * from "
					+ DBConnection.OperationTablename
					+ " where Operation_ID in(select Operation_ID from "
					+ DBConnection.Function_OperationTablename
					+ "  where Function_ID=?)order by Operation_ID  asc";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, Function_ID);
			rs = prepStmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			if (pagesize == 0)
				pagesize = numberOfColumns;

			int i = 0;
			rs.absolute(start);
			rs.previous();
			while (rs.next()) {
				OperationDetails od = new OperationDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				operationsOfRole.add(od);
				i++;
				if (i == pagesize)
					break;
			}

			return operationsOfRole;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public Collection getOperationsOfFunction(int Function_ID) throws Exception {
		// return getOperationsOfFunction(Function_ID,0,0);
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		ArrayList<OperationDetails> operationsOfRole = new ArrayList<OperationDetails>();
		try {
			con = DBConnection.getConnection();
			// String selectStatement="select * from Role_Function where
			// RoleID=? order by Role_Function_ID asc";
			String selectStatement = "select * from "
					+ DBConnection.OperationTablename
					+ " where Operation_ID in(select Operation_ID from "
					+ DBConnection.Function_OperationTablename
					+ "  where Function_ID=?)order by Operation_ID  asc";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, Function_ID);
			rs = prepStmt.executeQuery();

			while (rs.next()) {
				OperationDetails od = new OperationDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				operationsOfRole.add(od);

			}

			return operationsOfRole;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}
	}

	public Collection getOperationOwnerFunctions(int Operation_ID, int start,
			int pagesize) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<FunctionDetails> functions = new ArrayList<FunctionDetails>();

		try {
			String selectstatement = null;
			con = DBConnection.getConnection();
			selectstatement = "select * from " + DBConnection.FunctionTablename
					+ "  where Function_ID in (select Function_ID from "
					+ DBConnection.Function_OperationTablename
					+ " where Operation_ID =?)";
			prepStmt = con.prepareStatement(selectstatement);
			prepStmt.setInt(1, Operation_ID);
			rs = prepStmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			if (pagesize == 0)
				pagesize = numberOfColumns;
			int i = 0;
			rs.absolute(start);
			rs.previous();
			while (rs.next()) {
				FunctionDetails rd = new FunctionDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				functions.add(rd);
				i++;
				if (i == pagesize)
					break;

			}

		}

		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}
		return functions;
	}

	public Collection getOperationOwnerFunctions(int Operation_ID)
			throws Exception {

		return getOperationOwnerFunctions(Operation_ID, 0, 0);
	}

	public boolean addOperationOfFunction(int Function_ID, int Operation_ID,
			int OperatorID) throws Exception {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			String insertString = "insert into "
					+ DBConnection.Function_OperationTablename
					+ "(Function_ID,Operation_ID,CreateTime,UpdateTime,OperatorID) values("
					+ Function_ID + "," + Operation_ID
					+ ",curdate(),curdate()," + OperatorID + ")";

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

	public boolean deleteOperationOfFunction(int Function_ID, int Operation_ID)
			throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String checkString = "select * from "
					+ DBConnection.Function_OperationTablename
					+ " where Function_ID=" + Function_ID
					+ " and Operation_ID=" + Operation_ID;

			String deleteString = "delete from "
					+ DBConnection.Function_OperationTablename
					+ " where Function_ID=? and Operation_ID=?";

			Statement st = con.createStatement();
			rs = st.executeQuery(checkString);

			if (rs.next()) {

				prepStmt = con.prepareStatement(deleteString);
				prepStmt.setInt(1, Function_ID);
				prepStmt.setInt(2, Operation_ID);
				prepStmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
				return true;
			} else {
				return false;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			con.rollback();
			throw ex;

		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public boolean deleteAllOperationOfFunction(int Function_ID)
			throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);

			String deleteString = "delete from "
					+ DBConnection.Function_OperationTablename
					+ " where Function_ID=? ";
			prepStmt = con.prepareStatement(deleteString);
			prepStmt.setInt(1, Function_ID);

			prepStmt.executeUpdate();
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

	/*
	 * 
	 * 操作信息处理模块
	 */
	public int getNumberOfTotalOperations() throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<OperationDetails> totalOperations = new ArrayList<OperationDetails>();
		try {
			con = DBConnection.getConnection();
			String selectStatement = "select * from "
					+ DBConnection.OperationTablename
					+ " order by Operations_ID asc";
			prepStmt = con.prepareStatement(selectStatement);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				OperationDetails od = new OperationDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				totalOperations.add(od);
			}
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
		return totalOperations.size();

	}

	public Collection getOperations(int start, int pagesize) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String select = null;
		ArrayList<OperationDetails> totalOperations = new ArrayList<OperationDetails>();
		try {
			con = DBConnection.getConnection();

			select = "select * from " + DBConnection.OperationTablename
					+ "  order by Operation_ID asc";

			prepStmt = con.prepareStatement(select);

			rs = prepStmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			if (pagesize == 0)
				pagesize = numberOfColumns;

			int i = 0;
			rs.absolute(start);
			rs.previous();

			while (rs.next()) {
				OperationDetails od = new OperationDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				totalOperations.add(od);
				i++;
				if (i == pagesize)
					break;
			}

			return totalOperations;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public Collection getAllOperations() throws Exception {
		// return getOperations(0,0);
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String select = null;
		ArrayList<OperationDetails> totalOperations = new ArrayList<OperationDetails>();
		try {
			con = DBConnection.getConnection();

			select = "select * from " + DBConnection.OperationTablename
					+ "  order by Operation_ID asc";

			prepStmt = con.prepareStatement(select);

			rs = prepStmt.executeQuery();

			while (rs.next()) {
				OperationDetails od = new OperationDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				totalOperations.add(od);

			}

			return totalOperations;
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public OperationDetails getOperationDetails(int Operation_ID)
			throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			String selectString = "select * from "
					+ DBConnection.OperationTablename
					+ " where Operation_ID=? ";
			prepStmt = con.prepareStatement(selectString);
			prepStmt.setInt(1, Operation_ID);
			rs = prepStmt.executeQuery();
			if (rs.next()) {
				OperationDetails od = new OperationDetails(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDate(5), rs.getInt(6));
				return od;
			} else {
				return null;
			}

		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);

		}

	}

	public boolean addOperation(String Operation_Name, String Resume,
			int OperatorID) throws Exception {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String checkString = "select * from  "
					+ DBConnection.OperationTablename
					+ " where Operation_Name='" + Operation_Name + "'";
			// String insertString="INSERT INTO
			// Operation(Operation_ID,Operation_Name,Resume,CreateTime,UpdateTime,OperatorID)values(OperationID_SEQ.nextval,'"+Operation_Name+"','"+Resume+"',sysdate,sysdate,"+OperatorID+")";
			String insertString = "INSERT INTO "
					+ DBConnection.OperationTablename
					+ "(Operation_Name,Resume,CreateTime,UpdateTime,OperatorID)values('"
					+ Operation_Name + "','" + Resume
					+ "',curdate(),curdate()," + OperatorID + ")";
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
			DBConnection.closeConnection(con);
			st.close();
		}

	}

	public boolean updateOperation(int Operation_ID, String Operation_Name,
			String Resume, int OperatorID) throws Exception {
		Connection con = null;

		ResultSet rs = null;
		Statement st = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String updateString = "update " + DBConnection.OperationTablename
					+ " set Operation_Name='" + Operation_Name + "',Resume='"
					+ Resume + "',UpdateTime=curdate(),OperatorID="
					+ OperatorID + "  where Operation_ID=" + Operation_ID;
			st.executeUpdate(updateString);
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

	public boolean deleteOperation(int Operation_ID) throws Exception {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String deleteString = "delete from "
					+ DBConnection.OperationTablename
					+ " where Operation_ID=? ";
			prepStmt = con.prepareStatement(deleteString);
			prepStmt.setInt(1, Operation_ID);
			prepStmt.executeUpdate();
			// con.commit();
			deleteString = "delete from "
					+ DBConnection.Function_OperationTablename
					+ " where Operation_ID=? ";
			prepStmt.setInt(1, Operation_ID);
			prepStmt.executeUpdate();
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

}
