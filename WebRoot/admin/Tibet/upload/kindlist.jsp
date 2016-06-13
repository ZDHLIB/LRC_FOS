<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*,net.lrc.model.*,net.lrc.model.ResourceKind"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<title>编辑界面</title>
	</head>
	<%Language.language="tibet"; %>
	<body>
		<table width="95%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="1">
					<b>增加种类</b>
				</th>
			</tr>
			<tr>
				<td height="30" class="forumrow">
					<center>
						<a href="<%=request.getContextPath()%>/admin/Tibet/upload/resourceKind.jsp"><u>增加种类</u></a>
					</center>
				</td>
			</tr>
		</table>
		<br>
		<table width="95%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="5">
					<b>资源种类列表</b>
				</th>
			</tr>
			<tr>
				<td width="20%" height="30" class="forumrow">
					<center>
						<u>种类名称</u>
					</center>
				</td>
				<td width="20%" height="30" class="forumrow">
					<center>
						<u>种类说明</u>
					</center>
				</td>
				<td width="30%" class="forumrow">
					<center>
						<u>操作</u>
					</center>
				</td>
			</tr>
			<%
				List kindList=(List)request.getAttribute("kindList");
				if(kindList!=null) 
				{
					Iterator i=kindList.iterator();
					while(i.hasNext()) 
					{
						ResourceKind kind=(ResourceKind)i.next();
			%>
			<tr>
				<td width="20%" height=25 class="forumRow">
					<center>
						<a href="resource.do?method=getResource&language=tibet&id=<%=kind.getKind_id()%>"><%=kind.getKind_name()%></a>
					</center>
				</td>
				<td width="5%" class="forumRow">
					<center><%=kind.getKind_info()%></center>
				</td>
				<td width="30%" class="forumRow">
					<center>
						<a href="resource.do?method=showkind&language=tibet&id=<%=kind.getKind_id()%>">编辑</a> |
						<a href="resource.do?method=deletekind&language=tibet&id=<%=kind.getKind_id()%>" onClick="if(confirm('是否删除此条资源种类？')){return(true);}else{return(false);}"><font color="#ff0000">删除</font></a>
					</center>
				</td>
			</tr>
			<%
					}
				}
			%>
		</table>
	</body>
</html>