<%@ page language="java"  contentType="text/html; charset=utf-8" import="java.util.*,net.jtaq.utils.RoleDetails"  %>
 
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
 <%@page session="true" %>
  <html>
   <SCRIPT language="javascript">
     function add()
     {
        var adminID=document.Admin_Role.adminID.value;
        var roleID=getcheckedroleIDStr();
	    document.Admin_Role.action="<%=request.getContextPath()%>/AdminRoleAction.do?method=add&adminID="+adminID+"&roleID="+roleID;
        document.Admin_Role.submit();
     }
     
      function getcheckedroleIDStr() 
		  { 
		var strchoice=""; 
		for(var i=0;i<document.Admin_Role.roleID.length;i++) 
		{ 
		if (document.Admin_Role.roleID[i].checked) 
		{ 
		strchoice=strchoice+document.Admin_Role.roleID[i].value+","; 
		} 
		} 
		if (!document.Admin_Role.roleID.length) 
		{ 
		if (document.Admin_Role.roleID.checked) 
		{ 
		strchoice=document.Admin_Role.roleID[i].value;+"," 
		} 
		} 
		strchoice=strchoice.substring(0,strchoice.length-1); 
		//document.Fun_Oper.choiceid.value=strchoice; 
		// alert(strchoice); 
		 return strchoice;
		} 
      function selectroleIDALl() 
		  { 
		var strchoice=""; 
		for(var i=0;i<document.Admin_Role.roleID.length;i++) 
		{ 
		if (!document.Admin_Role.roleID[i].checked) 
		{ 
		document.Admin_Role.roleID[i].checked=true; 
		} 
		} 
		 
		} 
		 function delroleIDALl() 
		  { 
		var strchoice=""; 
		for(var i=0;i<document.Admin_Role.roleID.length;i++) 
		{ 
		if (document.Admin_Role.roleID[i].checked) 
		{ 
		document.Admin_Role.roleID[i].checked=false; 
		} 
		} 
		 
		} 
    
    function list() 
		  { 
		 window.location="<%=request.getContextPath()%>/AdminAction.do?method=list";
		} 
     
  </SCRIPT>
 
  <body>
   管理员角色列表
   
   <html:errors/>
 
   
   <form    name="Admin_Role"  method="post">
    
    
    <input  id="adminID" name="adminID"   type="hidden"   value="<bean:write name="AdminRoleForm"  property="adminID"/>"  > 
      
  <logic:present name="AdminRoleForm" property="adminID">
      
        <logic:present name="AdminRoleForm" property="roles">
   <hr>
    <bean:size id="size" name="AdminRoleForm" property="roles"/>
    <logic:equal name="size" value="0">
    <center>
    暂无记录！
    </center>
    </logic:equal>
    
   <logic:greaterThan name="size" value="0">
    
   <table border="1">
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
    <logic:iterate id="role" name="AdminRoleForm" property="roles">
      <% index--; %>
     
     <td><bean:write name="role"  property="roleName"/>    </td>
     <td>
     <%            
        Collection rolesOfAdmin=(Collection)request.getAttribute("rolesOfAdmin");
           boolean  isExit=false;  
           Iterator i=rolesOfAdmin.iterator();
           
           while(i.hasNext()){
           RoleDetails roleOfAdmin=(RoleDetails)i.next();
           int id=roleOfAdmin.getRoleID();
           String strid=""+id;
     %>      
      <logic:match name="role"  property="roleID" value="<%=strid%>" > 
      <input  id="roleID"  name="roleID" type=checkbox  value="<bean:write name="role"  property="roleID"/>" checked> 
       <% 
       isExit=true;
        
       %>
       
      </logic:match>
     <%}%>  
   
     <%if(!isExit) {%>
        <input  id="roleID"  name="roleID"  type=checkbox   value="<bean:write name="role"  property="roleID"/>" > 
      <%} %>
       
   
    </td>
    
   
    <% if(index==0){ %>
      <% out.print("</tr><tr>"); index=5;}   %>
     </logic:iterate>
      </tr>
   <tr><td  colspan="6"><input type="button" value="返回列表" onclick="list()"/><input type="button" value="全选" onclick="selectroleIDALl()"/><input type="button" value="全不选" onclick="delroleIDALl()"/> </td>  <td  colspan="4"><html:submit value="确认保存" onclick="add()"></html:submit></td></tr>
  </table>
</logic:greaterThan>   

 </logic:present>
 
</logic:present>
 
  </form>
  </body>
  </html>
