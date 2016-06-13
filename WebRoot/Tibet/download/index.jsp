<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="net.lrc.model.ResourceKind"/>
<jsp:directive.page import="net.lrc.util.DownloadDetails"/>
<jsp:useBean id="commonBean" class="net.lrc.javabean.CommonBean"/>
<jsp:useBean id="theresource" class="net.lrc.javabean.ResourceBean"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	    <title>资源下载中心</title>
		<link href="default.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
	<div id="header">
		<h1><a href="#">在线资源(藏文)</a></h1>
		<h2><a href="#">少数民族语言研究分中心</a></h2>
	</div>
	<div id="page">
		<div id="content">
			<div id="welcome" class="post">
				<h2>欢迎来到资源下载中心</h2>
				<div class="content">
					<img src="images/5.jpg" alt="图片" width="120" height="120" class="left"/>
					<p><strong>NMLR</strong>上提供了各类与少数民族语言相关的资源及软件，目前主要分为以下几类：<a href="#">软件资源</a>、<a href="#">报纸资源</a>和<a href="#">网站资源</a>。其中软件资源主要为要少数民族语言的输入法软件，报纸资源主要为，网站资源主要为。您可以将希望我们网站提供的资源在留言板上给出。</p>
				</div>
			</div>
			<%
				List<ResourceKind> temp=theresource.getAllkind();
				if(temp.size()==0)
				{
					out.print("抱歉，暂时没有资源分类");
				}
				else
				{
					for(int i=0;i<temp.size();i++)
					{
						out.print("<div class='post'>");
				     	out.print("<h3 class='title'><a name='"+temp.get(i).getKind_id()+"'>"+temp.get(i).getKind_name()+"</a></h3>");
				    	out.print("<div class='content'><p><pre>"+temp.get(i).getKind_info()+"</pre></p></div>");
				    
				     	out.print("<ul>");
				       	List<DownloadDetails> temp2=theresource.getRecentlyResource(temp.get(i).getKind_id(),5);
				       	
				       	if(temp2.size()==0)
				       	{
				       		out.print("<li>对起不，暂无相关资源");
				       	}
				       	else
				       	{
					    	for(int j=0;j<temp2.size();j++)
					       	{
					       		out.print("<li>");
					         	out.print(temp2.get(j).getTitle()+"<br>"+temp2.get(j).getInfo()+"<br>");
					         	out.print(temp2.get(j).getLastmodified()+" | "+"<a href=resource.do?method=getResource&id="+temp2.get(j).getId()+" target='_blank'>详细</a> | <a href=download.jsp?id="+temp2.get(j).getId()+">下载</a>");
					         	out.print("</li>");
					       } 
				       	}
				      	out.print("</ul>");
				      	out.print("</div>");
				  	}
				}
			%>
		</div>
		<div id="sidebar">
			<div id="menu">
				<ul>
					<li><a href="<%=request.getContextPath()%>/Tibet/index.jsp" title="">首页</a></li>
					<%
						List<ResourceKind> temp1=theresource.getAllkind(); 
						for(int i=0;i<temp.size();i++)
						{
					%>
					<%		 
							out.print("<li><a href='#"+temp1.get(i).getKind_id()+"'>"+temp1.get(i).getKind_name()+"</a></li>");
					%>
					<%
						} 
					%>
				</ul>
			</div>
			<div id="updates" class="boxed">
				<h2 class="title">最近上传</h2>
				<div class="content">
					<ul>
					<% 
						List<DownloadDetails> tempre=theresource.getRecentlyResource(null,3);
						if(tempre.size()==0)
						{
						   out.print("<li>暂无更新资源</li>");
						}
					 	else
					 	{
					 		for(int i=0;i<tempre.size();i++)
						   	{
						    	DownloadDetails t=tempre.get(i);
						    	out.print("<li>["+t.getLastmodified()+"]"+t.getTitle()+"<br>上传人："+commonBean.getAdminNamebyID(t.getLrryID())+"</li>");
							}
						}
					%>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<p>Copyright 2008-2009@NMLR Research Center | Privacy Policy</p>
	</div>
	</body>
</html>