 <%@ page contentType="text/html; charset=utf-8" import="net.jtaq.utils.FunctionDetails"%>
 <%@ include file="/admin/commons/pages/include.jsp" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
 <%
 FunctionDetails od = (FunctionDetails)request.getAttribute("functionDetails");
 String  functionName =  od.getFunctionName();
 int functionID = od.getFunctionID();
 String resume = od.getResume();
 %> 
    <center>
    <strong>update Function </strong>
      <font></font>
      <html:form action="/FunctionAction" method="post">
	 Name: <br>
	  <html:text property="functionName" value="<%=functionName %>" />  
      <br>
	 Resume:<br>
	  <html:textarea property="resume" value="<%=resume %>" />  
	<html:hidden property="functionID"  value="<%=""+functionID %>"  />  
	 <html:hidden property="operatorID" value="101"/> 
	   
	 <html:hidden property="method" value="save"/>
	   <br>
	   <html:submit></html:submit>
	 <html:reset></html:reset>
	  
      </html:form>
	 </center>