<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*"   %>
<%! 
public String convert(String qs){ 
try{ 
if (qs == null) return "NULL"; 
else return new String(qs.getBytes("utf-8"),"utf-8"); 
} 
catch(Exception e){ 
log("gb2iso error："+e.getMessage()); 
} 
return "NULL"; 
} 
%>
<%@ include file="conn.jsp"%>
<%
		//request.setCharacterEncoding("gb2312");
		String rand = (String)session.getAttribute("rand");
	    String input = request.getParameter("rand");
		if (rand.equals(input)) 
		{
		String title=request.getParameter("title");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String web=request.getParameter("web");
		String QQ=request.getParameter("QQ");
		String text=request.getParameter("text");
		String showw=request.getParameter("showw");
		String p1=request.getParameter("p1");
		String p2=request.getParameter("p2");
		String pic=null;
		String ip=request.getRemoteAddr();
		if("http://".equals(web)){web="";}
		if("m".equals(p1)){pic="m"+p2+".gif";}
		if("w".equals(p1)){pic="w"+p2+".gif";}
		if(name==null||QQ==null||text==null||p1==null||p2==null)
		{
			out.println("<script language=javascript>alert('不允许直接访问');");
			out.println("location.href('index.jsp');");
			out.println("</script>");
		}
		else
		{
		String sql="insert into "+tab+" values(0,'"+convert(title)+"','"+convert(name)+"','"+convert(QQ)+"','"+convert(email)+"','"+convert(web)+"','y','"+convert(pic)+"','"+convert(text)+"','"+ip+"',now())";
		st.executeUpdate(sql);
		st.close();
		con.close();
		out.println("<script language=javascript>alert('留言成功!');");
		out.println("location.href('index.jsp');");
		out.println("</script>");
		}
		}
		else
		{
			out.println("<script language=javascript>alert('验证码好像不对噢?!');");
			out.println("window.history.go(-1);");
			out.println("</script>");
		}
%>
<%
st.close();
con.close();
%>