<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.util.*,java.io.*"  %>
<%@ include file="config.jsp"%>
<%
String gg=null;gg=(String)request.getParameter("name");
String mm=null;mm=(String)request.getParameter("password");
String pass=null;pass=(String)session.getAttribute("pass");

if("y".equals(pass))
{
response.sendRedirect("index.jsp");
}else if(name.equals(gg)&&password.equals(mm))
{
session.setAttribute("pass","y");
response.sendRedirect("index.jsp");
}
else
{
%>
<html>
<head>
 
<link href='css/main.css' rel='stylesheet' type='text/css'>
<title>管理登陆</title>
</head>
<body>
<table border="0" width="100%" cellspacing="0" cellpadding="0" height="100%">
            <tr>
              <td width="100%">
        
          <div align="center">
            <center>
          <table width="300" border="0">
            <tr>
              <td height="62"><img src="pic/c_letter.gif" width="58" height="55"><span class="inp_set1"><a href="index.jsp">留言本</a> 
                  <font face="Verdana">>></font> 登陆/管理</span></td>
            </tr>
          </table>
           <form name="form" onSubmit="return check_form(this)" method="POST" action="">
          <table class=table border="0" cellspacing="1" cellpadding="0" width="300">
            <tr> 
              <td width="50%" valign="top"> 
                <table width="100%" height="200" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
                 
                    <tr> 
                      <td width="100%" nowrap class="inp_set1"> 
                        <p align="center">用户名： 
                          <input name="name" class="inp_set1" onMouseOver="style.backgroundColor='#ffffff'"  onmouseout="style.backgroundColor='#f3fede'"   size="13" maxlength="20">
                          <br>
                          <br>
                          密&nbsp;&nbsp;码： 
                          <input name="password"  type="password" class="inp_set1" onMouseOver="style.backgroundColor='#ffffff'"  onmouseout="style.backgroundColor='#f3fede'" size="13" maxlength="20">
                          <br>
                          <br>
                          <input name="cmdOk" type="submit" class="inp_set5" style="font-family: 宋体; font-size: 9pt;width:137;height:18" onMouseOver="style.backgroundColor='#ffffff'"  onmouseout="style.backgroundColor='#f3fede'" value=" 登 陆 " >
                        </p>
                      </td>
                    </tr>
                    <tr> 
                      <td width="100%" class="inp_set1"> 
                        <p align="center"> 
                      </td>
                    </tr>
                    <tr> 
                      <td width="100%" class="inp_set1"> 
                        <p align="center"><a href="#">忘了密码?</a></p>
                      </td>
                    </tr>
                
                </table>
          </table>           
               </form>    
          <br>
        </center>    
          </div>   
          <p align="center"><font size="2"><a href="index.jsp">&lt;&lt;返回&gt;&gt;</a></font></p>  
              </td>
            </tr>
          </table>
</body>
</html>
<% }%>
<script language="JavaScript" type="text/JavaScript">
function check_form(form1)
	{
		if (form.name.value=="")
		{
			alert("请输入用户名!！");
			form.name.focus();
			return false;
		}
		if (form.password.value=="")
		{
			alert("密码好像忘了填?");
			form.password.focus();
			return false;
		}
	}
</script>