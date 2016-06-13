<%@ page contentType="text/html;charset=utf-8"  import="java.util.*" language="java"  errorPage="" %>
<center>   
<%@ include file="/header.jsp" %>
<style type="text/css">
 form
 {
  behavior:url(validateForm.htc);
 }
 input
 {
  behavior:url(validate.htc);
 }
  
</style>

 
<link href="style01.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
 function checkUsername(obj){     

   var xmlHttp = false; 
try {
  xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e) {
  try {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } catch (e2) {
    xmlHttp = false;
  }
} 
if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
  xmlHttp = new XMLHttpRequest();
}    
 xmlHttp.onreadystatechange = function(){
    if(4=xmlHttp.readyState){
    if(200=xmlHttp.readyState){
       add(xmlHttp.responseXML)
    }else{
    alert("error");
    }
    }
  
  }; 
  var username = obj.parentNode.firstChild.value;   
  if ((username == null) || (username == "")) {
  	alert("请填写用户名！");
  	return;
  }
  var url = "user.do?method=checkUsername&username="+escape(username);   
  xmlHttp.open("GET", url,false);   
  xmlHttp.send(null); 
   
  if(11==xmlHttp.responseText) {
	   alert("用户名可以使用！");
  }
  if(22==xmlHttp.responseText) {
	   alert("用户名已经被注册！");
	    obj.parentNode.firstChildf.focus;
	}
  }
  
    function check() {
	    var pass1=document.getElementById("password11").value;
		var pass2=document.getElementById("password2").value;
		 
		if(pass1!=pass2){
		alert("新密码与确认值不一致!");	 
		}
		if(getLenth(pass1)<6){
		 alert("新密码长度最少为6位!");
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
</script>


 <form action="user.do?method=register" method="post" name="form1" id="form1" onsubmit="return this.validateSubmit()"> 
	 <table width="720" border="0" cellspacing="0" cellpadding="0"><tr><td background=<%=request.getContextPath()%>/images/l_left.gif>
	 <IMG src="<%=request.getContextPath()%>/images/l_left.gif"></td><td> 
     <table width="700" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse">
                <tr> 
                  <td width="100%" height="25">　</td>
                </tr>
                <tr> 
                  <td width="100%" height="25" bgcolor="#fafde8"><div align="center"><b>用户注册</b></div></td>
                </tr>
                <tr> 
                  <td height="2" bgcolor="#fafafa"> <table width="100%" border="0" cellspacing="1" cellpadding="0" height="100%">
                      <tr> 
                        <td>
                            <table width="90%" border="1" align="center" cellpadding="0" cellspacing="0" style="margin-bottom: 6">
                              <tr align="left">
                                <td height="25" colspan="2"><span style="font-weight: bold; color: #000000">必填项</span></td>
                                
                              </tr>
                              <tr> 
                                <td width="25%" height="25" align="left">用户名</td>
                                <td width="75%" align="left">
                                    <input style="FONT-SIZE: 12px;" type="text" name="username" size="25" class=input validation="Empty"  onblur="checkUsername(this)"/><font color="#ff000">*</font>   <!-- <input value="检测用户名" type="button" onMouseOut="checkUsername(this)"/> -->
                                </td>
                              </tr>
                              <tr> 
                                <td height="25" align="left">密码</td>
                                <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="password" id="password11"  name="password" size="25" class=input validation="Empty"/><font color="#ff000">*</font></td>
                              </tr>
                              <tr> 
                                <td height="25" align="left">密码确认</td>
                                <td height="25" align="left"><input onblur="check()" style="FONT-SIZE: 12px;"  type="password" id="password2"  name="password2" size="25" class=input validation="Empty"/><font color="#ff000">*</font></td>
                              </tr>
                              <tr> 
                                <td height="25" align="left">Email</td>
                                <td height="25" align="left">
                                <input style="FONT-SIZE: 12px;" type="text" name="email" size="25" class=input validation="Email"/><font color="#ff000">*</font></td>
                              </tr>
                              <tr>
                                <td height="25" align="left">学历</td>
                                <td height="25" align="left"><SELECT name=experience > <OPTION value="-1" selected>- 请选择 -</OPTION> 
                                <OPTION value=1>本科</OPTION> 
                                <OPTION value=2>硕士</OPTION>
                                <OPTION value=3>博士</OPTION>
                                <OPTION value=4>博士后</OPTION>
                                <OPTION value=5>其它</OPTION></SELECT>&nbsp;<font color="#ff000">*</font></td> 
                              </tr>
                              <tr>
                                <td height="25" align="left">民族</td>
                                <td height="25" align="left">
								  <select name="race"    style="width:150px;">
                                      <option selected="selected" value="-1">-请选择-</option>
                                      <option value="1">汉族 </option>
                                      <option value="2">蒙古族 </option>
                                      <option value="3">彝族 </option>
                                      <option value="4">侗族 </option>
                                      <option value="5">哈萨克族 </option>
                                      <option value="6">畲族 </option>
                                      <option value="7">纳西族 </option>
                                      <option value="8">仫佬族 </option>
                                      <option value="9">仡佬族 </option>
                                      <option value="10">怒族 </option>
                                      <option value="11">保安族 </option>
                                      <option value="12">鄂伦春族 </option>
                                      <option value="13">回族 </option>
                                      <option value="14">壮族 </option>
                                      <option value="15">瑶族 </option>
                                      <option value="16">傣族 </option>
                                      <option value="17">高山族 </option>
                                      <option value="18">景颇族 </option>
                                      <option value="19">羌族 </option>
                                      <option value="20">锡伯族 </option>
                                      <option value="21">乌孜别克族 </option>
                                      <option value="22">裕固族 </option>
                                      <option value="23">赫哲族 </option>
                                      <option value="24">藏族 </option>
                                      <option value="25">布依族 </option>
                                      <option value="26">白族 </option>
                                      <option value="27">黎族 </option>
                                      <option value="28">拉祜族 </option>
                                      <option value="29">柯尔克孜族 </option>
                                      <option value="30">布朗族 </option>
                                      <option value="31">阿昌族 </option>
                                      <option value="32">俄罗斯族 </option>
                                      <option value="33">京族 </option>
                                      <option value="34">门巴族 </option>
                                      <option value="35">维吾尔族 </option>
                                      <option value="36">朝鲜族 </option>
                                      <option value="37">土家族 </option>
                                      <option value="38">傈僳族 </option>
                                      <option value="39">水族 </option>
                                      <option value="40">土族 </option>
                                      <option value="41">撒拉族 </option>
                                      <option value="42">普米族 </option>
                                      <option value="43">鄂温克族 </option>
                                      <option value="44">塔塔尔族 </option>
                                      <option value="45">珞巴族 </option>
                                      <option value="46">苗族 </option>
                                      <option value="47">满族 </option>
                                      <option value="48">哈尼族 </option>
                                      <option value="49">佤族 </option>
                                      <option value="50">东乡族 </option>
                                      <option value="51">达斡尔族 </option>
                                      <option value="52">毛南族 </option>
                                      <option value="53">塔吉克族 </option>
                                      <option value="54">德昂族 </option>
                                      <option value="55">独龙族 </option>
                                      <option value="56">基诺族 </option>
                                  </select>
&nbsp;<font color="#ff000">*</font></td>
                              </tr>
                              <tr align="left">
                                <td height="25" colspan="2"><span style="color: #000000; font-weight: bold">更多选项</span></td>
                              
                              </tr>
							 <tr> 
                                <td height="25" align="left">真实姓名</td>
                                <td height="25" align="left">
                                    <input style="FONT-SIZE: 12px;" type="text" name="xm" size="25" class=input>
                               </td>
                              </tr>
                              <tr>
                                <td height="25" align="left">性别</td>
                                <td height="25" align="left"><INPUT class=radio type=radio value=1 name="gender" checked > 男 &nbsp; <INPUT class=radio type=radio value=2 name="gender"> 女 &nbsp;   </td>
                              </tr>
                              <tr>
                                <td height="25" align="left">生日</td>
                                <td height="25" align="left"><INPUT  onclick="setday(this)" size=25 name="birthdate">&nbsp;格式为0000-00-00</td>
                              </tr>
                              <tr>
                                <td height="25" align="left">出生地</td>
                                <td height="25" align="left"><INPUT size=25 name=birthPlace>&nbsp;</td>
                              </tr>
                              <tr>
                                <td height="25" align="left">年龄</td>
                                <td height="25" align="left"><INPUT size=15 name=age>&nbsp;</td>
                              </tr>
                              <tr> 
                                <td height="25" align="left">通讯地址</td>
                                <td height="25" align="left">
                                <input style="FONT-SIZE: 12px;" type="text" name="address" size="40" class=input></td>
                              </tr>
                              <tr> 
                                <td height="25" align="left">电话</td>
                                <td height="25" align="left">
                                    <input style="FONT-SIZE: 12px;" type="text" name="telephotone" size="20" class=input>
                                </td>
                              </tr>
                              <tr>
                                <td height="25" align="left">手机</td>
                                <td height="25" align="left"><INPUT size=20 name=mobilephotone>&nbsp;</td>
                              </tr>
                              <tr> 
                                <td height="25" align="left">邮编</td>
                                <td height="25" align="left">
                                    <input style="FONT-SIZE: 12px;" type="text" name="zipcode" size="20" class=input>
                                  
                                </td>
                              </tr>

							  <tr> 
                                <td height="25" align="left">所在单位</td>
                                <td height="25" align="left">
                                   <INPUT size=40 name=department>
                                </td>
                              </tr>
							  <tr> 
                                <td height="25" align="left" valign="top">工作领域</td>
                                <td height="25" align="left">
                               <TEXTAREA name=research rows=5 cols=40></TEXTAREA></td>
                              </tr>
							  <tr> 
                                <td height="25" align="left" valign="top">个人描述</td>
                                <td height="25" align="left">
                                <TEXTAREA name=info rows=5 cols=40></TEXTAREA></td>
                              </tr>
                              <tr> 
                                <td height="35" colspan="2"><div align="center"> 
                                    <table width="40%" border="0" align="center" cellpadding="0" cellspacing="0">
                                      <tr> 
                                        <td><div align="center"> 
                                            <input 
      align=absMiddle alt=确认 cache name="B13" 
      src="<%=request.getContextPath()%>/images/pics/ok.gif" type=image tppabs="" width="45" height="20">
                                          </div></td>
                                        <td><div align="center"> 
                                            <input type="reset" value="清除" name="B22" class=input>
                                          </div></td>
                                      </tr>
                                    </table>
                                  </div></td>
                              </tr>
                          </table>
                        
                        </td>
                      </tr>
                    </table></td>
                </tr>
              </table>
			  </td><td background=<%=request.getContextPath()%>/images/l_right.gif><IMG src="<%=request.getContextPath()%>/images/l_right.gif"></td></tr></table>
		</form>
		</center>
   <%@ include file="/footer.jsp"%>