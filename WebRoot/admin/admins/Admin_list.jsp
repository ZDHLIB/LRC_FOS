<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
<html>
	<title>管理员列表</title>
	<body>
		管理员
		<hr>
		<html:errors/>
		<html:link forward="Admin_add">增加管理员</html:link>
		<html:form action="/AdminAction.do?method=list" method="post">
			<html:submit value="管理员列表"></html:submit>
		</html:form>

<!-- name refers to the bean,and property refers to the property in the bean -->
		<logic:present name="AdminForm" property="admins">
			<hr/>
			<bean:size id="size" name="AdminForm" property="admins" />
			<logic:equal name="size" value="0">
				<center>
					暂无记录！
				</center>
			</logic:equal>

			<logic:greaterThan name="size" value="0">

				<table border="1">
					<tr>
						<th>
							&nbsp;ID&nbsp;
						</th>
						<th>
							&nbsp;名称&nbsp;
						</th>
						<th colspan=3>
							&nbsp;操作&nbsp;
						</th>
					</tr>
					<logic:iterate id="admin" name="AdminForm" property="admins">
						<tr>
							<td>
								<bean:write name="admin" property="adminID" />
							</td>
							<td>
								<bean:write name="admin" property="adminName" />
							</td>
							<td>
								<a href="<%=request.getContextPath()%>/AdminAction.do?method=update&adminID=<bean:write name="admin"  property="adminID"/>">修改密码</a>
							</td>
							<td>
								<a href="<%=request.getContextPath()%>/AdminAction.do?method=remove&adminID=<bean:write name="admin"  property="adminID"/>">删除</a>
							</td>
							<td>
								<a href="<%=request.getContextPath()%>/AdminRoleAction.do?method=list&adminID=<bean:write name="admin"  property="adminID"/>">分配角色</a>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</logic:greaterThan>
		</logic:present>
	</body>
</html>
