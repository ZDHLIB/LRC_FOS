<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
<jsp:directive.page import="net.lrc.model.Department"/>
<jsp:useBean id="departmentBean" class="net.lrc.javabean.DepartmentBean"></jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>增加管理员</title>
	</head>
	
	<body>
		<strong>增加管理员</strong>
      	<html:form action="/AdminAction.do?method=add" method="post">
      		用户名：<html:text property="adminName"/><br>
	 		密  码： <html:text property="password"/><br>
	 		<html:select property="operatorID" value="1" style="display:none;">
	 			<option value="1">请选择</option>
<%
				List departments = departmentBean.list();
         	
         		if(departments != null) 
         		{
         			Iterator i = departments.iterator();
         			
         			while(i.hasNext())
         			{
         				Department department = (Department)i.next();
%>
						<option value="<%=department.getDepartmentid()%>"><%=department.getDepartmentname()%></option>
<%
					}
				} 
%>
	 		</html:select><br>
	 	  	<html:submit value="确定"></html:submit>
			<html:reset value="取消"></html:reset>
		</html:form>
	</body>
</html>