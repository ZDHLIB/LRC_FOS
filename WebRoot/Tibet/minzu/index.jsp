<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:useBean id="commonBean" scope="page"
	class="net.lrc.javabean.CommonBean"></jsp:useBean>
<jsp:useBean id="contentBean" scope="page"
	class="net.lrc.javabean.ContentBean"></jsp:useBean>
<LINK href="<%=request.getContextPath()%>/lingdot_files/style.css"
	type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<script language=JavaScript>
function setContent(partid,contentid){
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
      alert("ok");
    }else{
    alert("error");
    }
    }
  
  }; 
   
  if ((partid == null) || (partid == "")) return;
    if ((contentid == null) || (contentid == "")) return;
    var url="CommonServlet.do?method=getComtentByCoutentAndPart&partid="+partid+"&contentid="+contentid;   
  xmlHttp.open("GET", url,false);   
  //xmlHttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
  xmlHttp.send(null); 
   
 document.getElementById("content").innerHTML="<table><tr><td>"+xmlHttp.ResponseText+"</td></tr></table>";
 
}



</script>
<center>
	<%@ include file="/Tibet/header.jsp"%>
	<table width="970">
		<tr bgcolor="#99CC000">
			<td class=tool_bar align=left height=25>
				<A href="<%=request.getContextPath()%>/Tibet/index.jsp"><FONT
					class=text_position_3 color=black><%=commonBean.getTitlebyPartID("3")%>》》</FONT>
				</A>&nbsp;※|※&nbsp;
				<%
					List list = contentBean.listbyPart("3");
					if (list == null)
						System.out.println("error");

					if (list != null && list.size() > 0) {
						for (Object contents : list) {
							net.lrc.model.Content n = (net.lrc.model.Content) contents;
				%>
				<A href="#"
					onclick="setContent('3','<%=String.valueOf(n.getId())%>')"><FONT
					class=text_position_3 color=black><%=n.getTitle()%></FONT> </A>&nbsp;※|※&nbsp;

				<%
					}
					}
				%>

			</td>
		</tr>
	</table>
	<div id="content" style="min-height: 400px; _height: 400px">



	</div>
	<%@ include file="../footer.jsp"%>
	<%
		String partid = request.getParameter("partid");
		String contentid = request.getParameter("contentid");
		if ((partid != null) && (partid != "") && (contentid != null)
				&& (contentid != "")) {
			out.print("<script language=\"JavaScript\" >setContent("
					+ partid + "," + contentid + ");</script>");
		} else {
			partid = "03";
			contentid = commonBean.getMinXHByPartID("03");
			out.print("<script language=\"JavaScript\" >setContent("
					+ partid + "," + contentid + ");</script>");
			partid = null;
			contentid = null;
		}
	%>
</center>