<%@ page language="java"  contentType="text/html; charset=utf-8" import="java.util.*,net.jtaq.utils.RoleDetails"  %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
 <%@page session="true" %>
<html>
   <SCRIPT language="javascript">
	 
	function updatenum( userID, view, down )
	{
		var view=document.getElementById("view");
		var down=document.getElementById("down");
		document.User_Role.action="<%=request.getContextPath()%>/UserRoleAction.do?method=updatenum&userID="userID+"&view="+view+"&down="+down";
		document.User_Role.submit();
	}
	
	function showDIV() {
		var myDIV=document.getElementById("table2");
		var myDIV2=document.getElementById("table1");
		myDIV.style.display="block";
		myDIV2.style.display="none";
	}  
	function showDIV1() {
		var myDIV=document.getElementById("table1");
		var myDIV2=document.getElementById("table2");
		myDIV.style.display="block";
		myDIV2.style.display="none";
	} 
	
  </SCRIPT>
  <body>
   用户授权
   <hr>
   <input type="radio" name="power" id="power"  onclick="showDIV1()" checked/>普通用户
   <input type="radio" name="power" id="power"  onclick="showDIV()"/>设为管理员
   <form method="post"  >
	   <logic:present name="UserRoleForm" property="userID">   
	   <logic:present name="UserRoleForm" property="view">
	   <logic:present name="UserRoleForm" property="down">
		  <hr>
		  <div id="table1" style="position:absolute; display:block; height:700px; width:100%;">
		  	<table>
		  		<tr>
					<td>
						<div align="left">
							可查看语料数目：
						</div>
					</td>
					<td>
						<label>
						<logic:iterate id="view" name="UserRoleForm" property="view">
							<html:text   property="view" size="15"/>
							<!-- <input type="text" name="view" id="view" value="0" size="20" maxlength="20"/> -->
						</logic:iterate>
						</label>
					</td>
				</tr>
		  		<tr>
					<td>
						<div align="left">
							可下载语料数目：
						</div>
					</td>
					<td>
						<label>
						<logic:iterate id="down" name="UserRoleForm" property="down">
						 	<html:text   property= "down" size="15"/>
						</logic:iterate>	
							<!-- <input type="text" name="down" id="down" value="0" size="20" maxlength="20"/> -->
						</label>
					</td>
				</tr>
		  	</table>
		  	
		  	<table>
		  		<tr>
					<td>
						<div align="center">
							<label>
								
								<input onclick="updatenum( <bean:write name="UserRoleForm"  property="userID"/>,
														   <bean:write name="UserRoleForm"  property="view"/>,
														   <bean:write name="UserRoleForm"  property="down"/>)"
									type="submit" name="submit" id="submit" value="确定"	/>  
								<!--<input type="submit" id="submit" name="submit"  value="确定" onclick="updatenum(userID,view,down)">
	     						<input type="submit" id="submit" name="submit" value="确定">-->
							</label>
						</div>
					</td>
					<td>
						<label>
							<!--<html:reset value="取消"></html:reset>
							<input type="reset" name="cancel" id="cancel" value="取消" />-->
							<html:reset value="取消"></html:reset>
						</label>
					</td>
				</tr>
		  	</table>
		  </div>
	
		   <div id="table2" style="position:absolute; display:none; height:700px; width:100%;">
			   <table border="1">
				   <tr>
					   <th>名称</th>
					   <th>选择 </th>
				   </tr>   
			  </table>
		   </div>
		 </logic:present>
		 </logic:present>
		 </logic:present>
 	 </form> 
   </body>
</html>
