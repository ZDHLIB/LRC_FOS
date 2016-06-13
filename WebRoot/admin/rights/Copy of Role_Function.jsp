<%@ page language="java" import="java.util.*,net.jtaq.utils.FunctionDetails" pageEncoding="utf-8"%>
 <%@ include file="/admin/commons/pages/include.jsp" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
 <%@page session="true" %>
  <html>
   <SCRIPT language="javascript">
     function add(roleID,functionID,operatorID)
     {
	    document.Role_Fun.action="<%=request.getContextPath()%>/RoleFunctionAction.do?method=add&roleID="+roleID+"&functionID="+functionID+"&operatorID="+operatorID;
        document.Role_Fun.submit();
     }
     function remove(roleID,functionID)
     {
	document.Role_Fun.action="<%=request.getContextPath()%>/RoleFunctionAction.do?method=remove&roleID="+roleID+"&functionID="+functionID;
        document.Role_Fun.submit();
     }
     
  </SCRIPT>
 
  <body>
 角色功能列表
   <hr>
   <html:errors/>
 
  <logic:present name="RoleFunctionForm" property="isAdd">
  
  <logic:match name="RoleFunctionForm"  property="isAdd" value="true" > 
               
    添加功能成功！
      </logic:match>
   
   </logic:present>
   <logic:present name="RoleFunctionForm" property="isRemove">
  
   <logic:match name="RoleFunctionForm"  property="isRemove" value="true" > 
               
    删除功能成功！
      </logic:match>
      
   
   </logic:present>
   
   <form    name="Role_Fun"  method="post">
    
    
  <logic:present name="RoleFunctionForm" property="roleID">
      
        <logic:present name="RoleFunctionForm" property="functions">
   <hr>
    <bean:size id="size" name="RoleFunctionForm" property="functions"/>
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
    <tr>  <% int index=5; %>
    
    <logic:iterate id="function" name="RoleFunctionForm" property="functions">
      <% index--; %>
     <td><bean:write name="function"  property="functionName"/>    </td>
     <td>
     <%
        Collection functionsOfRole=(Collection)request.getAttribute("functionsOfRole");
           boolean  isExit=false;  
           Iterator i=functionsOfRole.iterator();
           
           while(i.hasNext()){
           FunctionDetails functionOfRole=(FunctionDetails)i.next();
           int id=functionOfRole.getFunctionID();
           String strid=""+id;
     %>      
      <logic:match name="function"  property="functionID" value="<%=strid%>" > 
      <input  onClick="remove(<bean:write name="RoleFunctionForm"  property="roleID"/>,<bean:write name="function"  property="functionID"/>);" type=checkbox name="functionID"  value="<bean:write name="function"  property="functionID"/>" checked> 
       <% 
       isExit=true;
        
       %>
       
      </logic:match>
     <%}%>  
   
     <%if(!isExit) {%>
        <input onClick="add(<bean:write name="RoleFunctionForm"  property="roleID"/>,<bean:write name="function"  property="functionID"/>,101);" type=checkbox name="functionID"  value="<bean:write name="function"  property="functionID"/>" > 
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
