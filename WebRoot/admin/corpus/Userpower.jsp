<%@page contentType="text/html;charset=utf-8"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
<%@page import="net.lrc.model.User,net.lrc.javabean.UserBean,java.util.*"%>
<html>
	<HEAD>
		<TITLE>人员编辑界面</TITLE>
		<LINK REL="stylesheet" TYPE="text/css" HREF="admin.css">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	</HEAD>
	<body>
 	<table width="95%" border="0" cellspacing="1" cellpadding="3"
			align=center class="tableBorder">
			<tr>
				<th height=24 colspan=1>
					<B>添加用户</B>
				</th>
			</tr>
			<tr>
				<td height=30 class=forumrow>
					<center>
						<a href="<%=request.getContextPath()%>/admin/users/reg.jsp"><u>添加用户</u> </a>
					</center>
				</td>
			</tr>
		</table>
		<br>

		<table width="95%" border="0" cellspacing="1" cellpadding="3"
			align=center class="tableBorder">
			<tr>
				<th height=24 colspan=6>
					<B>用户列表</B>
				</th>
			</tr>
			<tr>
				<td width="5%" height=30 class="forumrow">
					<center>
						<u>用户名称</u>
					</center>
				</td>
				<td width="5%" class="forumrow">
					<center>
						<u>姓名</u>
					</center>
				</td>
				<td width="5%" class="forumrow">
					<center>
						<u>性别</u>
					</center>
				</td>
				<td width="5%" class="forumrow">
					<center>
						<u>单位</u>
					</center>
				</td>
				<td width="5%" class="forumrow">
					<center>
						<u>登陆次数</u>
					</center>
				</td>
				<td width="30%" class="forumrow">
					<center>
						<u>操作</u>
					</center>
				</td>
			</tr>
			<%
				//String offset = request.getParameter("offset");
				//String fp = request.getRequestURI();
				//userbean.setResult(offset, fp);
				String x = "男";
				UserBean  userbean=(UserBean)request.getAttribute("userBean");
				List list = userbean.getResult();
				if (list == null)
					System.out.println("error");
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					User user = (User) iterator.next();
					if( user.getGender().equals("0")){
						x = "女";
					}
			%>
			<tr>
				<td width="50%" height=25 class="forumRow">
					<a href="user.do?method=edit&id=<%=user.getId()%>"><%=user.getLoginName()%></a>
				</td>
				
				<td width="5%" class="forumRow">
					<center><%=user.getName()%></center>
				</td>
				<td width="5%" class="forumRow">
					<%=x%>
				</td>
				<td width="5%" class="forumRow">
				 <center><%=user.getDepartment()%>
				</td>
				<td width="15%" class="forumRow">
					 <%=user.getLoginCount()%> 
				</td>
				<td width="30%" class="forumRow">
					<center>
					</center>
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<br>
		<center>
		<%=userbean.PageLegend()	%>
		</center>
	</body>
</html>