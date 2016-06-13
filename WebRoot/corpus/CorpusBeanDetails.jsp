<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="corpus.CorpusBean" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
	if((session.getAttribute("user")==null) || (session.getAttribute("admin")==null)) 
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
			<br>
			<h2>对不起，您尚未登录!
			<br>
			<h2>请您先登录或注册。
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>语料资源信息</title>
<link href="<%=request.getContextPath()%>/lingdot_files/style.css" type="text/css" rel="stylesheet">
</head>

<body>
<%
	CorpusBean corpusBean=(CorpusBean)request.getAttribute("corpusBean");
	int id=corpusBean.getType_id();
	
	if(corpusBean==null)
	{	
%>
		<h2><font color="#cccc33">对不起，您所请求的链接暂时无法访问，请稍后再访问！</font></h2>
<%
	}
	else
	{ 
%>
<form id="corpusSearchForm" name="corpusSearchForm" method="post" action="" onSubmit="return(FormCheck())">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table width="653" height="356" border="1" align="center" bordercolor="#66CCFF" bgcolor="#cccc33" >
  <tr>
  <td width="553">
  <table width="628" border="0">
    <caption align="left">
    <br />
    <br />
    <br />
      <center><strong>语料资源信息</strong></center>
      <br/>
    </caption>
    <tr>
      <td width="132"><div align="right">类别：</div></td>
      <td width="486"><%=corpusBean.getCorpusCategory(id)%></td>
    </tr>
    <tr>
      <td><div align="right">语种：</div></td>
      <td><%=corpusBean.getLanguage()%></td>
    </tr>
    <tr>
      <td><div align="right">时间：</div></td>
      <td><%=corpusBean.getDate()%></td>
    </tr>
    <tr>
      <td><div align="right">来源：</div></td>
      <td><%=corpusBean.getOrigin()%></td>
    </tr>
    <tr>
      <td><div align="right">标题：</div></td>
      <td><%=corpusBean.getTitle()%></td>
    </tr>
    <tr>
      <td><div align="right">作者：</div></td>
      <td><%=corpusBean.getAuthor()%></td>
    </tr>
    <tr>
      <td><div align="right">大小：</div></td>
      <td><%=corpusBean.getSize()%></td>
    </tr>
    <tr>
      <th colspan="2">
        <a href="<%=request.getContextPath()%>/corpus/corpusServlet?method=exportCorpus&id=<%=corpusBean.getId()%>">下载</a>
        &nbsp;
        <h2><a href="#" onClick="javascript:window.close();">关闭</a></h2>
	  </th>
	</tr>  
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p></td>
  </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
<%
	}
%>
</body>
<br>
<br>
<%@ include file="/footer.jsp"%>
<%
	}
%>
