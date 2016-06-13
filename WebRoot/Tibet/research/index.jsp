<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  %>
<jsp:useBean id="commonBean" scope="page" class="net.lrc.javabean.CommonBean"></jsp:useBean>
<%@ page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="contentBean" scope="page" class="net.lrc.javabean.ContentBean"></jsp:useBean>
<center> 
<%@ include file="/Tibet/header.jsp" %>
<LINK href="<%=request.getContextPath()%>/Tibet/lingdot_files/style.css" type=text/css rel=stylesheet>

<META http-equiv=Content-Type content="text/html; charset=utf-8">
<script language=JavaScript>
function submitForm(partid,contentid){
   
    document.listform.action="CommonServlet.do?method=getComtentListByCoutentAndPart&partid="+partid+"&contentid="+contentid;
    document.listform.submit();
    document.listform.target="content";
}



</script>

<table width="970">
<tr bgcolor="#99CCff"><td class=tool_bar align=left height=25>
<A 
            href="<%=request.getContextPath()%>/Tibet/index.jsp"><FONT 
            class=text_position_3 color=black><%=commonBean.getTitlebyPartID("2") %>》》</FONT></A>&nbsp;☆&nbsp;  
<%
  
		   List  list=contentBean.listbyPart("2");
		   int len = list.size();
		   if(list == null) System.out.println("error");
		    
										if (list != null && list.size() > 0) {
											for (Object contents : list) {
											net.lrc.model.Content n = (net.lrc.model.Content)contents;
												 
											 
 %>
<A  href="#" onclick="submitForm('2','<%=n.getId()%>')"><FONT 
            class=text_position_3 color=black><%=n.getTitle()%></FONT></A>&nbsp;☆&nbsp;  
            
           <%} }%> 
            
             </td>
			</tr>
</table>
 <div id="content"  style="min-height:400px;_height:400px;background:url('<%=request.getContextPath() %>/images/logoback.jpg'); ">
 <form name="listform" action="" id="listform" method="post"> </form>
 <table>
 <% List newsList =(List)request.getAttribute("newsList"); 
  
    if (newsList != null && newsList.size() > 0) {
    
 
											for (Object news : newsList) {
											net.lrc.util.NewsDetails n = (net.lrc.util.NewsDetails)news;
												 SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd"); 
												String date = ss.format(n.getSubmittime());
  %>
                       <TR>
                      <TD vAlign=top height=21><IMG height=18 
                        src="p_01.gif" width=15></TD>
                      <TD  class=index_text_6 vAlign=top><A 
                        href="<%=request.getContextPath()%>/news/news.do?method=new&contentid=<%=request.getParameter("contentid")%>&partid=<%=request.getParameter("partid")%>&id=<%=n.getId()%>" 
                        target=_blank><%=n.getTitle()%>&nbsp;[<%=date%>]</A>&nbsp;</TD>
                        </TR>
 <%}} %>
 </table>
 </div>
 
<%@ include file="../footer.jsp"%>
</center> 
<%
  String  partidindex=request.getParameter("partidindex");
  String  contentidindex=request.getParameter("contentidindex");
if ((partidindex != null) &&(partidindex != "")&&(contentidindex != null) && (contentidindex != "")){
  out.print("<script language=\"JavaScript\" >submitForm("+partidindex+","+contentidindex+");</script>");
  partidindex=null;
  contentidindex=null;
}
//else{
        //partidindex="02";
      //  contentidindex=commonBean.getMinXHByPartID("02");
      //  out.print("<script language=\"JavaScript\" >submitForm("+partidindex+","+contentidindex+");</script>");
      //   partidindex=null;
 // contentidindex=null;
//}

 %>
