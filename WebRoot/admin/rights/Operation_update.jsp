 <%@ page contentType="text/html; charset=utf-8" import="net.jtaq.utils.OperationDetails"%>
 <%@ include file="/admin/commons/pages/include.jsp" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
 <%
 OperationDetails od = (OperationDetails)request.getAttribute("operationDetails");
 String  operationName =  od.getOperationName();
 int operationID = od.getOperationID();
 String resume = od.getResume();
 %> 
    <center><strong>update Operation </strong>
      <font></font>
      <html:form action="/OperationAction" method="post">
	 Name: <br>
	  <html:text property="operationName" value="<%=operationName %>" />  
     
	   <br>
	 Resume:<br>
	   
	 <html:textarea property="resume" value="<%=resume %>" />  
	<html:hidden property="operationID"  value="<%=""+operationID %>"  />  
	 <html:hidden property="operatorID" value="101"/> 
	   
	 <html:hidden property="method" value="save"/>
	  <br> 
	   <html:submit></html:submit>
	 <html:reset></html:reset>
	  
      </html:form>
	 </center>
 
