package net.jtaq.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import net.jtaq.db.DBConnection;
import net.jtaq.utils.UserDetails;

public class UserInfoBean {
      
	
	
	public UserDetails getUserDetails(int UserID)throws Exception{
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		
		try{
			con=DBConnection.getConnection();
			String selectstatement="select * from "+DBConnection.UserTablename+"  where UserID=? ";
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
	
	public boolean addUserInfo(String UserName,String Password,String RealName,int Sex,int Age,String Nation,String HomePlace,String Birthday,String Education,String Company ,String Address,String ZipCode,String Email,int Telephone,int Mobilephone,String Resume,String Level,int IsPublic,String CreateIP,String LastlyLoginIP,int state)throws Exception
	{
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		Statement st=null;
		 try{
			con=DBConnection.getConnection();
			
			con.setAutoCommit(false);
			java.util.Date   now   =   new   java.util.Date();   
			  java.sql.Timestamp     tt   =   new   java.sql.Timestamp(now.getTime())   ;   
			  tt.setNanos(0);   

			st=con.createStatement(/*ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.TYPE_FORWARD_ONLY*/);
			String  checkString="select * from "+DBConnection.UserTablename+" where UserName='"+UserName+"'";
			String insertString="INSERT INTO "+DBConnection.UserTablename+"(UserID,UserName,Password,RealName,Sex,Age,Nation,HomePlace,Birthday,Education,Company,Address,ZipCode,Email,Telephone,Mobilephone,Resume,Levels,IsPublic,CreateTime,CreateIP,LastlyLoginTime,LastlyLoginIP,state)values(ID_SEQ.nextval,'"+UserName+"','"+Password+"','"+RealName+"',"+Sex+","+Age+",'"+Nation+"','"+HomePlace+"','"+Birthday+"','"+Education+"','"+Company+"','"+Address+"','"+ZipCode+"','"+Email+"',"+Telephone+","+Mobilephone+",'"+Resume+"','"+Level+"',"+IsPublic+",sysdate,'"+CreateIP+"',sysdate,'"+LastlyLoginIP+"',"+state+")";
			 rs=st.executeQuery(checkString);
			// String insertString="INSERT INTO "+DBConnection.UserTablename+"(UserID,UserName,Password,RealName,Sex,Age,Nation,HomePlace,Birthday,Education,Company,Address,ZipCode,Email,Telephone,Mobilephone,Resume,Levels,IsPublic,CreateTime,CreateIP,LastlyLoginTime,LastlyLoginIP,state)values(ID_SEQ.nextval"+","+"'"+UserName+"'"+","+"'"+Password+"'"+","+"'"+RealName+"'"+","+Sex+","+Age+","+"'"+Nation+"'"+","+"'"+HomePlace+"'"+","+"'"+Birthday+"'"+","+"'"+Education+"'"+","+"'"+Company+"'"+","+"'"+Address+"'"+","+"'"+ZipCode+"'"+","+"'"+Email+"'"+","+Telephone+","+Mobilephone+","+"'"+Resume+"'"+","+"'"+Level+"'"+","+IsPublic+","+"'"+date+"'"+","+"'"+CreateIP+"'"+","+"'"+date+"'"+","+"'"+LastlyLoginIP+"'"+","+state+")";
			  
			  if(rs.next()) {return false;}
			  else{ 
				   st.executeUpdate(insertString);
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
	public boolean updateUserInfo(int UserID,String Password,String RealName,int Sex,int Age,String Nation,String HomePlace,
			Date Birthday,String Education,String Company ,
			String Address,String ZipCode,String Email,int Telephone,int Mobilephone,String Resume,String Level,int IsPublic,
			int state)throws Exception
	{
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		Statement st=null;
		try{
			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String updateString="update "+DBConnection.UserTablename+"  set  Password='"+Password+"',RealName='"+RealName+"',Age="+Age+",Sex="+Sex+"," +
					"Nation='"+Nation+"',HomePlace="+HomePlace+", " +"Birthday="+Birthday+" ,Education='"+Education+"' ,Company='"+Company+"',Address= " +
					Address+",ZipCode='"+ZipCode+"',Email='"+Email+"',Telephone="+Telephone+",Mobilephone="+Mobilephone+",Resume='"+Resume+"',Level='"+Level+"'" +
							",IsPublic="+IsPublic+",state="+state+"where UserID="+UserID+"";
			 st.executeUpdate(updateString); 
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
			st.close();
		}
		 
	}
	
//	用户验证模块
	public  boolean checkUserInfo(String UserName,String Password)throws Exception{
		
		Connection con=null;  
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		try{
			con=DBConnection.getConnection();
		String checkstatement="select * from "+DBConnection.UserTablename+"  where UserName=? and Password=? ";
	    prepStmt=con.prepareStatement(checkstatement);
	    prepStmt.setString(1, UserName);
		prepStmt.setString(2,Password);
	    rs=prepStmt.executeQuery();
			if(rs.next())
			{  return true;
			}
		 
		}catch (Exception e){}
		 
	 
		finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closePrepStmt(prepStmt);
			DBConnection.closeConnection(con);
		}
		 
		return false;
	}
 

	public  static  long  getMillis(java.util.Date  date)  {  
	    java.util.Calendar  c  =  java.util.Calendar.getInstance();  
	    c.setTime(date);  
	    return  c.getTimeInMillis();  
	  }  

	public static Date getDate(Date date,int days){

		java.util.Calendar c=java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date)+((long)days)*24*3600*1000);
		return c.getTime();


		}
}
