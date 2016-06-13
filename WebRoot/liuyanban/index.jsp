 
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.util.*,java.io.*"  %>
<%@ include file="conn.jsp"%>
<%@ include file="config.jsp"%>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
		int i=0,x=0;//计数器
		int showrow=4;//每页显示条数
		int pages=0;//当前页数
		int start; //本页开始条数
		String sql="select * from "+tab+" order by id desc"; 
		java.sql.ResultSet rs = st.executeQuery(sql);
		String strp=(String)request.getParameter("pages");
		String pass=null;pass=(String)session.getAttribute("pass");

		int sig=1,alt;
		if(strp==null){sig=1;}else{
		for(int i1=0;i1<strp.length();i1++){
		alt=strp.charAt(i1);
		if(alt<48||alt>57){
		sig=1;break;
		}else{sig=2;}
		}
		}
		if(sig==1){pages=1;}else{pages=Integer.parseInt(strp);}
		
		rs.last();
		int count=rs.getRow();
		int laspc;
		int pa=count/showrow;
		if(count%showrow>0){pa=pa+1;laspc=count%showrow;}else{laspc=showrow;}
		if(pages<0)pages=1;
		if(pages>pa)pages=pa;
		start=(pages-1)*showrow+1;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href='css/main.css' rel='stylesheet' type='text/css'>
<title><%=sitename%></title>
</head>

<body topmargin="0">
   
    <table align="center" width="675" height="550" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="675">
		
		<form name="form" method="post" onSubmit="return check_form(this)" action="save.jsp">
<div align="center"> 
      <table border="0" cellpadding="0" cellspacing="0">
        <tbody> 
        <tr> 
          <td colspan="3"><img src="img\frame_tct.gif" width="500" height="140" border="0" usemap="#Map"></td>
        </tr>
        <tr> 
          <td width="12" background="img/frame_tcl.gif"></td>
          <td bgcolor="#ffffff" width="476" align="center"><table width="421" border="0" cellpadding="0" cellspacing="0">
              <tbody> 
              <tr> 
                <td align="center" width="164" valign="middle" height="30"><div align="right"><img src="img\Fi_fu1.gif" width="12" height="12" border="0"></div></td>
                <td width="40" valign="middle"><a href="../index.jsp" target="_parent" class="STYLE1">SiteHome</a></td>
                <td align="center" width="21" valign="middle"><img src="img\Fi_fu1.gif" width="12" height="12" border="0"></td>
                <td width="40" valign="middle"> 
                  <a href="#"></a>                <a href="index.jsp" target="_self" class="STYLE1">Home</a></td>
                 
                <td align="center" width="15" valign="middle"><img src="img\Fi_fu1.gif" width="12" height="12" border="0"></td>
                <td width="40" valign="middle"><a href="login.jsp">Admin</a></td>
                <td align="center" width="44" valign="middle"><% if("y".equals(pass)){ %><a href="loginout.jsp">退出</a><%}%></td>
              </tr>
              </tbody> 
            </table>
            <table border="0" cellpadding="0" cellspacing="0">
              <tbody> 
              <tr> 
                <td valign="middle"><img src="face/m01.gif" name="tus" alt="偶的个人形象" align="absmiddle"></td>
                <td width="35"></td>
                <td valign="middle"> 
                  <table width="288" border="0" cellpadding="0" cellspacing="0">
                    <tbody> 
                    <tr><script>function showimage(){document.images.tus.src="face/"+document.form.p1.options[document.form.p1.selectedIndex].value+""+document.form.p2.options[document.form.p2.selectedIndex].value+".gif";}</script> 
                      <td style="color:#999900;">主题:</td>
                      <td width="6"></td>
                      <td>
                        <input name=title type=text class="inp_set1" id="title" onFocus="this.style.backgroundColor='#fffdf7'" onBlur="this.style.backgroundColor='#FFFFFF'" value="路过" size="30" maxlength="12">                      </td>
                    </tr>
                    <tr> 
                      <td style="color:#999900;">昵称:</td>
                      <td width="6"></td>
                      <td>
                        <input name=name type=text class="inp_set1" id="name" onFocus="this.style.backgroundColor='#fffdf7'" onBlur="this.style.backgroundColor='#FFFFFF'" value="" size="30" maxlength="10">                      </td>
                    </tr>
                    <tr> 
                      <td style="color:#999900;">QQ:</td>
                      <td width="6"></td>
                      <td>
                        <input  name=QQ type=text class="inp_set1" id="QQ" onFocus="this.style.backgroundColor='#fffdf7'" onBlur="this.style.backgroundColor='#FFFFFF'" size="30" maxlength="14">                      </td>
                    </tr>
                    <tr> 
                      <td style="color:#999900;">信箱:</td>
                      <td width="6"></td>
                      <td><input  name=email type=text class="inp_set1" id="email" onFocus="this.style.backgroundColor='#fffdf7'" onBlur="this.style.backgroundColor='#FFFFFF'" size="30" maxlength="30"></td>
                    </tr>
                    <tr>
                      <td style="color:#999900;">主页:</td>
                      <td></td>
                      <td><input  name=web type=text class="inp_set1" id="web" onFocus="this.style.backgroundColor='#fffdf7'" onBlur="this.style.backgroundColor='#FFFFFF'" value="http://" size="30" maxlength="40"></td>
                    </tr>
                    <tr>
                      <td style="color:#999900;">验证码:</td>
                      <td></td>
                      <td><input  name=rand type=text class="inp_set1" id="rand" onFocus="this.style.backgroundColor='#fffdf7'" onBlur="this.style.backgroundColor='#FFFFFF'" size="6" maxlength="4">
                        <img src="image.jsp" width="60" height="20"></td>
                    </tr>
                    </tbody> 
                  </table>
                </td>
              </tr>
              <tr> 
                <td colspan="3" align="center" height="30" valign="middle"> 
                  <table border="0" cellpadding="0" cellspacing="0">
                    <tbody> 
                    <tr> 
                      <td style="color:#999900;">性别</td>
                      <td width="6"></td>
                      <td><select name="p1" size="1" onChange="showimage()">
                        <option value="m">男生</option>
                        <option value="w">女生</option>
                      </select></td>
                      <td width="9"></td>
                      <td style="color:#999900;">头像</td>
                      <td width="6"> 
                        <select name="p2" size=1 onChange="showimage()">
                          <option value="01">01</option>
                          <option value="02">02</option>
                          <option value="03">03</option>
                          <option value="04">04</option>
                          <option value="05">05</option>
                          <option value="06">06</option>
                          <option value="07">07</option>
                          <option value="08">08</option>
                        </select>                      </td>
                      <td>&nbsp;</td>
                    </tr>
                    </tbody> 
                  </table>
                  
                </td>
              </tr>
              </tbody> 
            </table>
            <textarea name="text" cols="55" rows=8 wrap="soft" class="inp_set1" id="text" onFocus="this.style.backgroundColor='#fffdf7'" onBlur="this.style.backgroundColor='#FFFFFF'"></textarea>
            <table cellpadding="0" cellspacing="0">
              <tbody> 
              <tr> 
                <td valign="middle"> 
                  <input type="submit" value="我写好咯" class="inp_set1" onFocus="this.style.backgroundColor='#fffdf7'" onBlur="this.style.backgroundColor='#FFFFFF'" name="submit">
                </td>
                <td width="5"></td>
                <td valign="middle"> 
                  <input type="reset" value="重写一回" class="inp_set1" onFocus="this.style.backgroundColor='#fffdf7'" onBlur="this.style.backgroundColor='#FFFFFF'" name="reset">
                </td>
              </tr>
              </tbody> 
            </table>
            <table border="0" cellpadding="0" cellspacing="0">
              <tbody> 
              <tr> 
                <td valign="bottom" align="left"><img src="img\Fi_har.gif" width="28" height="20" border="0" align="middle"><img src="img\Fi_jo.gif" width="37" height="26" border="0" align="middle" hspace="3"></td>
                <td width="300" height="29" align="center" valign="bottom"> 
                  <table border="0" cellpadding="0" cellspacing="0" width="197">
                    
                  </table>
                </td>
                <td valign="bottom" align="right"><img src="img\Fi_hasa.gif" width="24" height="23" border="0" hspace="4" align="bottom"><img src="img\Fi_sc.gif" width="24" height="22" border="0" vspace="3" align="top"></td>
              </tr>
              </tbody> 
            </table>
          </td>
          <td width="12" background="img/frame_tcr.gif"></td>
        </tr>
        <tr> 
          <td valign="top" colspan="3"><img src="img\frame_tcb.gif" width="500" height="12" border="0"></td>
        </tr>
        </tbody> 
      </table>
    </div>
</form>

</td>
      </tr>

      <tr>

        <td>
	<%
  rs.absolute(start);
  rs.previous();
  if(count==0)
  {
  		out.print("<center><font color='#528e31'>还没有人留言噢!</font></center>");
  }
  while(rs.next()){
  %>	

		<table align="center" border="0" cellpadding="0" cellspacing="0">
          <tbody>
            <tr>
              <td valign="top" colspan="3" align="left"><img src="img\frame_tgt.gif" width="100%" height="100" border="0"></td>
            </tr>
            <tr>
              <td width="12" background="img/frame_tgl.gif"></td>
              <td bgcolor="#ffffff" align="center" width="476"><table cellpadding="0" cellspacing="0">
                  <tbody>
                    <tr>
                      <td width="103" class="inp_set2"><p>IP:<br>
                        <% int point=rs.getString(10).lastIndexOf(".");out.print(rs.getString(10).substring(0,point)+".*");
%>
                        </p>
                      </td>
                      <td width="3"></td>
                      <td><table width="366" border="0" align="right" cellpadding="0" cellspacing="0">
                          <tbody>
                            <tr>
                              <td width="42" style="color:#528e31;">时间:
                              <% String tim=rs.getString(11);out.print(tim.substring(5,16));%></td>
                              <td valign="bottom" width="12"><img src="img\Fi_fu1.gif" width="12" height="12" border="0"></td>
                              <td width="195" align="center" valign="bottom" style="color:#528e31;">主题:<%=rs.getString(2)%></td>
                              <td valign="bottom" width="36"><img src="img\Fi_fu1.gif" width="12" height="12" border="0"><a href="http://wpa.qq.com/msgrd?V=1&Uin=<%=rs.getString(4)%>&Site=<%=rs.getString(3)%>&Menu=yes" target="_blank"><img src="pic/qq.gif" alt="点击给我发消息" width="23" height="16" border="0"></a></td>
                              <td width="25"><% if("y".equals(pass)){ %>
                                  <div align="center"><a href="delete.jsp?id=<%=rs.getString(1)%>"><img src="pic/del.gif" onClick="return confirm(&quot;确认要删除吗?&quot;)" width="25" height="17" border="0" alt="删除"></a></div>
                                <%}%>                              </td>
                              <td width="76" valign="bottom" align="center"><a target="_blank" href=<%=rs.getString(6)%>><img border="0" src="pic/home.gif" alt="偶的竹叶。。。" width="21" height="20"></a><a href="mailto:<%=rs.getString(5)%>"><img border="0" src="img/fi_mp.gif" alt="伊妹儿" width="17" height="15"></a></td>
                            </tr>
                          </tbody>
                      </table></td>
                    </tr>
                    <tr>
                      <td width="103" align="center"><p><img src="face/<%=rs.getString(8)%>" width="65" height="110" border="0" align="absmiddle"></p>
                          <p style="color:#528e31;">昵称:<%=rs.getString(3)%></p></td>
                      <td width="3"></td>
                      <td width="368"><table cellpadding="5" cellspacing="1" width="100%" bgcolor="#c2e4ae">
                          <tbody>
                            <tr>
                              <td bgcolor="#ffffff" align="center" valign="bottom"><br>
                                  <table cellpadding="0" cellspacing="0">
                                    <tbody>
                                      <tr>
                                        <td align="center"><table border="0" cellpadding="0" cellspacing="0">
                                            <tbody>
                                              <tr>
                                                <td height="20" align="center" valign="top" style="color:#528e31;"><%//if(rs.getString(7))=="y"){%>
												<p align="left"><%=rs.getString(9)%>
                                                  <p align="left">
												  <%//}%>
											    </td>
                                              </tr>
                                              <tr>
                                                <td width="300"><img src="img\fi_lg.gif" width="300" height="12" border="0"></td>
                                              </tr>
                                            </tbody>
                                        </table></td>
                                      </tr>
                                    </tbody>
                                  </table>
                                <br></td>
                            </tr>
                          </tbody>
                      </table></td>
                    </tr>
                  </tbody>
              </table></td>
              <td width="12" background="img\frame_tgr.gif"></td>
            </tr>
            <tr>
              <td valign="top" colspan="3" height="12"><img src="img\frame_tgb.gif" width="100%" height="12" border="0"></td>
            </tr>
          </tbody>
        </table>
<%
  i++;
  if(i==showrow)break;
   }
  %>
		</td>

      </tr>

      <tr>
        <td><table width="605" height="23" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="7">&nbsp;</td>
            <td width="591"><div align="right">
                <%
		 if(pa<=5){
	for(int j=1;j<pa+1;j++){
    if(j==pages){%>
                <font color=#ff0000>第<%=pages%>页</font>&nbsp;
                <% }else{%>
                <a href="index.jsp?pages=<%=j%>">第<%=j%>页</a>&nbsp;
                <% }
    }
	}else if(pages<4){
	for(int k=1;k<pages+3;k++){
    if(k==pages){%>
                <font color=#ff0000>第<%=k%>页</font>&nbsp;
                <% }else{ %>
                <a href="index.jsp?pages=<%=k%>">第<%=k%>页</a>&nbsp;
                <% }
    }
	}else if((pa-pages)<4){
	for(int l=pa-5;l<=pa;l++){if(l==pages){ %>
                <font color="#FF0000">第<%=l%>页</font>&nbsp;
                <% }else{ %>
                <a href="index.jsp?pages=<%=l%>">第<%=l%>页</a>&nbsp;
                <% }}
	}else{
	for(int m=pages-3;m<pages+3;m++){if(m==pages){ %>
                <font color="#FF0000">第<%=m%>页</font>&nbsp;
                <% }else{ %>
                <a href="index.jsp?pages=<%=m%>">第<%=m%>页</a>&nbsp;
                <% }}
	}%>
            &nbsp;&nbsp;<span class="inp_set1">共<%=pa%>页 <%=count%>条</span>&nbsp;&nbsp;</div></td>
            <td width="10">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center">
          <table width="304" height="16" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="19"><img src="img\Fi_fu1.gif" width="12" height="12"></td>
               


              <td width="20"><img src="img\Fi_fu1.gif" width="12" height="12"></td>
            </tr>
          </table>
        </div></td>
      </tr>
</table>
</body>                                                                            
</html>
<script language="JavaScript" type="text/JavaScript">
function check_form(form1)
	{
		if (form.title.value=="")
		{
			alert("请输入留言的主题！");
			form.title.focus();
			return false;
		}
		if (form.name.value=="")
		{
			alert("昵称好像忘了填?");
			form.name.focus();
			return false;
		}
		if (form.QQ.value=="")
		{
			alert("QQ号忘了填！");
			form.QQ.focus();
			return false;
		}
		if (form.QQ.value.length<5)
		{
			alert("现在的QQ号好像最少也是5位吧?！");
			form.QQ.focus();
			return false;
		}
		if (form.email.value!="")
		{

			if (form.email.value.indexOf ('@') == -1||form.email.value.indexOf ('.') == -1)
				{
				alert("E-mail格式可能不对噢?！");
				form.email.focus();
				return false;
				}
		}
		if (form.rand.value=="")
		{
			alert("验证码没输入！");
			form.rand.focus();
			return false;
		}
		if (form.text.value=="")
		{
			alert("你留言了吗?！");
			form.text.focus();
			return false;
		}
		if (form.text.value.length>300)
		{
			alert("你想把我数据库挤爆啊?!说这么多?");
			form.text.focus();
			return false;
		}
	}
</script>
<% 
rs.close(); 
st.close(); 
con.close(); 
%>  
 