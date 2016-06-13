<%@ page language="java" import="net.lrc.model.User" pageEncoding="utf-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<title>首页:少数民族语言分中心</title>
		<link href="<%=request.getContextPath()%>/lingdot_files/style.css" type="text/css" rel="stylesheet">
		<script language="javascript">
	    	setInterval("dateName.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000)
		</script>
		<script language="javascript">
			function showDIV()
			{
				var myDIV=document.getElementById("login");
				myDIV.style.display="block";
			}
			</script>
			<%
				User u=(User)request.getSession().getAttribute("user");
			%>
	</head>		
	<table cellSpacing="5" cellPadding="0" width="968" border="0" align="center">
		<tbody>
			<tr>
				<td vAlign="middle" align="left" rowSpan="2" class="header_tool_bar_ch">
					<font color="#cccc33"><span id="dateName"></span></font>
				</td>
				<td vAlign="middle" align="left" rowSpan="2" class="header_tool_bar_ch">
					<%
						if(u!=null) 
						{
					%>
					
					<font color="#cccc33">您好，<%=u.getLoginName()%>！</font>
					<font color="#cccc33"><a href="<%=request.getContextPath()%>/users/history.jsp?username=<%=u.getLoginName()%>">语料操作历史记录</a></font>
					<br>
					<font color="#cccc33">您还能浏览<%=u.getView()%>条语料，下载<%=u.getDown()%>条语料！</font>
					<%
						} 
						else 
						{
					%>
					<div id="login" style="position:absolute;display:none">
						<form action="user.do?method=login" method="post" >
							<table>
								<tr>
									<td class="header_tool_bar_ch">
										用户名:<input type="text" name="username"/>
									</td>
									<td class="header_tool_bar_ch">
										密码:<input type="password" name="password"/>
									</td>
									<td class="header_tool_bar_ch">
										<input type="submit" value="提交"/>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<%
						}
					%>
				</td>
				<td align="right">
					<form name="search_form">
						<table cellSpacing="0" cellPadding="0" width="500" border="0">
							<tbody>
								<tr bgColor="#cccc33" height="24" align="center" vAlign="middle">
									<td class="header_tool_bar_ch">
										<a href="#">中文版&nbsp;</a><font color="#ffffff">|</font>
										<a href="<%=request.getContextPath()%>/Tibet/index.jsp">&nbsp;藏文版</a><font color="#ffffff"></font>
									</td>
									<td class="header_tool_bar_ch">
										<%
											if(u==null) 
											{
										%>
										<a href="#" onClick="showDIV()">用户登录</a><font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</font>
									</td>
									<td class="header_tool_bar_ch">
										<a href="<%=request.getContextPath()%>/users/reg.jsp">用户注册</a><font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</font>
										<%
											} 
											else 
											{
										%>
										<a href="<%=request.getContextPath()%>/users/user.do?method=logout">用户注销</a><font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</font>
									</td>
									<td class="header_tool_bar_ch">
										<a href="<%=request.getContextPath()%>/users/modify.jsp">用户资料</a><font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</font>
										<%
											}
										%>
									</td>
									<td class="header_tool_bar_ch">
										<a href="<%=request.getContextPath()%>/corpus/CorpusSearch.jsp" target="_blank">语料资源检索</a>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
				</td>
			</tr>
		</tbody>
	</table>
	<table cellSpacing="0" cellPadding="0" width="968" border="0" align="center">
		<tbody>
			<tr bgColor="#a7c0c5">
				<td align="right">
					<table cellSpacing="0" cellPadding="0" width="650" align="right" border="0">
						<tbody>
							<tr>
								<td class="tool_bar" align="right">
									<a href="<%=request.getContextPath()%>/index.jsp "><font class="text_position_3">首頁</font></a>&nbsp;|&nbsp;
									<a href="<%=request.getContextPath()%>/download/index.jsp"><font class="text_position_3">资源下载</font></a>&nbsp;|&nbsp;
									<a href="<%=request.getContextPath()%>/liuyanban/index.jsp"target=_blank><font class="text_position_3">访客留言</font></a>&nbsp;|&nbsp;
									<a href="<%=request.getContextPath()%>/contact.jsp"><font class="text_position_3">联系我们</font></a>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="image_event_table_1" height="200" cellSpacing="0" cellPadding="0" width="968" border="0" align="center">
		<tbody>
			<tr>
				<td width="100%" rowSpan="3">
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="100%" height="100%">
						<param name="movie" value="<%=request.getContextPath()%>/images/newheader.swf">
						<param name="quality" value="high">
						<embed src="<%=request.getContextPath()%>/images/newheader.swf" width="100%" height="100%"></embed>
					</object>
				</td>
			</tr>
		</tbody>
	</table>