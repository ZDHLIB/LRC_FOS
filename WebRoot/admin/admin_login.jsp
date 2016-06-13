<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
	request.getSession().removeAttribute("admin");
%>

<html>
	<head>
	<script language="javascript">
		function FormCheck()
		{
		 	var pass11=document.getElementsByName("adminName");
			var pass21=document.getElementsByName("password");
			var pass31=document.getElementsByName("authcode");
			var pass1=pass11[0].value;
			var pass2=pass21[0].value;
			var pass3=pass31[0].value;
			 
			if(pass1=='')
			{
				alert("请输入用户名！"); 
				return(false);
			} 
		    if(pass2=='')
		    {
		    	alert("请输入密码！"); 
		    	return(false);
		    } 
		    if(pass3=='')
		    {
		    	alert("请输入验证码！"); 
		    	return(false);
		    } 
		    return(true);
		}
	</script>
	<style type="text/css">
		body 
		{
			background-color:#002779
		}
		.input 
		{
			border:#000000 1px solid;
			font-family:"宋体";
			background-color:#ffffff
		}
	</style>
	</head>
	<body>
		<html:form action="/AdminAction.do?method=login" method="post" onsubmit="return(FormCheck());">
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" style="padding-bottom:100px">
						<table width="460" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<img src="images/login_1.jpg" width="190" height="23">
								</td>
							</tr>
						</table>
						<table width="460" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<img src="images/login_2.jpg" width="460" height="142">
								</td>
							</tr>
						</table>
						<table width="460" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td bgColor="#eeeeee" height="6"/>
							</tr>
						</table>
						<table width="460" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
							<tr>
								<td align="center" style="padding-top:15px">
									<table width="300" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="35%" height="30" align="right">
												<strong>用户名：</strong>
											</td>
											<td>
												<html:text property="adminName"  />
											</td>
										</tr>
										<tr>
											<td height="30" align="right">
												<strong>密 码：</strong>
											</td>
											<td>
												<html:password property="password"/>
											</td>
										</tr>
										<tr>
											<td width="35%" height="30" align="right">
												<strong>验证码：</strong>
											</td>
											<td>
												<html:text property="authcode"/><img src="AuthCode.do">
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center" height="40">
												<input type="submit" name="Submit" value="提  交" class="input">
												&nbsp;
												<input type="reset" name="Submit" value="重  置" class="input">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table cellSpacing="0" cellPadding="0" width="460" bgColor="#ffffff" border="0">
							<tr>
								<td>
									<img height="10" src="images/login_3.gif" width="10">
								</td>
								<td align="right">
									<img height="10" src="images/login_4.gif" width="10">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>