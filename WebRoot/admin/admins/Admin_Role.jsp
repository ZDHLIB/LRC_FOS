<%@ page language="java"  contentType="text/html; charset=utf-8" import="java.util.*,net.jtaq.utils.RoleDetails" import="net.jtaq.utils.AdminDetails" %>
 
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
	 <br>
	 <br>
	   <%
  
		//AdminDetails ad = (AdminDetails)request.getSession().getAttribute("request.getParameter("adminName")");
 		%> 
	 <form method="post" action="admin/admins/OperatorUpdate.jsp">
	 	 <input  id="adminID" name="adminID"   type="hidden"   value="<%=request.getParameter("adminID")%>" />
	     <input type="radio" id="roleID" name="role" value="01" checked>语料管理员</input><br>
	     <input type="radio" id="roleID" name="role" value="02" >语料下载员</input><br>
	     <br>
		 <td>
		 	<input type="button" value="返回列表" onclick="list()"/> 
		 </td>  
		 <td>
		 	<html:submit value="确认保存"></html:submit> 
		 </td>

	 </form>
  </body>
  </html>
