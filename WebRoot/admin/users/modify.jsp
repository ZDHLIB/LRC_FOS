<%@ page language="java" import="net.lrc.model.User,java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="departmentBean" class="net.lrc.javabean.DepartmentBean"></jsp:useBean>
<jsp:directive.page import="net.lrc.model.Department"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<SCRIPT language=JAVASCRIPT src="<%=request.getContextPath()%>/commons/js/myCalendar.js"></SCRIPT>
 <link href="style01.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="admin.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户资料更改</title>
<%
 
User user=null;
if (request.getAttribute("user")==null){ %>

<center>
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
<script language="javascript" type="text/javascript">
	function showDIV() {
		var myDIV=document.getElementById("table2");
		var myDIV2=document.getElementById("table1");
		myDIV.style.display="block";
		myDIV2.style.display="none";
	}  
	function showDIV1() {
		var myDIV=document.getElementById("table1");
		var myDIV2=document.getElementById("table2");
		myDIV.style.display="block";
		myDIV2.style.display="none";
	}

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
   if(22==xmlHttp.responseText){
    alert("用户名已经被注册！");
    obj.parentNode.firstChildf.focus;
    }
  }
</script>

<link href="style01.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<center>   

 <form action="user.do?method=register" method="post" name="form1" id="form1" onsubmit="return this.validateSubmit()"> 
	 <table width="720" border="0" cellspacing="0" cellpadding="0"><tr><td> 
     <table width="700" border="0" cellspacing="0" cellpadding="0" >
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
                            <table width="90%" border="1" align="center" cellpadding="0" cellspacing="0" >
                              <tr align="left">
                                <td height="25" colspan="2"><span style="font-weight: bold; color: #000000">必填项</span></td>
                                
                              </tr>
                              <tr> 
                                <td width="25%" height="25" align="left">用户名</td>
                                <td width="75%" align="left">
                                    <input style="FONT-SIZE: 12px;" type="text" name="username" size="25" class=input validation="Empty"/><font color="#ff000">*</font><input value="检测用户名" type="button" onclick="checkUsername(this)"/>
                                </td>
                              </tr>
                              <tr> 
                                <td height="25" align="left">密码</td>
                                <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="password" name="password" size="25" class=input validation="Empty"/><font color="#ff000">*</font></td>
                              </tr>
                              <tr> 
                                <td height="25" align="left">密码确认</td>
                                <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="password" name="password2" size="25" class=input validation="Empty"/><font color="#ff000">*</font></td>
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
                                <td height="25" align="left"><INPUT size=25 name=birthdate>&nbsp;格式为0000-00-00</td>
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


<%}else{
     user = (User)request.getAttribute("user");}%>
<body topmargin="0"><center>

<form action="user.do?method=update" method="post" name="modiyinfoform">

  <table  width="620" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td background=images/l_left.gif>&nbsp;</td>
      <td>

      <table width="600" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse">
          <tr>
            <td width="100%" height="25" bgcolor="#fafde8"><div align="center"><b>用户资料修改</b></div></td>
          </tr>
          <tr>
            <td height="2" bgcolor="#fafafa"><table width="100%" border="0" cellspacing="1" cellpadding="0" height="100%">
                <tr>
                  <td><table width="90%" border="1" align="center" cellpadding="0" cellspacing="0" style="margin-bottom: 6">
                      
                      <tr>
                        <td width="25%" height="25" align="left">用户名</td>
                        <td width="75%" align="left"><%=user.getLoginName()%>
                        <input type="hidden" name="id" value="<%=user.getId()%>" />
*                        </td>
                      </tr>
                      <tr>
                        <td height="25" align="left">Email</td>
                        <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="text" name="email" size="25" value="<%=user.getEmail()%>" class=input />
                        *</td>
                      </tr>
                      <tr>
                        <td height="25" align="left">学历</td>
                        <td height="25" align="left"><select name=experience>
                            <option value="" selected>- 请选择 -</option>
                            <option value=1 <%if("1".equalsIgnoreCase(user.getExperience())) {%>selected<%} %>>本科</option>
                            <option value=2 <%if("2".equalsIgnoreCase(user.getExperience())) {%>selected<%} %>>硕士</option>
                            <option value=3 <%if("3".equalsIgnoreCase(user.getExperience())) {%>selected<%} %>>博士</option>
                            <option value=4 <%if("4".equalsIgnoreCase(user.getExperience())) {%>selected<%} %>>博士后</option>
                            <option value=5 <%if("5".equalsIgnoreCase(user.getExperience())) {%>selected<%} %>>其它</option>
                          </select>
  &nbsp;</td>
                      </tr>
                      <tr>
                        <td height="25" align="left">民族</td>
                        <td height="25" align="left"><select name="race" style="width:150px;">
                            <option selected="selected" value="">-请选择-</option>
                            <option value="1" <%if("1".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>汉族 </option>
                            <option value="2" <%if("2".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>蒙古族 </option>
                            <option value="3" <%if("3".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>彝族 </option>
                            <option value="4" <%if("4".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>侗族 </option>
                            <option value="5" <%if("5".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>哈萨克族 </option>
                            <option value="6" <%if("6".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>畲族 </option>
                            <option value="7" <%if("7".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>纳西族 </option>
                            <option value="8" <%if("8".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>仫佬族 </option>
                            <option value="9" <%if("9".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>仡佬族 </option>
                            <option value="10" <%if("10".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>怒族 </option>
                            <option value="11" <%if("11".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>保安族 </option>
                            <option value="12" <%if("12".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>鄂伦春族 </option>
                            <option value="13" <%if("13".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>回族 </option>
                            <option value="14" <%if("14".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>壮族 </option>
                            <option value="15" <%if("15".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>瑶族 </option>
                            <option value="16" <%if("16".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>傣族 </option>
                            <option value="17" <%if("17".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>高山族 </option>
                            <option value="18" <%if("18".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>景颇族 </option>
                            <option value="19" <%if("19".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>羌族 </option>
                            <option value="20" <%if("20".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>锡伯族 </option>
                            <option value="21" <%if("21".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>乌孜别克族 </option>
                            <option value="22" <%if("22".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>裕固族 </option>
                            <option value="23" <%if("23".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>赫哲族 </option>
                            <option value="24" <%if("24".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>藏族 </option>
                            <option value="25" <%if("25".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>布依族 </option>
                            <option value="26" <%if("26".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>白族 </option>
                            <option value="27" <%if("27".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>黎族 </option>
                            <option value="28" <%if("28".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>拉祜族 </option>
                            <option value="29" <%if("29".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>柯尔克孜族 </option>
                            <option value="30" <%if("30".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>布朗族 </option>
                            <option value="31" <%if("31".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>阿昌族 </option>
                            <option value="32" <%if("32".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>俄罗斯族 </option>
                            <option value="33" <%if("33".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>京族 </option>
                            <option value="34" <%if("34".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>门巴族 </option>
                            <option value="35" <%if("35".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>维吾尔族 </option>
                            <option value="36" <%if("36".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>朝鲜族 </option>
                            <option value="37" <%if("37".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>土家族 </option>
                            <option value="38" <%if("38".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>傈僳族 </option>
                            <option value="39" <%if("39".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>水族 </option>
                            <option value="40" <%if("40".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>土族 </option>
                            <option value="41" <%if("41".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>撒拉族 </option>
                            <option value="42" <%if("42".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>普米族 </option>
                            <option value="43" <%if("43".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>鄂温克族 </option>
                            <option value="44" <%if("44".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>塔塔尔族 </option>
                            <option value="45" <%if("45".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>珞巴族 </option>
                            <option value="46" <%if("46".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>苗族 </option>
                            <option value="47" <%if("47".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>满族 </option>
                            <option value="48" <%if("48".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>哈尼族 </option>
                            <option value="49" <%if("49".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>佤族 </option>
                            <option value="50" <%if("50".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>东乡族 </option>
                            <option value="51" <%if("51".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>达斡尔族 </option>
                            <option value="52" <%if("52".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>毛南族 </option>
                            <option value="53" <%if("53".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>塔吉克族 </option>
                            <option value="54" <%if("54".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>德昂族 </option>
                            <option value="55" <%if("55".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>独龙族 </option>
                            <option value="56" <%if("56".equalsIgnoreCase(user.getRace())) {%>selected<%} %>>基诺族 </option>
                          </select>
&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="25" align="left">真实姓名</td>
                        <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="text" name="xm" size="25" class=input value="<%=user.getName() %>"/>
                        </td>
                      </tr>
                      <tr>
                        <td height="25" align="left">性别</td>
                        <td height="25" align="left">
                        <select name="gender">
                        <option <%if("1".equalsIgnoreCase(user.getGender())) {%>checked<%} %> value="1" >男</option>
                        男 &nbsp;
                         <option value="2" <%if("2".equalsIgnoreCase(user.getGender())) {%>checked<%} %>  >女</option>
                        女 &nbsp;
                        </select>
                        </td>
                      </tr>
                      <tr>
                        <td height="25" align="left">生日</td>
                        <td height="25" align="left"><input onclick="setday(this)" size=25 name=birthdate value="<%=user.getBirthdate() %>"/></td>
                      </tr>
                      <tr>
                        <td height="25" align="left">出生地</td>
                        <td height="25" align="left"><input size=25 name=birthPlace value="<%=user.getBirthPlace() %>"/>
  &nbsp;</td>
                      </tr>
                      <tr>
                        <td height="25" align="left">年龄</td>
                        <td height="25" align="left"><input size=15 name=age value="<%=user.getAge() %>"/>
  &nbsp;</td>
                      </tr>
                      <tr>
                        <td height="25" align="left">通讯地址</td>
                        <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="text" name="address" size="40" class=input value="<%=user.getAddress() %>"/></td>
                      </tr>
                      <tr>
                        <td height="25" align="left">电话</td>
                        <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="text" name="telephotone" size="20" value="<%=user.getTelephone() %>" class=input />
                        </td>
                      </tr>
                      <tr>
                        <td height="25" align="left">手机</td>
                        <td height="25" align="left"><input size=20 name=mobilephotone value="<%=user.getMobilephone() %>"/>
  &nbsp;</td>
                      </tr>
                      <tr>
                        <td height="25" align="left">邮编</td>
                        <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="text" name="zipcode" size="20" class=input value="<%=user.getZipcode() %>"/>
                        </td>
                      </tr>
                      <tr>
                        <td height="25" align="left">所在单位</td>
                        <td height="25" align="left"><input size=40 name=department value="<%=user.getDepartment() %>"/>
                        </td>
                      </tr>
                      <tr>
                        <td height="25" align="left" valign="top">工作领域</td>
                        <td height="25" align="left"><textarea name=research rows=3 cols=60 ><%=user.getResearch() %></textarea></td>
                      </tr>
                      <tr>
                        <td height="25" align="left" valign="top">个人描述</td>
                        <td height="25" align="left"><textarea name=info rows=3 cols=60 ><%=user.getInfo() %></textarea></td>
                      </tr>
                      </tr>  
                      <tr>
				       <td height="25" align="left">可查看语料数目</td>
				       <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="text" name="view" size="20" class=input value="<%=user.getView()%>"/></td>
				     </tr>
				     <tr>
				       <td height="25" align="left">可下载语料数目</td>
				       <td height="25" align="left"><input style="FONT-SIZE: 12px;" type="text" name="down" size="20" class=input value="<%=user.getDown()%>"/></td>
				     </tr>                                    
                  </table></td>
                </tr>
            </table></td>
          </tr>
      </table></td>
      <td background=images/l_right.gif>&nbsp;</td>
    </tr>
      <tr>
         <td height="35" colspan="2"><div align="center">
             <table width="40%" border="0" align="center" cellpadding="0" cellspacing="0">
               <tr>
                 <td><div align="center">
                     <input align=absMiddle alt=确认 cache name="B13" 
					src="<%=request.getContextPath()%>/images/pics/ok.gif" type=image tppabs="" width="45" height="20" />
          </div>  
          </td>               
        </tr>
           </table>
           <input onclick="javascript:window.location='user.do?method=list'" type=button value="返回列表" name=button />
       </div></td>
     </tr>
  </table>
</form>
</center>
</body>
</html>
