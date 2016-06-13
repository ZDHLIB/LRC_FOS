<%@ page language="java" import="java.util.*,net.jtaq.utils.FunctionDetails" pageEncoding="utf-8"%>
 <%@ include file="/admin/commons/pages/include.jsp" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
 <%@page session="true" %>
  <html>
   <SCRIPT language="javascript">
     function add()
     {
      var roleID=document.Role_Fun.roleID.value;
      var functionID=getcheckedfunctionIDStr();
     // alert("roleID="+roleID);
     // alert("functionID="+functionID);
	    document.Role_Fun.action="<%=request.getContextPath()%>/RoleFunctionAction.do?method=add&roleID="+roleID+"&functionID="+functionID;
        document.Role_Fun.submit();
     }
     
      function getcheckedfunctionIDStr() 
		  { 
		var strchoice=""; 
		for(var i=0;i<document.Role_Fun.functionID.length;i++) 
		{ 
		if (document.Role_Fun.functionID[i].checked) 
		{ 
		strchoice=strchoice+document.Role_Fun.functionID[i].value+","; 
		} 
		} 
		if (!document.Role_Fun.functionID.length) 
		{ 
		if (document.Role_Fun.functionID.checked) 
		{ 
		strchoice=document.Role_Fun.functionID[i].value;+"," 
		} 
		} 
		strchoice=strchoice.substring(0,strchoice.length-1); 
		 
		// alert(strchoice); 
		 return strchoice;
		} 
		
		 function selectfunctionIDALl() 
		  { 
		var strchoice=""; 
		for(var i=0;i<document.Role_Fun.functionID.length;i++) 
		{ 
		if (!document.Role_Fun.functionID[i].checked) 
		{ 
		document.Role_Fun.functionID[i].checked=true; 
		} 
		} 
		 
		} 
		 function delfunctionIDALl() 
		  { 
		var strchoice=""; 
		for(var i=0;i<document.Role_Fun.functionID.length;i++) 
		{ 
		if (document.Role_Fun.functionID[i].checked) 
		{ 
		document.Role_Fun.functionID[i].checked=false; 
		} 
		} 
		 
		} 
    
    function list() 
		  { 
		 window.location="<%=request.getContextPath()%>/RoleAction.do?method=list";
		} 
  </SCRIPT>
 
  <body>
 角色功能列表
   
   <html:errors/>
 
   
   
   <form    name="Role_Fun"  method="post">
    
    <input  id="roleID" name="roleID"   type="hidden"   value="<bean:write name="RoleFunctionForm"  property="roleID"/>"  > 
     
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
      <input   name="functionID" type=checkbox name="functionID"  value="<bean:write name="function"  property="functionID"/>" checked> 
       <% 
       isExit=true;
        
       %>
       
      </logic:match>
     <%}%>  
   
     <%if(!isExit) {%>
        <input  name="functionID"  type=checkbox name="functionID"  value="<bean:write name="function"  property="functionID"/>" /> 
       
      <%  isExit=false;  } %>
       
   
    </td>
    
    <% if(index==0){ %>
      <% out.print("</tr><tr>"); index=5;}   %>
     </logic:iterate>
      </tr>
   <tr><td  colspan="6"><input type="button" value="返回列表" onclick="list()"/><input type="button" value="全选" onclick="selectfunctionIDALl()"/><input type="button" value="全不选" onclick="delfunctionIDALl()"/> </td>  <td  colspan="4"><html:submit value="确认保存" onclick="add()"></html:submit></td></tr>
  </table>
</logic:greaterThan>   

 </logic:present>
 
</logic:present>
 
  </form>
  </body>
  </html>
