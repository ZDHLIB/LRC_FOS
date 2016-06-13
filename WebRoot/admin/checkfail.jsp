<%@ page language="java"  import="net.jtaq.utils.*,java.util.*"   %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page session="true"   %>
  <script language=javascript>
 
function back()
{ 
    history.go(-1);  //后退1页
} 
//实现层和表格的动态创建
function display() 
{
  
  document.write("<div  id='div1' style='font-size:12px;position:absolute;display:'block';text-align:center;overflow:visible'>");
  document.write("<div style='position:absolute;top:expression((body.clientHeight-300)/2);left:expression((body.clientWidth-200)/2);width:200px;height:180px;background-color:#dbdbdb;border:1px solid #cccccc;'>");
  document.write("<table width=200 height=20 bgcolor=#ffff00");
  document.write("<tr align=center>");
  document.write("<td align=left>提示:</td>");
  document.write("</tr>");
  document.write("</table>");
  document.write("<span ><br>您的权限不足，无法完成操作，请与管理员联系...<br><a href='#' onclick='back();' >[返回]</a></span>");
  document.write("</div>");
  document.write("</div>");
}
 
</script>
 
<body onload="display();">
  </body>
