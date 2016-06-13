<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
  <html>
   <SCRIPT language="javascript">
     function update()
     {
	document.operationForm.action="<%=request.getContextPath()%>/OperationAction.do?method=update";
        document.operationForm.submit();
     }
     function delete()
     {
	document.operationForm.action="<%=request.getContextPath()%>/OperationAction.do?method=remove";
        document.operationForm.submit();
     }
     function list()
     {
	document.operationForm.action="<%=request.getContextPath()%>/OperationAction.do?method=list";
        document.operationForm.submit();
     }
  </SCRIPT>
  <title>Operation List</title>
  <body>
   <html:errors/>
   <html:link forward="Operation_add"> 增加操作 </html:link>
   <html:form   action="/OperationAction.do?method=list"  >
    
   
       <html:hidden name="all" property="all" value="all"/> 
	   
    
      <html:submit value="操作列表" ></html:submit>
     </html:form>
      
        <logic:present name="OperationForm" property="operations">
        <hr>
    <bean:size id="size" name="OperationForm" property="operations"/>
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
   <th colspan=2>操作 </th>
    </tr>
   <logic:iterate id="operation" name="OperationForm" property="operations">
    <tr>
     <td><bean:write name="operation"  property="operationID"/>    </td>
     <td><bean:write name="operation"  property="operationName"/>    </td>
     <td><bean:write name="operation"  property="resume"/>    </td>
  
     <td><a href="<%=request.getContextPath()%>/OperationAction.do?method=update&operationID=<bean:write name="operation"  property="operationID"/>">修改</a>   </td>
     <td><a href="<%=request.getContextPath()%>/OperationAction.do?method=remove&operationID=<bean:write name="operation"  property="operationID"/>">删除</a> </td>
    </tr>
      </logic:iterate>
  </table>
</logic:greaterThan>   

 </logic:present>

 
  </body>
  </html>
