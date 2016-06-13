<%@ page language="java" import="net.lrc.model.User" pageEncoding="utf-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		
		<title>Index:LRC</title>
		
		<link href="<%=request.getContextPath()%>/Tibet/lingdot_files/style.css" type="text/css" rel="stylesheet">
		
		<script language="javascript">
			var nowTime;
			var year;
			var month;
			var date;
			var hour;
			var minute;
			var second;
			var timeString;
			
			function MyDate()
			{
				timeString="";
				
				nowTime=new Date();
				year=nowTime.getYear();
				month=nowTime.getMonth();
				date=nowTime.getDate();
				hour=nowTime.getHours();
				minute=nowTime.getMinutes();
				second=nowTime.getSeconds();
				
				month=month+1;
				date=(date<10)?("0"+date):date;
				hour=(hour<10)?("0"+hour):hour;
				minute=(minute<10)?("0"+minute):minute;
				second=(second<10)?("0"+second):second;
				
				timeString=year+"-"+month+"-"+date+"  "+hour+":"+minute+":"+second;
				document.getElementById("dateName").innerHTML=timeString;
			}
			
	    	setInterval("MyDate()",1000);
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
					<font color="#cccc33">Hello，<%=u.getLoginName()%>！</font>
					<%
						} 
						else 
						{
					%>
					<div id="login" style="position:absolute;display:none">
						<form action="user.do?method=login" method="post">
							<table>
								<tr>
									<td class="header_tool_bar_ch">
										Username:<input type="text" name="username"/>
									</td>
									<td class="header_tool_bar_ch">
										Password:<input type="password" name="password"/>
									</td>
									<td class="header_tool_bar_ch">
										<input type="submit" value="submit"/>
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
						<table cellSpacing="0" cellPadding="0" border="0">
							<tbody>
								<tr bgColor="#cccc33"  align="center" vAlign="middle">
									<td class="header_tool_bar_ch">
										<a href="<%=request.getContextPath() %>/index.jsp">&nbsp;&nbsp;རྒྱ་ཡིག་པར་གཞི་</a><font color="#ffffff">&nbsp;|&nbsp;</font>
										<a href="#">བོད་ཡིག་པར་གཞི་</a><font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
									</td>
									<td class="header_tool_bar_ch">
										<%
											if(u==null) 
											{
										%>
										<a href="#" onClick="showDIV()">བེད་སྤྱོད་མི་སྣ་འཁོད་པ་</a><font color="#ffffff">&nbsp;&nbsp;|&nbsp;&nbsp;</font>
									</td>
									<td class="header_tool_bar_ch">
										<a href="<%=request.getContextPath()%>/Tibet/users/reg.jsp">བེད་སྤྱོད་མི་སྣའི་ཐོ་འགོད་པ་</a><font color="#ffffff">&nbsp;&nbsp;|&nbsp;&nbsp;</font>
										<%
											} 
											else 
											{
										%>
										<a href="<%=request.getContextPath()%>/users/user.do?method=logout">Logout</a><font color="#ffffff">&nbsp;&nbsp;|&nbsp;&nbsp;</font>
									</td>
									<td class="header_tool_bar_ch">
										<a href="<%=request.getContextPath()%>/Tibet/users/modify.jsp">UserData</a><font color="#ffffff">&nbsp;&nbsp;|&nbsp;&nbsp;</font>
										<%
											}
										%>
									</td>
									<td class="header_tool_bar_ch">
										<a href="<%=request.getContextPath()%>/corpus/CorpusSearch.jsp" target="_blank">སྐད་ཡིག་རྒྱུ་ཆའི་ཐོན་ཁུངས་ བཤེར་འཚོལ་</a>&nbsp;&nbsp;
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
					<table cellSpacing="0" cellPadding="0" width="750" align="right" border="0">
						<tbody>
							<tr>
								<td class="tool_bar" align="right">
									<a href="<%=request.getContextPath()%>/Tibet/index.jsp "><font class="text_position_3">མདུན་ཤོག་</font></a>&nbsp;<font color="#ffffff">|</font>&nbsp; 
									<a href="<%=request.getContextPath()%>/Tibet/public/index.jsp"><font class="text_position_3">ལྟེ་བའི་ངོ་སྤྲོད་</font></a>&nbsp;<font color="#ffffff">|</font>&nbsp;
									<a href="<%=request.getContextPath()%>/Tibet/news/index.jsp"><font class="text_position_3">གསར་འགྱུར་གྱི་ལྟེ་བ་</font></a>&nbsp;<font color="#ffffff">|</font>&nbsp;
									<a href="<%=request.getContextPath()%>/Tibet/research/index.jsp"><font class="text_position_3">ཚན་རིག་ཞིབ་འཇུག་གི་གནས་ཚུལ་</font></a>&nbsp;<font color="#ffffff">|</font>&nbsp;
									<a href="<%=request.getContextPath()%>/Tibet/download/index.jsp"><font class="text_position_3">ཐོན་ཁུངས་འོག་འདྲེན་་</font></a>&nbsp;<font color="#ffffff">|</font>&nbsp;
									<a href="<%=request.getContextPath()%>/Tibet/liuyanban/index.jsp" target="_blank"><font class="text_position_3">བསའེབ</font></a>&nbsp;<font color="#ffffff">|</font>&nbsp;
									<a href="#"><font class="text_position_3">བསའེབ</font></a>
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
					</object>
				</td>
			</tr>
		</tbody>
	</table>