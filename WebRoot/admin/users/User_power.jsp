<%@ page language="java"  contentType="text/html; charset=utf-8" import="java.util.*,net.jtaq.utils.RoleDetails"  %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
 <%@page session="true" %>
  <html>
   <SCRIPT language="javascript">
     function add(userID,roleID,operatorID)
     {
	    document.User_Role.action="<%=request.getContextPath()%>/UserRoleAction.do?method=add&userID="+userID+"&roleID="+roleID+"&operatorID="+operatorID;
        document.User_Role.submit();
     }
     function remove(userID,roleID)
     {
	document.User_Role.action="<%=request.getContextPath()%>/UserRoleAction.do?method=remove&roleID="+roleID+"&userID="+userID;
        document.User_Role.submit();
     }
     
  </SCRIPT>
 
  <body>
   用户授权
   <hr>
   <html:errors/>
  <logic:present name="UserRoleForm" property="isAdd">
  
  <logic:match name="UserRoleForm"  property="isAdd" value="true" > 
               
    添加角色成功！
      </logic:match>
   
   </logic:present>
   <logic:present name="UserRoleForm" property="isRemove">
  
   <logic:match name="UserRoleForm"  property="isRemove" value="true" > 
               
    删除角色成功！
      </logic:match>
      
   
   </logic:present>
   
   
   <form    name="User_Role"  method="post">
    
    
  <logic:present name="UserRoleForm" property="userID">
      
        <logic:present name="UserRoleForm" property="roles">
   <hr>
    <bean:size id="size" name="UserRoleForm" property="roles"/>
    <logic:equal name="size" value="0">
    <center>
    暂无记录！
    </center>
    </logic:equal>
   
   <logic:greaterThan name="size" value="0">
   
   <table width="100%" border="0" align="center" ellspacing="1" cellpadding="1" align="center" class="tableBorder" >
			可查看语料数量：<input type="text" id="view" name="view" value="0"></input><br>
			可下载语料数量：<input type="text" id="view" name="view" value="0"></input>
	</table>
</logic:greaterThan>   

 </logic:present>
 
</logic:present>
 
  </form>
  </body>
  </html>

