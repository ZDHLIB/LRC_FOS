<%@ page language="java" import="net.lrc.model.User" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style01.css" rel="stylesheet" type="text/css" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>用户密码修改</title>
 <SCRIPT language="javascript">
     function check()
     {
     var pass1=document.getElementById("user_passnew").value;
	var pass2=document.getElementById("user_passre").value;
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
User user=null;
if (session.getAttribute("user")==null){
    response.sendRedirect("index.asp");
	}else{
     user = (User)session.getAttribute("user"); 
	}
%>
<body topmargin="0">
<form method="post" action="user.do?method=modifypwd" onsubmit="return check();">
  <br>
  <table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
    <td width="100%">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="100%">
			 
			</td>
        </tr>
        <tr>
            <td width="100%" bgcolor="#fafde8" align="center" height="18">用户密码修改<input  type="hidden" name="id" value="<%=user.getId()%>"></td>
        </tr>
        <tr>
            <td width="100%" bgcolor="#fafafa"> <table width="100%" height="34" border="0" cellpadding="0" cellspacing="0">
                <tr> 
                  <td width="398" height="120"> 
                    <div align="center">
                      <center>
                    <table width="90%" border="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0">

                      <tr> 
                        <td width="40%" height="25"><div align="right">原始密码：</div></td>
                        <td width="60%" height="25">　<input style="FONT-SIZE: 12px" type="password" name="user_passold" size="25" class=input></td>
                      </tr>
                      <tr> 
                        <td width="21%" height="25"><div align="right">新密码：</div></td>
                        <td width="79%" height="25">　<input style="FONT-SIZE: 12px" type="password" id="user_passnew" name="user_passnew" size="25" class=input></td>
                      </tr>
                      <tr> 
                        <td width="21%" height="25"><div align="right">新密码确认：</div></td>
                        <td width="79%" height="25">　<input style="FONT-SIZE: 12px" type="password" id="user_passre" name="user_passre" size="25" class=input ></td>
                      </tr>
                      <tr> 
                       
                        <td height="35" colspan="2"> <table width="40%" border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr> 
                              <td><div align="center"> 
  <input   align=absMiddle alt=确认 cache name="B1"   src="/lrc/images/pics/ok.gif" type=image tppabs="" width="45" height="20" />
                                </div>
                                </td>
                            </tr>
                          </table></td>
                      </tr>
                    </table>
                      </center>
                  </div>
                    <table width="100%" height="25" border="0" align="right" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td height="35" align="right" valign="left">
                          <input onclick=javascript:window.close() type=button value=关闭 name=button /> 
                          </td>
                      </tr>
                    </table></td></tr>
              </table>
              
            </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</body>
</html>
