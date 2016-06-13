<%@page import="java.sql.*" %>
<%@page import="com.mysql.jdbc.Driver" %>
<%@page import="java.io.*" %>
<%@ page language="java"  contentType="text/html; charset=utf-8" import="java.util.*,net.jtaq.utils.RoleDetails" import="net.jtaq.utils.AdminDetails" %>
 
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
 <%@page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF">
<title>添加数据</title>
</head>
<body>

 <h1 align="center">管理员权限分配成功！</h1><hr>
<center>
  
  <%
   String driverName = "com.mysql.jdbc.Driver";
   String userName = "root";
   String passWord = "123456";
   String dbName = "lrcos";
  // String tableName = "usermanager";
   
   String url = "jdbc:mysql://localhost/" + dbName +"?user=" + userName +"&password=" +passWord;
   Class.forName(driverName).newInstance();
   Connection conn = DriverManager.getConnection(url);
   Statement stat = conn.createStatement();
   
   //用statement 一次添加一条记录
   //String sql = "insert into usermanager values('1010','jack','m')";
   //stat.executeUpdate(sql);
  // out.println("statement,添加数据成功！<p>");
   
   //用PreparedStatement 添加多条记录
  // String sql3 = "insert into usermanager values(?,?,?)";
  // PreparedStatement pre = conn.prepareStatement(sql3);
  /* 
   //添加第一条记录
   pre.setString(1,"1005");
   pre.setString(2,"燕妮");
   pre.setString(3,"女");
   pre.executeUpdate();
   
   //添加第二天记录
   pre.setString(1,"1006");
   pre.setString(2,"张三");
   pre.setString(3,"男");
   pre.executeUpdate();
   
   //添加第三条记录
   pre.setString(1,"1007");
   pre.setString(2,"Tom");
   pre.setString(3,"男");
 // pre.executeUpdate();
   out.println("PreparedStatement,添加数据成功！<p>");
   
   
   //删除数据
      String sql1 = "delete from usermanager where name = 'jack'";
      int row1 = stat.executeUpdate(sql1);
      out.print(row1 + "条记录被删除！");*/
      
      //修改数据
      Integer role = Integer.parseInt(request.getParameter("role"));
      Integer adminID = Integer.parseInt(request.getParameter("adminID"));
      String sql = "update admin set OperatorID="+role+" where Admin_ID="+adminID;
	  int row = stat.executeUpdate(sql);
      stat.close();
      conn.close();
   %>
   <a href="Admin_list.jsp" style="font-size:15px;">返回管理员列表</a>
   </center>
</body>
</html>

