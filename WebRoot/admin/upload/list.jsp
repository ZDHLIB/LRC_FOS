<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="net.lrc.model.*,net.lrc.util.DownloadDetails,java.util.*,net.lrc.javabean.ResourceBean"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
<jsp:useBean id="bean" class="net.lrc.javabean.ResourceBean"></jsp:useBean>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<title>编辑界面</title>
	</head>
	<%Language.language="china"; %>
	<body>
		<table width="95%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="1">
					<b>增加上传</b>
				</th>
			</tr>
			<tr>
				<td height="30" class="forumrow">
					<center>
						<a href="<%=request.getContextPath()%>/admin/upload/upload.jsp"><u>增加上传</u></a>
					</center>
				</td>
			</tr>
		</table>
		<br>
		<table width="95%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="5">
					<b>下载文件列表</b>
				</th>
			</tr>
			<tr>
				<td width="20%" height="30" class="forumrow">
					<center>
						<u>文件名称</u>
					</center>
				</td>
				<td width="20%" height="30" class="forumrow">
					<center>
						<u>文件类型</u>
					</center>
				</td>
				<td width="5%" class="forumrow">
					<center>
						<u>文件大小</u>
					</center>
				</td>
				<td width="5%" class="forumrow">
					<center>
						<u>最后更新</u>
					</center>
				</td>
				<td width="30%" class="forumrow">
					<center>
						<u>操作</u>
					</center>
				</td>
			</tr>
			<%
				String offset=request.getParameter("offset");
				String fp=request.getRequestURI();
				bean.setResult(offset,fp);
				List resourcelist=bean.getResult();
				SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd");
				
				if(resourcelist!=null) 
				{
					Iterator i=resourcelist.iterator();
					while(i.hasNext()) 
					{
						DownloadDetails dd=(DownloadDetails)i.next();
			%>
			<tr>
				<td width="20%" height="25" class="forumRow">
					<a href="resource.do?method=getResource&language=china&id=<%=dd.getId()%>"><%=dd.getName()%></a>
				</td>
				<td width="5%" class="forumRow">
					<center><%=dd.getType()%></center>
				</td>
				<td width="5%" class="forumRow">
					<center><%=dd.getSize()%></center>
				</td>
				<td width="15%" class="forumRow">
					<center><%=dd.getLastmodified()%></center>
				</td>
				<td width="30%" class="forumRow">
					<center>
						<a href="#" onClick="javascript:window.open('resource.do?method=getResource&language=china&id=<%=dd.getId()%>','','width='+(screen.width/3)+',height='+(screen.availHeight/2)+',left='+(screen.width/3)+',top='+(screen.availHeight/3))">查看</a> |
						<a href="resource.do?method=updateStatus&language=china&id=<%=dd.getId()%>" onClick="if(confirm('是否审核通过此条资源？')){return(true);}else{return(false);}"><font color="#ff0000">审核</font></a> |
						<a href="resource.do?method=delete&language=china&id=<%=dd.getId()%>" onClick="if(confirm('是否删除此条资源？')){return(true);}else{return(false);}"><font color="#ff0000">删除</font></a>
					</center>
				</td>
			</tr>
			<%
					}
				}
			%>
		</table>
		<br>
		<center>
			<%
				out.print(bean.PageLegend());
			%>
		</center>
	</body>
</html>