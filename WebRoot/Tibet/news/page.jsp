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
����ҳ��

����һҳ��

</span>
<%}else{ %>
<a class="global" href="<%=pager.getFirstLink()%>">����ҳ��</a>
<a class="global" href="<%=pager.getPreviousLink()%>">����һҳ��</a>

<%
 
} %>
<%
if(pager.getHasNext() == 1){ %>
<a class="global" href="<%=pager.getNextLink()%>">����һҳ��</a>
<a class="global" href="<%=pager.getLastLink()%>">��βҳ��</a>
<%}else{ %>
<span disabled>
����һҳ��

��βҳ��

</span>
<%
}
 %>
&nbsp;&nbsp;
ҳ��<%=pager.getCurrentPage()%>/<%=pager.getTotalPage()%> ÿҳ<%=pager.getPageSize()%>��/��<%=pager.getTotalRecord()%>��
&nbsp;ת�� <input type="text" name="toPage" size="6">
<input type="submit" value="go" class="btn">
</form>
</div>
<%
}
 %>

