<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*"   %>
<%@ include file="conn.jsp"%>
<%
		String pass=null;pass=(String)session.getAttribute("pass");
		if("y".equals(pass))
			{
				String id=request.getParameter("id");
				
				if(id==null)
					{
					out.print("<br><br><br><center><h3><font color=#FF0000>请不要直接访问页面!</font></h3></center>");
					}
				else
					{
			
					String sql="delete from "+tab+" where id="+id;
					st.executeUpdate(sql);
					st.close();
					con.close();
                                        out.println("删除成功!!!返回主页");
					response.sendRedirect("index.jsp");
					}
			}
				else
				response.sendRedirect("login.jsp");
%>