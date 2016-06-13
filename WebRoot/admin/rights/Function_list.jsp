<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
  <html>
   <SCRIPT language="javascript">
     function update()
     {
	document.operationForm.action="<%=request.getContextPath()%>/FunctionAction.do?method=update";
        document.operationForm.submit();
     }
     function delete()
     {
	document.operationForm.action="<%=request.getContextPath()%>/FunctionAction.do?method=remove";
        document.operationForm.submit();
     }
     function list()
     {
	document.operationForm.action="<%=request.getContextPath()%>/FunctionAction.do?method=list";
        document.operationForm.submit();
     }
  </SCRIPT>
  <title>Function List</title>
  <body>
  
   <html:errors/>
   <html:link forward="Function_add"> 增加功能 </html:link>
   <html:form   action="/FunctionAction.do?method=list"  >
    
   <html:submit value="功能列表" ></html:submit>
   </html:form>
    <logic:present name="FunctionForm" property="functions">
        <hr>
    <bean:size id="size" name="FunctionForm" property="functions"/>
    <logic:equal name="size" value="0">
    <center>
    暂无记录！
    </center>
    </logic:equal>
   
   <logic:greaterThan name="size" value="0">
   
   <table border="2">
   <tr>
   <th>ID</th>
   <th>名称</th>
   <th>说明</th>
   <th colspan=3>操作 </th>
    </tr>
   <logic:iterate id="function" name="FunctionForm" property="functions" >
    <tr>
     <td><bean:write name="function"  property="functionID"/>    </td>
     <td><bean:write name="function"  property="functionName"/>    </td>
     <td><bean:write name="function"  property="resume"/>    </td>
  
     <td><a href="<%=request.getContextPath()%>/FunctionAction.do?method=update&functionID=<bean:write name="function"  property="functionID"/>">修改</a>   </td>
     <td><a href="<%=request.getContextPath()%>/FunctionAction.do?method=remove&functionID=<bean:write name="function"  property="functionID"/>">删除</a> </td>
     <td><a href="<%=request.getContextPath()%>/FunctionOperationAction.do?method=list&functionID=<bean:write name="function"  property="functionID"/>">分配操作</a> </td>
    </tr>
      </logic:iterate>
  </table>
</logic:greaterThan>   

 </logic:present>

 
  </body>
  </html>
