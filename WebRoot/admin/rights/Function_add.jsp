<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
  
 
    <center><strong>增加功能</strong>
      <font></font>
      <html:form action="/FunctionAction.do?method=add" method="post">
	  名称: <br>
	  <html:text property="functionName"></html:text>
     
	   <br>
	 功能说明:<br>
	 <html:textarea property="resume"></html:textarea><br>
	 <html:hidden property="operatorID" value="101"/> 
	  
	
		  <html:submit value="确定" ></html:submit>
	 <html:reset value="重置" ></html:reset>
	</html:form>
	 
