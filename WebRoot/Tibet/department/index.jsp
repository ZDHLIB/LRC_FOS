<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="net.lrc.model.Department,net.lrc.model.Master"/>
     <jsp:useBean id="thenews" scope="page" class="net.lrc.javabean.NewsBean"></jsp:useBean>
     <%@ page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="departmentBean" class="net.lrc.javabean.DepartmentBean"></jsp:useBean>
<jsp:useBean id="commonBean" class="net.lrc.javabean.CommonBean"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>研究部门</title>

<link href="default.css" rel="stylesheet" type="text/css" />
 
</head>
<body>
<%    
 String depidtemp=(String)request.getParameter("id");  
 int depidint=0;
 if(null!=depidtemp){
  depidint=Integer.parseInt(depidtemp); 
} 
%>
<div id="header">
	<h1><a href="#"><%=commonBean.getDepNamebyID(depidint)%></a></h1>
	<h2><a href="<%=request.getContextPath()%>/index.jsp">གྲངས་ཉུང་མི་རིགས་ཀྱི་སྐད་ཡིག་ཞིབ་འཇུག་ལྟེ་བ</a></h2>
</div>
<div id="page">
	<div id="content">
		<div id="welcome" class="post">
		<%
		Master master=null;
		Department dep=null;
		   
		    String depid=(String)request.getParameter("id");
		     int id=0;
		     id=Integer.parseInt(depid);
			    dep=departmentBean.show(id) ;
			      master=departmentBean.showMaster(id);
		   
			  if(dep!=null){%>
			<h2><%=dep.getDepartmentname()%></h2>
			<div class="content"><img src="images/img4.jpg" alt="" width="120" height="120" class="left" />
				<p><strong>NMLR</strong></p>
			</div>
		</div>
		 <%   
				out.print("<div class=post>");
				out.print("<h3 class=title>"+dep.getDepartmentname()+"</h3>");
				out.print("<ul>");
				out.print("<li><a name=\"info\" ><strong>ལྡེ་ཁག་ངོ་སྤྲོད་</strong></a>");
				out.print("<br>"+dep.getDepartmentinfo()+"<br>");
				out.print("</li>");
				out.print("</ul>");
				
				out.print("<ul>");
				out.print("<li ><a name=\"task\" ><strong>ལྟེ་ཁག་་ལས་འགན་་</strong></a>");
				out.print("<br>"+dep.getDepartmenttask()+"<br>");
				out.print("</li>"); 
				out.print("</ul>");
				out.print("</div>");
			  
			}
		%><%
		
		if(master!=null)
		{%>
			 
		 <%      out.print("<div  class=post>");
			     out.print("<h3 class=title><a name=\"master\" >&nbsp;部门领导:"+master.getName()+"</a></h3>");
			     out.print("<ul>");
			     out.print("<li><strong>工作经验</strong>");
			     out.print("<br>"+master.getWorkexperience()+"<br>");
			     out.print("</li>");
			     out.print("</ul>");
			     
			     out.print("<ul>");
			     out.print("<li><strong>学习经历</strong>");
			     out.print("<br>"+master.getStudyexperience()+"<br>");
			     out.print("</li>");
			     out.print("</ul>");
			     
			     out.print("<ul>");
			     out.print("<li><strong>研究领域</strong>");
			     out.print("<br>"+master.getResearch()+"<br>");
			     out.print("</li>");
			     out.print("</ul>");
			     
			     out.print("</div>");
			  
			} %>
	</div>
	
	<div id="sidebar">
		<div id="menu">
			<ul>
				<li><a href="<%=request.getContextPath()%>/Tibet/index.jsp" title="">མདུན་ཤོག་</a></li>
				<li><a href="#info" title="">ལྡེ་ཁག་ངོ་སྤྲོད་</a></li>
				<li><a href="#master"title="">部门领导</a></li>
				<li class="active"><a href="#task" title="部门职责">ལྟེ་ཁག་་ལས་འགན་་</a></li>
				</ul>
		</div>
		
		<div id="updates" class="boxed">
			<h2 class="title">最近部门动态</h2>
			<div class="content">
			<table>
				<%
		   
		   	List list = thenews.listByJGDM(depidint,20);
		   
		   if(list == null) System.out.println("error");
		   SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
										if (list != null && list.size() > 0) {
											for (Object news : list) {
											net.lrc.util.NewsDetails n = (net.lrc.util.NewsDetails)news;
												 
												String date = ss.format(n.getSubmittime());
		   
		 %>
                    <TR>
                      <TD vAlign=top height=21><IMG height=10 
                        src="newsList_files/p_02.gif" width=10></TD>
                      <TD vAlign=top><A 
                        href="<%=request.getContextPath()%>/news/news.do?method=new&contentid=<%=n.getKind()%>&partid=<%=n.getType()%>&id=<%=n.getId()%>" 
                        target=_blank><%=n.getTitle()%>&nbsp;[<%=date%>]</A>&nbsp;</TD></TR>
                   <%
										}
										}
									%>
									</table>     
			</div>
		</div>

	</div>
	<div style="clear: both;">&nbsp;</div>

<div id="footer">
	<p>Copyright 2008-2009@NMLR Research Center | Privacy Policy</p>
</div>
</body>
</html>