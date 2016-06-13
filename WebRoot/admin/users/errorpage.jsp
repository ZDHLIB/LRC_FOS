<%@ include file="../../header.jsp" %>
<%@ page language="java" import="java.io.*" pageEncoding="utf-8" isErrorPage="true"%>


<html>
<head>
<LINK href="../images/style.css" type=text/css rel=stylesheet>
<title>Error page</title>
</head>
<body>
<center>
<br>服务器端发生错误：<%=exception.getMessage() %>
<p>错误原因为：<%exception.printStackTrace(new PrintWriter(out)); %>
<center> 
</body>
<%@ include file="../../footer.jsp" %>
</html>
