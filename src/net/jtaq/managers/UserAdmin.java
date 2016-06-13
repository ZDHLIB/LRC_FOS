package net.jtaq.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import net.jtaq.db.DBConnection;
import net.jtaq.utils.RoleDetails;
import net.jtaq.utils.UserDetails;

public class UserAdmin {

	
	 private ArrayList<UserDetails> users;
	  
		
		//用户信息处理模块
		public int getNumberOfUsers() throws Exception{
			Connection con=null;
			PreparedStatement prepStmt=null;
			ResultSet rs=null;
			users=new ArrayList<UserDetails>();
			try{
				con=DBConnection.getConnection();
				String selectStatement="select * from "+DBConnection.UserTablename+"   order by UserID asc";
				prepStmt=con.prepareStatement(selectStatement);
				rs=prepStmt.executeQuery();
				while(rs.next()){
					UserDetails ud=new UserDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),
							rs.getDate(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15),rs.getInt(16),rs.getString(17),rs.getString(18),
							rs.getInt(19),rs.getDate(20),rs.getString(21),rs.getDate(22),rs.getString(23),rs.getInt(24), rs.getInt(25), rs.getInt(26));
				    users.add(ud);
				}
			}finally{
				DBConnection.closeResultSet(rs);
				DBConnection.closePrepStmt(prepStmt);
				DBConnection.closeConnection(con);
			}
			return users.size();
			
		}
		public Collection getUsers(int start,int pagesize)throws Exception{
			Connection con=null;
			PreparedStatement prepStmt=null;
			ResultSet rs=null;
			users=new ArrayList<UserDetails>();
			 	
			try{
				con=DBConnection.getConnection();
				String selectStatement="select * from "+DBConnection.UserTablename+"   order by UserID asc";
				prepStmt=con.prepareStatement(selectStatement);
				rs=prepStmt.executeQuery();
				
				ResultSetMetaData   rsmd   =   rs.getMetaData();   
		        int   numberOfColumns   =   rsmd.getColumnCount();
		        if(pagesize==0) pagesize=numberOfColumns;
		        
				 int i=0;
				 rs.absolute(start);
				 rs.previous();
				while(rs.next()){
					UserDetails ud=new UserDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),
							rs.getDate(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15),rs.getInt(16),rs.getString(17),rs.getString(18),
							rs.getInt(19),rs.getDate(20),rs.getString(21),rs.getDate(22),rs.getString(23),rs.getInt(24), rs.getInt(25), rs.getInt(26));
				    users.add(ud);
				    i++;
				    if(i==pagesize)break;
				}
			}finally{
				DBConnection.closeResultSet(rs);
				DBConnection.closePrepStmt(prepStmt);
				DBConnection.closeConnection(con);
				 
			}  
			 
			 
			return users;
			  
			 
		}
		public Collection getUsers()throws Exception{
			
			return getUsers(0,0);
		}
		public Collection getUsers(String searchIndex,String value,int start,int pagesize)throws Exception{
			Connection con=null;
			PreparedStatement prepStmt=null;
			ResultSet rs=null;
			 users=new ArrayList<UserDetails>();
			try{
				String selectstatement=null;
				con=DBConnection.getConnection();
				
				if(searchIndex != null && !searchIndex.equals("")){
					if(!searchIndex.equalsIgnoreCase("all"))	{
					 if(searchIndex.equalsIgnoreCase("UserName"))
						 selectstatement="select * from "+DBConnection.UserTablename+"   where UsrName=? order by UserID asc";
						else if(searchIndex.equalsIgnoreCase("level"))
							selectstatement="select * from "+DBConnection.UserTablename+"   where level=? order by UserID asc";
						else if(searchIndex.equalsIgnoreCase("state"))
							selectstatement="select * from "+DBConnection.UserTablename+"   where state=? order by UserID asc";
						else if (searchIndex.equalsIgnoreCase("RealName"))
							selectstatement="select * from "+DBConnection.UserTablename+"   where RealName=? order by UserID asc";
						else if(searchIndex.equalsIgnoreCase("education"))
							selectstatement="select * from "+DBConnection.UserTablename+"   where Eduction=? order by UserID asc";
						else if (searchIndex.equalsIgnoreCase("age"))
							selectstatement="select * from "+DBConnection.UserTablename+"   where Age=? order by UserID asc";
					   prepStmt=con.prepareStatement(selectstatement);
					      prepStmt.setString(1, value);
							rs=prepStmt.executeQuery();
							
					}
					else if(searchIndex.equalsIgnoreCase("all")){
						Statement stmt= con.createStatement();
						rs=stmt.executeQuery("select * from "+DBConnection.UserTablename+"   order by UserID asc");
						
			       }
			        		 	 
				  } 
				
				ResultSetMetaData   rsmd   =   rs.getMetaData();   
		        int   numberOfColumns   =   rsmd.getColumnCount();
		        if(pagesize==0) pagesize=numberOfColumns;
		        
				  int i=0;
				 rs.absolute(start);
				  rs.previous();
				 while(rs.next()){
					 UserDetails ud=new UserDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),
								rs.getDate(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15),rs.getInt(16),rs.getString(17),rs.getString(18),
								rs.getInt(19),rs.getDate(20),rs.getString(21),rs.getDate(22),rs.getString(23),rs.getInt(24),rs.getInt(25), rs.getInt(26));
					    users.add(ud);
				    i++;
				    if(i==pagesize)break; 
				}
							 
				return users;
			}finally{
				DBConnection.closeResultSet(rs);
				DBConnection.closePrepStmt(prepStmt);
				DBConnection.closeConnection(con);
				 
			}  
		}
		 
		public Collection getUsers(String name,String value)throws Exception{
			return  getUsers(name,value,0,0);
			
		}
		public UserDetails getUserDetails(int UserID)throws Exception{
			Connection con=null;
			PreparedStatement prepStmt=null;
			ResultSet rs=null;
			try{
				con=DBConnection.getConnection();
				String selectstatement="select * from "+DBConnection.UserTablename+"   where UserID=? ";
				prepStmt=con.prepareStatement(selectstatement);
				prepStmt.setInt(1, UserID);
				rs=prepStmt.executeQuery();
				if(rs.next()){
					UserDetails ud=new UserDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),
							rs.getDate(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15),rs.getInt(16),rs.getString(17),rs.getString(18),
							rs.getInt(19),rs.getDate(20),rs.getString(21),rs.getDate(22),rs.getString(23),rs.getInt(24), rs.getInt(25), rs.getInt(26));
					prepStmt.close();
					return ud;
				}else{return null;}
			}
		finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
			 
		}
			
		}
		public boolean deleteUser(int UserID)throws Exception
		{
			Connection con=null;
			PreparedStatement prepStmt=null;
			ResultSet rs=null;
			try{
				con=DBConnection.getConnection();
				
				con.setAutoCommit(false);
				String  checkString="select * from "+DBConnection.UserTablename+"   where UserID=?";
				 String deleteString="delete from "+DBConnection.UserTablename+"   where UserID=?";
				 prepStmt=con.prepareStatement(checkString);
				 prepStmt.setInt(1, UserID);
				 rs=prepStmt.executeQuery();
				  if(!rs.next()) {return false;}
				  else{ 
					  prepStmt=con.prepareStatement(deleteString);
					    prepStmt.setInt(1, UserID);
						prepStmt.executeUpdate();
					    }
				 
				    con.commit();
					con.setAutoCommit(true); 
					return true;
			  }catch(Exception ex){
				con.rollback();
				throw ex;
				 }finally{
					DBConnection.closeResultSet(rs);
					DBConnection.closePrepStmt(prepStmt);
					DBConnection.closeConnection(con);
				 
			}
			 
		}
		 
		public int getNumberofRolesOfUser(String UserID) throws Exception{
			Connection con=null;
			PreparedStatement prepStmt=null;
			ResultSet rs=null;
			ArrayList<RoleDetails> userRoles=new ArrayList<RoleDetails>();
			try{
				con=DBConnection.getConnection();
				String selectStatement="select * from "+DBConnection.RoleTablename+"  where Role_ID in (select Role_ID from "+DBConnection.User_RoleTablename +" where UserID=? )order by Role_ID asc";
				prepStmt=con.prepareStatement(selectStatement);
				prepStmt.setString(1, UserID);
				rs=prepStmt.executeQuery();
				while(rs.next()){
					RoleDetails  rd=new RoleDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getInt(6));
					userRoles.add(rd);
				}
			}finally{
				DBConnection.closeResultSet(rs);
				DBConnection.closePrepStmt(prepStmt);
				DBConnection.closeConnection(con);
			}
			return userRoles.size();
			
		}
		public Collection getRolesOfUser(int UserID) throws Exception{
			Connection con=null;
			PreparedStatement prepStmt=null;
			ResultSet rs=null;
			ArrayList<RoleDetails> userRoles=new ArrayList<RoleDetails>();
			try{
				con=DBConnection.getConnection();
				String selectStatement="select * from "+DBConnection.RoleTablename+"  where Role_ID in (select Role_ID from "+DBConnection.User_RoleTablename +" where UserID=? )order by Role_ID asc";
				prepStmt=con.prepareStatement(selectStatement);
				prepStmt.setInt(1, UserID);
				rs=prepStmt.executeQuery();
				while(rs.next()){
					RoleDetails  rd=new RoleDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getInt(6));
					userRoles.add(rd);
				}
			}finally{
				DBConnection.closeResultSet(rs);
				DBConnection.closePrepStmt(prepStmt);
				DBConnection.closeConnection(con);
			}
			return userRoles;
			
		}
		 
		public boolean addRoleOfUser(int  UserID,int Role_ID,int OperatorID)throws Exception{
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			try{
				con=DBConnection.getConnection();
				con.setAutoCommit(false);
				st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				String insertString="insert into "+DBConnection.User_RoleTablename +"(UserID,Role_ID,CreateTime,UpdateTime ,OperatorID ) values("+UserID+","+Role_ID+",curdate(),curdate(),"+OperatorID+")";
				st.executeUpdate(insertString);
				con.commit();
				con.setAutoCommit(true); 
					return true;
			}catch(Exception ex){
				con.rollback();
				throw ex;
				}finally{
			    DBConnection.closeResultSet(rs);
				DBConnection.closeConnection(con);
				st.close();
			}
			 
		}
		public boolean updateRoleOfUser(int  UserID,int Role_ID,int OperatorID)throws Exception{
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			try{
				con=DBConnection.getConnection();
				con.setAutoCommit(false);
				st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				String insertString="update  "+DBConnection.User_RoleTablename +" set Role_ID="+Role_ID+",updateTime=curdate(),OperatorID="+OperatorID+"where UserID="+UserID;
				st.executeUpdate(insertString);
				con.commit();
				con.setAutoCommit(true); 
					return true;
			}catch(Exception ex){
				con.rollback();
				throw ex;
				}finally{
			    DBConnection.closeResultSet(rs);
				DBConnection.closeConnection(con);
				st.close();
			}
			 
		}
		
		public boolean deleteUserRole(int  UserID,int Role_ID)throws Exception{
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			PreparedStatement prepStmt=null;
			try{
				con=DBConnection.getConnection();
				con.setAutoCommit(false);
				String insertString="delete from "+DBConnection.User_RoleTablename +" where UserID=? and Role_ID=?";
				prepStmt=con.prepareStatement(insertString);
				prepStmt.setInt(1, UserID);
				prepStmt.setInt(2, Role_ID);
				prepStmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true); 
					return true;
			}catch(Exception ex){
				con.rollback();
				throw ex;
				}finally{
			    DBConnection.closeResultSet(rs);
				DBConnection.closeConnection(con);
				 
			}
			 
		}
		public boolean updatenum(int userID, int view, int down) throws Exception {
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			try{
				con = DBConnection.getConnection();
				con.setAutoCommit(false);
				st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				String insertString = "update" + DBConnection.User_RoleTablename + "set view="+view+",down="+ down +"where UserID="+userID;
				st.executeUpdate(insertString);
				con.commit();
				con.setAutoCommit(true); 
					return true;
			}catch(Exception ex){
				con.rollback();
				throw ex;
				}finally{
			    DBConnection.closeResultSet(rs);
				DBConnection.closeConnection(con);
				st.close();
			}
		}	
}
