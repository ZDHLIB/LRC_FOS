<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="net.lrc.model.*,java.util.*" pageEncoding="utf-8" %>
<%@ page session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	net.jtaq.utils.AdminDetails admin=(net.jtaq.utils.AdminDetails)request.getSession().getAttribute("admin");
 	if(admin!=null)
 	{
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<title>Slashdot's Menu</title>
		<link rel="stylesheet" type="text/css" href="sdmenu/sdmenu.css"/>
		<script type="text/javascript" src="sdmenu/sdmenu.js"></script>
		<script type="text/javascript">
			var myMenu;
			
			window.onload=function()
			{
				myMenu=new SDMenu("my_menu");
				myMenu.init();
			};

			top.document.title="系统管理后台"; 

			function RemoveAll()
			{
				top.fraMain.win.removeall();
			}

			function AddWin(url,title)
			{
				top.fraMain.AddWin(url,title);
			}
			
		</script>
		<script id="DataLoad" language="JavaScript" type="text/javascript" defer></script>
  	</head>
  	<body bgcolor="#4169E1">
  		<div style="float:left" id="my_menu" class="sdmenu">
		    <div>	 <span>用户管理</span>
		        <a href="#" onclick="AddWin('users/reg.jsp','添加用户');">添加用户</a>
		        <a href="#" onclick="AddWin('user.do?method=list','用户列表');">用户列表</a>
		        <!-- <a href="#" onclick="AddWin('corpus/corpusServlet?method=ListAllusers','用户列表');">用户权限</a> -->
		    </div>
		    <div>
		        <span>语料库管理</span>
		       	<a href="#" onclick="AddWin('textbook/addTextBook.jsp','录入教材');">录入教材</a>
		       	<a href="#" onclick="AddWin('textbook/searchTextbook.jsp','查找教材');">查找教材</a>
		       	<a href="#" onclick="AddWin('textbook/addTextDetail.jsp','批量录入课文');">批量录入课文</a>
		       	<a href="#" onclick="AddWin('textbook/searchTextDetail.jsp','查找课文');">查找课文</a>	       	
		    </div>
		    <div>
		        <span>管理人员</span>        
		        <a href="#" onclick="AddWin('admins/Admin_add.jsp','增加管理员');">增加管理员</a>
		        <a href="#" onclick="AddWin('admins/Admin_list.jsp','管理员列表');">管理员列表</a>
		        <a href="#" onclick="AddWin('admins/Admin_update.jsp','修改密码');">修改密码</a>
		    </div>
		    <div>
		        <span class="block">权限管理</span>
		        <a href="#" onclick="AddWin('admins/Admin_list.jsp','分配管理员权限');">分配管理员权限</a>
		    </div>
		</div>	
  	</body>
</html>
<%
	}
	else
	{
 		response.sendRedirect("index.jsp");
	}
%>

