<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8" import="net.lrc.util.DownloadDetails"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
<%@ page session="true"%>

<html> 
	<head>
		<title>下载页面</title>
	</head>
   	<style type="text/css">
	<!--
		body 
		{
			background-color:#9ACD32
		}
	-->
	</style>
  	<body>
  	<center>
    <%
       DownloadDetails dd=null;
       dd=(DownloadDetails)request.getAttribute("resource");
   	%>
   	<%
   		if(dd==null)
   		{ 
   	%>
 	<p>对不起您所查询的链接信息不存在！</p>
	<%
			return;
		} 
	%> 
    <table border="1" vAlign="middle">
    	<tr>
    		<td colspan="2"><strong>链接详情</strong></td>
    	</tr>
    	<tr>
    		<td><strong>名称：</strong></td>
    		<td><strong><%=dd.getName()%></strong></td>   
    	</tr>  
    	<tr>
    		<td ><strong>介绍：</strong></td>
    		<td><strong><%=dd.getInfo()%></strong></td>   
    	</tr> 
    	<tr>
    		<td><strong>类型：</strong></td>
    		<td><strong><%=dd.getType()%></strong></td>   
    	</tr>  	      
		<tr>
			<td><strong>大小：</strong></td>
			<td><strong><%=dd.getSize()%></strong></td>   
    	</tr> 	
    	<tr>
    		<td><strong>分类：</strong></td>
    		<td><strong><%=dd.getPart()%></strong></td>   
    	</tr> 	
     	<tr>
     		<td><strong>发布日期：</strong></td>
    		<td><strong><%=dd.getDate()%></strong></td>   
    	</tr>  
     	<tr>
     		<td><strong>最后修改日期：</strong></td>
    		<td><strong><%=dd.getLastModified()%></strong></td>   
    	</tr>
    	<tr>
    		<td><strong>下载次数：</strong></td>
    		<td><strong><%=dd.getCount()%></strong></td>   
    	</tr> 	
    	<tr>
    		<td colspan="2"><strong>资料提供: 少数民族语言分中心</strong></td>
    	</tr>	 
	 	<tr>
	 		<td >
	 			<a href="<%=request.getContextPath()%>/download/download.jsp?id=<%=dd.getId()%>"><strong><h4>点击下载</h4></strong></a> 
    		</td>
    		<td >
	 			<a href="#" onClick="javascript:window.close();"><strong><h4>关闭窗口</h4></strong></a>
    		</td>   
    	</tr> 				  
	 </table>          
	 </center>
  </body>
</html>
 