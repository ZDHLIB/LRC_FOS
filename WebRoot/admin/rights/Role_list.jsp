<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
<%@ page session="true"%>
  <html>
    <SCRIPT language="javascript">
     function update()
     {
	document.operationForm.action="<%=request.getContextPath()%>/RoleAction.do?method=update";
        document.operationForm.submit();
     }
     function delete()
     {
	document.operationForm.action="<%=request.getContextPath()%>/RoleAction.do?method=remove";
        document.operationForm.submit();
     }
     function list()
     {
	document.operationForm.action="<%=request.getContextPath()%>/RoleAction.do?method=list";
        document.operationForm.submit();
     }
  </SCRIPT>
  <title>Role List</title>
  <body>
   <html:errors/>
   <html:link forward="Role_add"> 增加角色 </html:link>
   <html:form   action="/RoleAction.do?method=list"  >
    
   <html:submit value="角色列表" ></html:submit>
   </html:form>
    
    <logic:present name="RoleForm" property="roles">
        <hr>
    <bean:size id="size" name="RoleForm" property="roles"/>
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
   <logic:iterate id="role" name="RoleForm" property="roles" >
    <tr>
     <td><bean:write name="role"  property="roleID"/>    </td>
     <td><bean:write name="role"  property="roleName"/>    </td>
     <td><bean:write name="role"  property="resume"/>    </td>
  
     <td><a href="<%=request.getContextPath()%>/RoleAction.do?method=update&roleID=<bean:write name="role"  property="roleID"/>">修改</a>   </td>
     <td><a href="<%=request.getContextPath()%>/RoleAction.do?method=remove&roleID=<bean:write name="role"  property="roleID"/>">删除</a> </td>
    <td><a href="<%=request.getContextPath()%>/RoleFunctionAction.do?method=list&roleID=<bean:write name="role"  property="roleID"/>">分配功能</a> </td>
    </tr>
      </logic:iterate>
  </table>
</logic:greaterThan>   

 </logic:present>

 
  </body>
  </html>
