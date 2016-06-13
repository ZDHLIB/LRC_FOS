<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
 
  
  <body>
 
	 <%
	  out.println("欢迎你来到管理后台！");
	   
	  %>
	  <logic:redirect forward="admin_mainFrame"/> 
</body>
</html:html>
