<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%

  net.jtaq.utils.AdminDetails admin=(net.jtaq.utils.AdminDetails)request.getSession().getAttribute("admin");
 if(admin!=null){
 %>
<HTML>
	<HEAD>
		<TITLE>MDI</TITLE>
		<META NAME="Author" CONTENT="">
		<META http-equiv="Content-Type" content="text/html; charset=gb2312">
	</HEAD>
	 <FRAMESET ROWS="64,*" border="0" frameborder="0" FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0"
		MARGINHEIGHT="0" MARGINWIDTH="0" BORDERCOLOR="#6e8ade" name="IndexFst" >
			<FRAME SRC="top.htm" name="fraToolbar" id="fraToolbar" frameborder="no" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0"
			MARGINWIDTH="0" scrolling="no" noresize >
		<FRAMESET COLS="145,*" border="0" frameborder="0" FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0"
			MARGINHEIGHT="0" MARGINWIDTH="0" BORDERCOLOR="#6e8ade" name="mainframe">
				<FRAME src="left3.jsp" zIndex="1000" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" scrolling="yes"	MARGINWIDTH="0" noresize frameborder="no">
				<FRAME SRC="main.htm" NAME="fraMain" scrolling="no"	MARGINWIDTH="0" noresize frameborder="no" >
		</FRAMESET>
 	</FRAMESET>
</HTML>
<%}else{
 response.sendRedirect("index.jsp");

}%>



 
