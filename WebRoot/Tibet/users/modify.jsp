<%@ page language="java" import="java.util.*,net.lrc.model.User" pageEncoding="utf-8"%>
<jsp:useBean id="departmentBean" class="net.lrc.javabean.DepartmentBean"></jsp:useBean>
<jsp:directive.page import="net.lrc.model.Department"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<center>
<head>
 <%@ include file="/Tibet/header.jsp"%>
<link href="style01.css" rel="stylesheet" type="text/css"/>
 <SCRIPT language=JavaScript>
 	function yhse(){
		 var url = "modifypwd.jsp";
         var mywin = window.open(url, "修改密码", "top=220,left=280,scrollbars=no,location=no,height=360,width=400,toolbar=no");
	}
	
</SCRIPT>
 
<title>用户资料更改</title>
<%
 
User user=null;
if (session.getAttribute("user")!=null){ user=(User)session.getAttribute("user");%>
   
<body topmargin="0"> 
<form action="user.do?method=update" method="post" name="modiyinfoform">
  <table width="620" border="0" cellspacing="0" cellpadding="0">
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
*         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="yhse()">修改密码</a>              </td>

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
                        </select></td>
                      </tr>
                      <tr>
                        <td height="25" align="left">生日</td>
                        <td height="25" align="left"><input  onclick="setday(this)" size=25 name=birthdate value="<%=user.getBirthdate() %>"/></td>
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
                       <tr>
                        <td height="25" align="left" valign="top">所属部门</td>
                        <td height="25" align="left"><select name=departmentId disabled>
                         <option value="-1">请选择</option>
	   <%
				List departments=departmentBean.list();
         	if (departments!= null) {
         		Iterator i=departments.iterator();
         	while(i.hasNext()){
         	Department department=(Department)i.next();
			%>
			<option value="<%=department.getDepartmentid() %>" <% if(user.getDepartmentId()==department.getDepartmentid()){%>selected<%} %> ><%=department.getDepartmentname()%></option>
			<%}} %>
                        </select> </td>
                      </tr>
                      <tr>
                        <td height="35" colspan="2"><div align="center">
                            <table width="40%" border="0" align="center" cellpadding="0" cellspacing="0">
                              <tr>
                                <td><div align="center">
                                    <input 
      align=absMiddle alt=确认 cache name="B13" 
      src="<%=request.getContextPath()%>/images/pics/ok.gif" type=image tppabs="" width="45" height="20" />
                                </div>  </td>
                               
                              </tr>
							 
                            </table>
                             
                        </div></td>
                      </tr>
                  </table></td>
                </tr>
            </table></td>
          </tr>
      </table></td>
      <td background=images/l_right.gif>&nbsp;</td>
    </tr>
  </table>
</form>
<%} %>
</center>
</body>
</html>
