<%@ page language="java" import="java.util.*,net.jtaq.utils.OperationDetails" pageEncoding="utf-8"%>
 <%@ include file="/admin/commons/pages/include.jsp" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
   <SCRIPT language="javascript">
     function add(functionID,operationID,operatorID)
     {
	    document.Fun_Oper.action="<%=request.getContextPath()%>/FunctionOperationAction.do?method=add&functionID="+functionID+"&operationID="+operationID+"&operatorID="+operatorID;
        document.Fun_Oper.submit();
     }
     function remove(functionID,operationID)
     {
	document.Fun_Oper.action="<%=request.getContextPath()%>/FunctionOperationAction.do?method=remove&functionID="+functionID+"&operationID="+operationID;
        document.Fun_Oper.submit();
     }
     
  </SCRIPT>
  <title>Operation List</title>
  <body>
  功能操作列表
   <hr>
   <html:errors/>
 
  <logic:present name="FunctionOperationForm" property="functionID">
  
  <logic:match name="FunctionOperationForm"  property="isAdd" value="true" > 
               
    添加操作成功！
      </logic:match>
  <logic:match name="FunctionOperationForm"  property="isRemove" value="true" > 
               
    删除操作成功！
      </logic:match>
   </logic:present>
   <form    name="Fun_Oper"  method="post">
    
    
  <logic:present name="FunctionOperationForm" property="functionID">
      
        <logic:present name="FunctionOperationForm" property="operations">
   <hr>
    <bean:size id="size" name="FunctionOperationForm" property="operations"/>
    <logic:equal name="size" value="0">
    <center>
    暂无记录！
    </center>
    </logic:equal>
   
   <logic:greaterThan name="size" value="0">
   
   <table border="2">
   <tr>
   <th>名称</th>
    <th>选择 </th>
    <th>名称</th>
    <th>选择 </th>
    <th>名称</th>
    <th>选择 </th>
    <th>名称</th>
    <th>选择 </th>
    <th>名称</th>
    <th>选择 </th>
    </tr>
    <tr>
    <% int index=5; %>
    <logic:iterate id="operation" name="FunctionOperationForm" property="operations">
     <% index--; %>
     <td><bean:write name="operation"  property="operationName"/>    </td>
     <td>
     <%
        Collection operationsOfFunction=(Collection)request.getAttribute("operationsOfFunction");
           boolean  isExit=false;  
           Iterator i=operationsOfFunction.iterator();
           
           while(i.hasNext()){
           OperationDetails operationOfFunction=(OperationDetails)i.next();
           int id=operationOfFunction.getOperationID();
           String strid=String.valueOf(id);
         
     %>      
      <logic:match name="operation"  property="operationID" value="<%=strid%>" > 
      <input  id="<bean:write name="operation"  property="operationID"/>" name="<bean:write name="operation"  property="operationID"/>" onClick="remove(<bean:write name="FunctionOperationForm"  property="functionID"/>,<bean:write name="operation"  property="operationID"/>);" type=checkbox   value="<bean:write name="operation"  property="operationID"/>" checked> 
       <% 
       isExit=true;
        
       %>
       
      </logic:match>
     <%}%>  
   
     <%if(!isExit) {%>
        <input id="<bean:write name="operation"  property="operationID"/>"  name="<bean:write name="operation"  property="operationID"/>" onClick="add(<bean:write name="FunctionOperationForm"  property="functionID"/>,<bean:write name="operation"  property="operationID"/>,101);" type=checkbox    value="<bean:write name="operation"  property="operationID"/>" > 
      <%} %>
      
    </td>
    <% if(index==0){ %>
      <% out.print("</tr><tr>"); index=5;}   %>
     </logic:iterate>
      </tr>
  </table>
</logic:greaterThan>   

 </logic:present>
 
</logic:present>
 
  </form>
  </body>
  </html>
