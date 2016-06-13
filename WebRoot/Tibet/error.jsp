<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<base href="<%=basePath%>">
		<title>ERROR!</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page.">
	</head>
	
	<script language="javascript">
		function back()
		{ 
    		history.go(-1);  
		} 
		
		function display() 
		{
		  	document.write("<div id='div1' style='font-size:12px; position:absolute; display:'block'; text-align:center; overflow:visible'>");	
			  	document.write("<div style='position:absolute; top:expression((body.clientHeight-300)/2); left:expression((body.clientWidth-200)/2); width:200px; height:180px; background-color:#dbdbdb; border:1px solid #cccccc'>");
				  	document.write("<table width='200' height='20' bgcolor='#ff0000'>");
					  	document.write("<tr align='center'>");
					  		document.write("<td align='left'>HINT:</td>");
					  	document.write("</tr>");
				  	document.write("</table>");
				  	document.write("<span><br>It is error in operation...<br><a href='#' onclick='back();'>[BACK]</a></span>");
			  	document.write("</div>");
		  	document.write("</div>");
		} 
	</script>
	
	<body onload="display();">
	</body>	
</html>
