<%@ page language="java"   pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>无标题页</title>
<script language=javascript>
 
function admin_index() 
{
   window.location='admin_login.jsp';
}
//实现层和表格的动态创建
function display() 
{
  
  document.write("<div  id='div1' style='font-size:12px;position:absolute;display:'block';text-align:center;overflow:visible'>");
  document.write("<div style='position:absolute;top:expression((body.clientHeight-300)/2);left:expression((body.clientWidth-200)/2);width:200px;height:180px;background-color:#dbdbdb;border:1px solid #cccccc;'>");
  document.write("<table width=200 height=20 bgcolor=#ff0000");
  document.write("<tr align=center>");
  document.write("<td align=left>提示:</td>");
  document.write("</tr>");
  document.write("</table>");
  document.write("<span ><br>登陆信息发生了错误...<br><a href='<%=request.getContextPath()%>/admin/admin_login.jsp'>[返回]</a></span>");
  document.write("</div>");
  document.write("</div>");
}
 
</script>
 
<body onload="display();">
</body>
</html>