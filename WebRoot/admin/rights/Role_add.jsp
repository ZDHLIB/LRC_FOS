<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
  <%@ include file="/admin/commons/pages/include.jsp" %>
 
    <center><strong>角色增加</strong>
      <font></font>
      <html:form action="/RoleAction.do?method=add" method="post">
	  名称: <br>
	  <html:text property="roleName"></html:text>
     
	   <br>
	 说明:<br>
	 <html:textarea property="resume"></html:textarea><br>
	 <html:hidden property="operatorID" value="101"/> 
		  <html:submit value="确定" ></html:submit>
	 <html:reset value="重置" ></html:reset>
	  
      </html:form>
	 
