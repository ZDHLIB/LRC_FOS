<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<jsp:directive.page import="net.lrc.util.Pager"/>
<%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path1 + "/";
%>
<LINK href="<%=basePath1%>default.css" type=text/css	rel=stylesheet>
<%  
Pager pager =  (Pager)request.getAttribute("pager");  
if(pager != null&&pager.toString() != ""){ 
%>
<script language='javascript'>
function changePage(){
var s=document.all['toPage'].value;
if(s==""){
s=1;
}
window.location.href="<%=pager.getForScriptLink()%>toPage="+s;
return false;
}
</script>
<div align="center">
<form onsubmit="return changePage()">
<% if(pager.getCurrentPage() == 1){ %>
<span disabled>
【首页】

【上一页】

</span>
<%}else{ %>
<a class="global" href="<%=pager.getFirstLink()%>">【首页】</a>
<a class="global" href="<%=pager.getPreviousLink()%>">【上一页】</a>

<%
 
} %>
<%
if(pager.getHasNext() == 1){ %>
<a class="global" href="<%=pager.getNextLink()%>">【下一页】</a>
<a class="global" href="<%=pager.getLastLink()%>">【尾页】</a>
<%}else{ %>
<span disabled>
【下一页】

【尾页】

</span>
<%
}
 %>
&nbsp;&nbsp;
页数<%=pager.getCurrentPage()%>/<%=pager.getTotalPage()%> 每页<%=pager.getPageSize()%>条/共<%=pager.getTotalRecord()%>条
&nbsp;转到 <input type="text" name="toPage" size="6">
<input type="submit" value="go" class="btn">
</form>
</div>
<%
}
 %>

