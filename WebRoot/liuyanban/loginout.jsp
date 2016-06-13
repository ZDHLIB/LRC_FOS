<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*"   %>
<%
String pass=null;pass=(String)session.getAttribute("pass");
if(!"y".equals(pass))
out.print("<script language=javascript> alert('你好像没有登陆吧?');location.href('index.jsp');</script>");
else if("y".equals(pass))
session.setAttribute("pass","n");
out.print("<script language=javascript> alert('退出成功!欢迎再回来!');location.href('index.jsp');</script>");
%>