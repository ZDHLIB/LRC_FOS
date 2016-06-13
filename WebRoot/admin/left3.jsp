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
  		<div style="float:left" id="my_menu" class="sdmenu"><!-- 
		    <div>
		        <span>资源管理</span>
		        <a href="#" onclick="AddWin('upload/upload.jsp','添加下载');">添加下载</a>
		        <a href="#" onclick="AddWin('resource.do?method=list&language=china','下载信息列表');">下载信息列表</a>
		        <a href="#" onclick="AddWin('upload/resourceKind.jsp','添加资源种类');">添加资源种类</a>
		        <a href="#" onclick="AddWin('resource.do?method=listkind&language=china','资源种类列表');">资源种类列表</a>
		        <a href="#" onclick="AddWin('Tibet/upload/upload.jsp','[藏]添加下载');">添加下载<font color="#ff0000">[藏文版]</font></a>
		        <a href="#" onclick="AddWin('resource.do?method=list&language=tibet','[藏]下载信息列表');">下载信息列表<font color="#ff0000">[藏文版]</font></a>
		        <a href="#" onclick="AddWin('Tibet/upload/resourceKind.jsp','[藏]添加资源种类');">添加资源种类<font color="#ff0000">[藏文版]</font></a>
		        <a href="#" onclick="AddWin('resource.do?method=listkind&language=tibet','[藏]资源种类列表');">资源种类列表<font color="#ff0000">[藏文版]</font></a>
		    </div>-->
		    <div>
		        <span>语料库管理</span>
		        <a href="#" onclick="AddWin('corpus/corpusServlet?method=ListAllCorpusCategory','语料种类列表');">语料种类列表</a>       	
		       	<a href="#" onclick="AddWin('corpus/corpusServlet?method=ListAllCorpusLanguage','语料语种列表');">语料语种列表</a>
		       	<a href="#" onclick="AddWin('corpus/corpusServlet?method=ListAllCorpusNetMedia','语料网络媒体类别列表');">语料网络媒体类别列表</a>
		        <a href="#" onclick="AddWin('corpus/corpusServlet?method=ListAllCorpusPaperMedia','语料纸质媒体类别列表');">语料纸质媒体类别列表</a>		       	
		       	<a href="#" onclick="AddWin('corpus/corpusServlet?method=listCorpus','语料信息列表');">语料信息列表</a>
		       	<a href="#" onclick="AddWin('corpus/AdminCorpusSearch.jsp','搜索并下载语料');">搜索并下载语料</a>	      	       	
		       	<a href="#" onclick="AddWin('corpus/UploadCorpus.jsp','单个上传语料');">单个上传语料</a>
		       	<a href="#" onclick="AddWin('corpus/UploadCorpusAll/SecondUploadCorpusAll.jsp','批量上传语料');">批量上传语料</a>
		    </div>
		    <div>
		        <span>管理人员</span>        
		        <a href="#" onclick="AddWin('admins/Admin_update.jsp','修改密码');">修改密码</a>
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

