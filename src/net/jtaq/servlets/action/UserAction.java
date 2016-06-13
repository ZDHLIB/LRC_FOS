package net.jtaq.servlets.action;
import net.jtaq.users.UserInfoBean;
import net.lrc.db.Mysql;

import javax.servlet.http.*;

import  org.apache.struts.action.*;
public class UserAction extends Action{
	
	
	public  ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		
		//UserForm uf=(UserForm)form;
		DynaActionForm uf=(DynaActionForm)form;
		try{
			//javax.sql.DataSource dataSource=getDataSource(request);
			//ServletContext context = servlet.getServletContext();
			// DataSource dataSource = (DataSource)context.getAttribute("jtaq");
			//DBConnection dbcon = new DBConnection(dataSource);
			      UserInfoBean uib=new UserInfoBean();
			      String logname=(String)uf.get("logname");
			      String password=(String)uf.get("password");
			//if( uib.checkUserInfo(uf.getLogname(),uf.getPassword()))
			      if( uib.checkUserInfo(logname,password))
			      {
				//request.setAttribute("user",uf.getLogname());
				return (mapping.findForward("admin_login_success"));
				
				};
					
		}catch(Exception e)
		 {
			e.printStackTrace();
			
		 }
		 
		return (mapping.findForward("admin_login_error"));
		 
		
	}
	
	public 	void  updatenum(HttpServletRequest request,HttpServletResponse response)throws Exception
	
	{
		String tempview = request.getParameter("view1");
		String tempdown = request.getParameter("down1");
		String tempuserID = request.getParameter("userID");
		int viewcount = 0;
		int downcount = 0;
		int id = 0;
		viewcount = Integer.parseInt(tempview);
		downcount = Integer.parseInt(tempdown);
		id = Integer.parseInt(tempuserID);
		response.getWriter().write("<script>alert('³É¹¦');</script>");
		try {
			Mysql mysql = new Mysql();

			String sql = "select view, down from userinfo where id='"
				+ id + "'";
			
			sql = "update userinfo set view='" + viewcount
					+ "',down='" + downcount
					+ "' where id='"+ id +"'";
			mysql.executeUpdate(sql);
			mysql.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}
