<%@ page language="java" import="net.lrc.model.*,net.lrc.model.ResourceKind" pageEncoding="utf-8"%>
<%@ include file="/admin/commons/pages/include.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加资源种类</title>
	</head>
	<%Language.language="china"; %>
	<body>
		<%
			ResourceKind kind=(ResourceKind)request.getAttribute("resourceKind");
			if(kind==null) 
			{
		%>
		<form name="form1" action="resource.do?method=addkind&language=china" method="post">
			<table>
				<tr>
					<td>
						种类名称：
					</td>
					<td>
						<input name="kindname" type="text"/>
					</td>
				</tr>
				<tr>
					<td>
						种类说明：
					</td>
					<td>
						<textarea name="kindinfo"></textarea>
					</td>
				</tr>
			</table>
			<input type="submit" value="提交">
		</form>
		<%
			} 
			else 
			{
		%>
		<form name="form2" action="resource.do?method=updatekind&language=china" method="post">
			<table>
				<tr>
					<td>
						种类名称：
					</td>
					<td>
						<input name="kindname" value="<%=kind.getKind_name()%>"/>
						<input name="id" type="hidden" value="<%=kind.getKind_id()%>"/>
					</td>
				</tr>
				<tr>
					<td>
						种类说明：
					</td>
					<td>
						<textarea name="kindinfo"><%=kind.getKind_info()%></textarea>
					</td>
				</tr>
			</table>
			<input type="submit" value="提交">
		</form>
		<%
			}
		%>
	</body>
</html>
