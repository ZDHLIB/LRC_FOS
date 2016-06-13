<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
 <jsp:useBean id="thenews" scope="page" class="net.lrc.javabean.NewsBean"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>新闻中心</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8"><LINK 
href="newsList_files/list.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.6000.16762" name=GENERATOR></HEAD>
<BODY text=#666666 bgColor=#ffffff leftMargin=0 topMargin=0 marginwidth="0" 
marginheight="0">
<DIV align=center>
<TABLE height=50 cellSpacing=0 cellPadding=0 width=760 border=0>
  <TBODY>
  <TR>
    <TD vAlign=bottom align=left background=newsList_files/obj2_01.gif><IMG 
      height=30 src="newsList_files/first.jpg" 
width=150></TD></TR></TBODY></TABLE></DIV>
<DIV align=center>
<TABLE cellSpacing=0 cellPadding=0 width=760 border=0>
  <TBODY>
  <TR>
    <TD><IMG height=8 src="newsList_files/obj2_02.gif" 
width=760></TD></TR></TBODY></TABLE></DIV><!--  
<DIV align=center>
 <FORM name=search 
    onsubmit="if(this.keyword.value==''){alert('请填写关键词');this.keyword.focus();return false}else{return true;}" 
    action="">
<TABLE class=p9 cellSpacing=0 cellPadding=0 width=760 
background=newsList_files/obj2_04.gif border=0>
  <TBODY>
  <TR>
    <TD align=right width=100 height=21>&nbsp;</TD>
   
    <TD vAlign=center width=360><INPUT class=tx3 size=12 name=keyword> <SELECT 
      id=type name=type> <OPTION value=topic selected>标题</OPTION> <OPTION 
        value=username>作者</OPTION> <OPTION value=content>正文</OPTION></SELECT> 
      &nbsp; <SELECT id=searchdate name=searchdate> <OPTION value=-1 
        selected>---搜索所有---</OPTION> <OPTION value=1>&nbsp;搜索今日最新&nbsp;</OPTION> 
        <OPTION value=3>&nbsp;搜索最近3天&nbsp;</OPTION> <OPTION 
        value=5>&nbsp;搜索最近5天&nbsp;</OPTION> <OPTION 
        value=10>&nbsp;搜索最近10天&nbsp;</OPTION> <OPTION 
        value=30>&nbsp;搜索最近30天&nbsp;</OPTION> <OPTION 
        value=60>&nbsp;搜索最近60天&nbsp;</OPTION> <OPTION 
        value=180>&nbsp;搜索最近半年&nbsp;</OPTION></SELECT>&nbsp; <INPUT class=bt3 type=submit value=搜索> </TD>
    <TD vAlign=center width=204>
      <DIV align=right>
      <SCRIPT language=JavaScript>
 
   var enabled = 0;   today = new Date();
   var day;   var date;
   if(today.getDay()==0)     day = "星期日"
   if(today.getDay()==1)     day = "星期一"
   if(today.getDay()==2)     day = "星期二"
   if(today.getDay()==3)     day = "星期三"
   if(today.getDay()==4)     day = "星期四"
   if(today.getDay()==5)     day = "星期五"
   if(today.getDay()==6)     day = "星期六"
   document.fgColor = " FF0072";
   date1 = "<font size=2 color=#547137>" + (today.getYear())  + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日  " + "</font>";
   date2 = "<font size=2 color=#547137>" +  day + "</font>";
   document.write( date1.fontsize(3) + date2.fontsize(3) );
 
</SCRIPT>
    </DIV></TD>
    <TD width=96>　</TD></TR></TBODY></TABLE> </FORM>    </DIV>-->
<DIV align=center>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width=761 border=0><!--DWLayoutTable-->
  <TBODY>
  <TR>
    <TD vAlign=top width=100 background=newsList_files/bg_01.gif 
      height=488><IMG height=1 src="newsList_files/spacer.gif" width=1></TD>
    <TD width=23>&nbsp;</TD>
    <TD vAlign=top width=541>
      <TABLE cellSpacing=0 cellPadding=0 width=530 align=center border=0><!--DWLayoutTable-->
        <TBODY>
        <TR>
          <TD width=530 height=10><IMG height=1 
            src="newsList_files/spacer.gif" width=1></TD>
          <TD width=11></TD></TR>
        <TR>
          <TD vAlign=top height=442>
            <TABLE cellSpacing=0 cellPadding=0 width=530 border=0><!--DWLayoutTable-->
              <TBODY>
              <TR>
                <TD width=530 height=18><IMG height=18 
                  src="newsList_files/p_01.gif" width=15> <SPAN 
                  class=songgreenbold12>中心新闻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;<a href= "<%=request.getContextPath()%>/Tibet/index.jsp ">BACK</a></SPAN></TD>
                <TD>&nbsp;</TD></TR>
              <TR>
                <TD vAlign=top height=3><IMG height=3 
                  src="newsList_files/ba1_01.gif" width=680></TD>
                <TD>&nbsp;</TD></TR>
              <TR>
                <TD vAlign=top height=392>
                  <TABLE cellSpacing=0 cellPadding=0 width=530 border=0><!--DWLayoutTable-->
                    <TBODY>
                    <TR>
                      <TD width=15 height=21>&nbsp;</TD>
                      <TD width=515>&nbsp;</TD></TR>
                  
		<%
		   String offset = request.getParameter("offset");
		   String fp = request.getRequestURI();
		   thenews.setNewsTypeStr("('1','2')");
		   thenews.setResult(offset,fp);
		   	List list = thenews.getResult();
		    if(list == null) System.out.println("error");
		   SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
		   int index0=6;
										if (list != null && list.size() > 0) {
											for (Object news : list) {
											net.lrc.util.NewsDetails n = (net.lrc.util.NewsDetails)news;
												 
												String date = ss.format(n.getSubmittime());
		   
		 %>
                    <TR>
                      <TD vAlign=top height=21><IMG height=18 
                        src="newsList_files/p_02.gif" width=15></TD>
                      <TD vAlign=top><A 
                        href="<%=request.getContextPath()%>/news/news.do?method=new&contentid=<%=n.getKind()%>&partid=<%=n.getType()%>&id=<%=n.getId()%>" 
                        target=_blank><%=n.getTitle()%>&nbsp;[<%=date%>]</A><%if(--index0>0){ %><img src="<%=request.getContextPath()%>/lingdot_files/new.gif" border=0><%} %>&nbsp;</TD></TR>
                   <%
										}
										}
									%>     
                        
                        
                        
                       </TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
        <TR>
          <TD colSpan=2 height=27 align="center">
          <%
					out.print(thenews.PageLegend());
				%></TD>
        <TR>
          <TD vAlign=top height=15>
            <HR width=530>
          </TD>
          <TD></TD></TR>
        <TR>
          <TD vAlign=top align=right height=21></TD>
          <TD></TD></TR></TBODY></TABLE></TD>
    <TD vAlign=top width=97 background=newsList_files/bg_02.gif><IMG height=1 
      src="newsList_files/spacer.gif" width=1></TD></TR></TBODY></TABLE><%@ include file="../footer.jsp"%></DIV></BODY></HTML>
