 <%@ page contentType="text/html; charset=utf-8" import="net.jtaq.utils.RoleDetails"%>
 <%@ include file="/admin/commons/pages/include.jsp" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
 <%
 RoleDetails rd = (RoleDetails)request.getAttribute("roleDetails");
 String  roleName =  rd.getRoleName();
 int roleID = rd.getRoleID();
 String resume = rd.getResume();
 %> 
    <center><strong>update Role </strong>
      <font></font>
      <html:form action="/RoleAction" method="post">
	 Name: <br>
	  <html:text property="roleName" value="<%=roleName %>" />  
     
	   <br>
	 Resume:<br>
	   
	 <html:textarea property="resume" value="<%=resume %>" />  
	<html:hidden property="roleID"  value="<%=""+roleID %>"  />  
	 <html:hidden property="operatorID" value="101"/> 
	   
	 <html:hidden property="method" value="save"/>
	   <br>
	   <html:submit></html:submit>
	 <html:reset></html:reset>
	  
      </html:form>
	 </center>
 
