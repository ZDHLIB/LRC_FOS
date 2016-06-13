<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="corpus.CorpusBean"%>
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	if( Integer.parseInt(request.getParameter("down")) == 0 ) 
	{
%> 
<html>
	<head>
	</head>
	<body bgcolor="#cccc33">
		<center>
			<br>
			<br>
			<br>
			<h2>对不起，您可下载的语料条数为0，请和管理员联系!
			<br>
			<br>
			<br>
			<br>
			<a href="#" onClick="javascript:window.close();">关闭窗口</a>
			<br>
			<br>
			<br>
			<br>
		</center>
	</body>
</html>
<%
	} 
	else 
	{
%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	</head>
	<body bgcolor="#cccc33">
			
		<center>
			<br>
			<br>
			<br>
			<h2>下载语料会减少您可下载语料的数目
			<br>
			<br>是否确定下载？
			<br>
			<br>
			<!-- <form action="net.lrc.servlet/UerServlet?method=LoginView" method="post"> -->
			<a href="<%=request.getContextPath()%>/textBookServelet?method=downloadDetail2&path=<%=request.getParameter("path")%>&down=<%=request.getParameter("down")%>&downed=<%=request.getParameter("downed")%>&userID=<%=request.getParameter("userID")%>&username=<%=request.getParameter("username")%>&fileID=<%=request.getParameter("fileID")%>">确定查看</a>
			<a href="#" onClick="javascript:window.close();">取消</a>
			<br>
			<br>
			<br>
			<br>
		</center>
	</body>
</html>
<%}%>