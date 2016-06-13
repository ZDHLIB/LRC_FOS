<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="commonBean" scope="page" class="net.lrc.javabean.CommonBean"></jsp:useBean>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 <%
							net.lrc.util.NewsDetails  news = (net.lrc.util.NewsDetails)request.getAttribute("news");
							 if(news==null){
							 out.print("新闻已经被删除或锁定");
							 out.close();
							 }
							SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
							String date = ss.format(news.getSubmittime());
							String partid = (String)request.getAttribute("partid");
		                   String contentid = (String)request.getAttribute("contentid");
							 
							%>
<HTML><HEAD><TITLE>新闻中心：<%=news.getTitle()%></TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8"><LINK 
href="news_files/news.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.6000.16762" name=GENERATOR></HEAD>
<BODY text=#666666 bgColor=#ffffff leftMargin=0 background=news_files/bg_02.gif 
topMargin=0 marginwidth="0" marginheight="0">
<DIV align=center>
<TABLE height=50 cellSpacing=0 cellPadding=0 width=760 border=0>
  <TBODY>
  <TR>
    <TD vAlign=bottom align=left background=news_files/obj2_01.gif><IMG 
      height=30 src="news_files/obj_title.gif" 
width=150></TD></TR></TBODY></TABLE></DIV>
<DIV align=center>
<TABLE cellSpacing=0 cellPadding=0 width=760 border=0>
  <TBODY>
  <TR>
    <TD><IMG height=8 src="news_files/obj2_02.gif" 
width=760></TD></TR></TBODY></TABLE></DIV>
<DIV align=center>
<TABLE cellSpacing=0 cellPadding=0 width=760 background=news_files/obj2_04.gif 
border=0 name="menubutton"><!--DWLayoutTable-->
  <TBODY>
  <TR>
    <TD width=760 height=21>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<SPAN 
      class=songgreen12>信息类别:</SPAN> <%=commonBean.getTitlebyPartID(partid) %>  >> <%=commonBean.getTitlebyContentID(Integer.parseInt(contentid)) %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <a href= "<%=request.getContextPath()%>/index.jsp ">返回主页</a></TD></TR></TBODY></TABLE></DIV>
<DIV align=center>
<TABLE height="92%" cellSpacing=0 cellPadding=0 width=761 bgColor=#ffffff 
border=0><!--DWLayoutTable-->
  <TBODY>
  <TR>
    <TD vAlign=top width=761 height=388>
      <DIV align=center>
      <TABLE cellSpacing=0 cellPadding=0 width=680 align=center border=0><!--DWLayoutTable-->
        <TBODY>
        <TR>
          <TD width=57></TD>
          <TD colSpan=2><IMG height=1 src="news_files/spacer.gif" width=1></TD>
          <TD width=93></TD></TR>
        <TR>
          <TD></TD>
          <TD colSpan=2><IMG height=1 src="news_files/spacer.gif" width=1></TD>
          <TD></TD></TR>
        <TR>
          <TD vAlign=top colSpan=4>
            <DIV align=center>
            <TABLE cellSpacing=0 cellPadding=0 width=680 border=0><!--DWLayoutTable-->
              <TBODY>
              <TR>
                <TD vAlign=top width=680 
                height=16><!--DWLayoutEmptyCell-->&nbsp;</TD></TR>
               
              <TR>
                <TD vAlign=top height=16>
                  <DIV align=center>
                  <P class=style3><STRONG><FONT size=+1><%=news.getTitle() %>
                  </FONT></STRONG></P>
                  <P align=right><SPAN 
                  class=songblue12line20>信息发布时间:</SPAN>&nbsp; <%=date %> 
                  &nbsp;&nbsp; </P></DIV></TD></TR>
              <TR>
                <TD vAlign=top height=5><IMG height=5 
                  src="news_files/ba1_01.gif" width=680></TD></TR>
              <TR>
                <TD vAlign=top height=325>
                  <TABLE width="100%" align=center><!--DWLayoutTable-->
                    <TBODY>
                    <TR>
                      <TD vAlign=top align=middle colSpan=2>
                        <P> </P></TD></TR>
                    <TR>
                      <TD style="MIN-HEIGHT: 400px; HEIGHT: 400px" vAlign=top 
                      colSpan=2 height=9>
                        <P><%=news.getContent() %><BR></P>
                        <P>&nbsp;</P></TD></TR></TBODY>
                    <TBODY>
                    <TR>
                      <TD vAlign=top borderColor=#eeeeee width=157 
                      bgColor=#ffffff height=25><SPAN 
                        class=songblue12line20>信息发布:</SPAN>&nbsp; <%=commonBean.getDepNamebyAdminID(news.getLrryID())%> </TD>
                      <TD vAlign=top borderColor=#eeeeee width=507 
                      bgColor=#ffffff>
                        <DIV align=right><SPAN 
                        class=songblue12line20>信息有效期:</SPAN>&nbsp; 
                        <%=ss.format(news.getLastmodified())%>&nbsp;至&nbsp;<%=ss.format(new Date(news.getLastmodified().getTime()+new Long(news.getEffectivedays())*24*60*60*1000))%> &nbsp;</DIV></TD></TR>
                    <TR>
                      <TD vAlign=top colSpan=2 height=144>
                        <P>
                        <P></P></TD></TR></TBODY></TABLE></TD></TR>
              <TR>
                <TD height=21>&nbsp;</TD></TR></TBODY></TABLE></DIV></TD></TR>
        <TR>
          <TD vAlign=top colSpan=4 height=19>
            <DIV align=right><A href="javascript:window.print()">打印本页</A> | <A 
            href="javascript:window.close()">关闭窗口</A>&nbsp;</DIV></TD></TR>
        <TR>
          <TD vAlign=top colSpan=4 height=15>
            <HR width=680>
          </TD></TR>
        <TR>
          <TD vAlign=top align=right colSpan=4 height=21>
             </TD></TR></TBODY></TABLE></DIV></TD></TR></TBODY></TABLE></DIV></BODY>
    
     </HTML>
