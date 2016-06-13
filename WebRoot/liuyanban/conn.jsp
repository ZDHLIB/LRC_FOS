<%@ page  language="java" import="java.sql.*,java.util.*,java.io.*" errorPage=""%>
<%
 String user="root";
 String pw="123456";
 String tab="xinxi"; 
Class.forName("com.mysql.jdbc.Driver").newInstance();
String url="jdbc:mysql://localhost:3306/lrc?useUnicode=true&characterEncoding=utf-8";
Connection con=DriverManager.getConnection(url,user,pw);
Statement st=con.createStatement();
%>