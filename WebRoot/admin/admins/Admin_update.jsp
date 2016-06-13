 <%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" import="net.jtaq.utils.AdminDetails"%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ include file="/admin/commons/pages/include.jsp" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT language="javascript">
     function check()
     {
     var pass11=document.getElementsByName("newPassword");
	var pass21=document.getElementsByName("password2");
	var pass1=pass11[0].value;
	var pass2=pass21[0].value;
	if(getLenth(pass1)<6){
	 alert("新密码长度最少为6位！");
	 return false;
	}
	if(pass1!=pass2){
	alert("新密码与确认值不一致！");
	return false;
	}
	else{
	return true;
	}
     }
      function getLenth(str){
    var byteLen=0,len=str.length;
    if(str){
        for(var i=0; i<len; i++){
            if(str.charCodeAt(i)>255){
                byteLen += 2;
            }
            else{
                byteLen++;
            }
        }
        return byteLen;
    }
    else{
        return 0;
    }
}
  </SCRIPT>
 <%
  
AdminDetails ad = (AdminDetails)request.getSession().getAttribute("admin");
 String  adminName =  ad.getAdminName();
 
 
 %>  
<html>
 <META http-equiv=Content-Type content="text/html; charset=utf-8">
<head>
<title></title>
</head>
<body>
    
      <html:form action="/AdminAction.do?method=changePassword" method="post" onsubmit="return check()">
	   <br>用户名:&nbsp;&nbsp;<%=adminName %>
	 
	   <br>
	   原密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:password property="password"  />
	   <br>
	  新密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	  <html:password   property="newPassword"  /><br>  
	 确认新密码：<input name="password2" type="password" onblur="check()" />
	  
	 
	   <br>
	  	  <html:submit value="确定" ></html:submit>
	 <html:reset value="重置" ></html:reset>
	  </html:form>
	</body>
	</html>